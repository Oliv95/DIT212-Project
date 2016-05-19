package domain.Repos;

import domain.Admin;
import domain.Course;
import domain.Matched;
import domain.User;
import domain.interfaces.ICourseRepo;
import domain.util.Gcode;
import sun.misc.GC;

import java.io.*;
import java.util.*;

/**
 * Created by oliv on 4/23/16.
 */
public class LocalCourseRepo implements ICourseRepo{

    private Map<Gcode, Course> courses;
    private static LocalCourseRepo repo;

    private final String SEPERATOR = File.separator;
    private final String PATH = System.getProperty("user.home")+SEPERATOR;
    private final String COURSESFILENAME = "Courses.ser";
    private final String GCODEFILENAME = "Gcode.txt";

    public void saveCourses(){
        saveState(COURSESFILENAME, courses);
    }

    private void saveState(String fileName, Object toSave){
        //TODO what to do about exceptions
        ObjectOutputStream objectStream = null;
        FileOutputStream outStream = null;
        try {
            outStream = new FileOutputStream(PATH+fileName);
            objectStream = new ObjectOutputStream(outStream);
            objectStream.writeObject(toSave);
            objectStream.close();
            outStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Object readState(String fileName){
        FileInputStream inStream = null;
        ObjectInputStream objectInStream = null;
        Object result = null;
        try {
            inStream = new FileInputStream(PATH+fileName);
            objectInStream = new ObjectInputStream(inStream);
            result = objectInStream.readObject();
            objectInStream.close();
            inStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void readCourses() {
        courses = (Map<Gcode,Course>) readState(COURSESFILENAME);
    }

    private void readCounter() {
        try {
            FileReader fileReader = new FileReader(PATH + GCODEFILENAME);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String counter = bufferedReader.readLine();
            bufferedReader.close();
            fileReader.close();
            if (counter.trim().equals("")) {
                Gcode.setCounter(0);
            }
            else {
                int c = Integer.parseInt(counter.trim());
                Gcode.setCounter(c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveCounter(int toWrite){
            File f = new File(PATH + GCODEFILENAME);
        try {
            BufferedWriter b = new BufferedWriter(new FileWriter(f));
            b.write(String.valueOf(toWrite));
            b.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * private constructor
     */
    private LocalCourseRepo() {
        if(!(new File(PATH + COURSESFILENAME).exists())) {
            try {
                new File(PATH + COURSESFILENAME).createNewFile();
                courses = new LinkedHashMap<>();
                saveState(COURSESFILENAME, courses);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(!(new File(PATH + GCODEFILENAME).exists())) {
            try {
                new File(PATH + GCODEFILENAME).createNewFile();
                saveCounter(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        readCounter();
        readCourses();

    }

    /**
     * Singleton constructor for LocalCourseRepo
     * @return the single repository object
     */
    public static synchronized LocalCourseRepo getInstance() {
        if(repo == null) {
            repo = new LocalCourseRepo();
            return repo;
        } else {
            return repo;
        }
    }

    @Override
    public String getCourseAdmin(Gcode gcode) {
        return courses.get(gcode).getAdmin();
    }

    @Override
    public List<String> getAllEnrolled(Gcode gcode) {
        Course c = courses.get(gcode);
        if (c == null) {
            return null;
        }
        return c.getRegisteredEmails();
    }

    @Override
    public Gcode createCourse(String admin, String name) {
        Gcode code = new Gcode();
        Course course = new Course(name, admin, code);
        courses.put(code, course);
        saveCounter(Gcode.getCounter());
        saveState(COURSESFILENAME,courses);
        return code;
    }

    @Override
    public List<Matched> getAllMatches(Gcode gcode) {
        return courses.get(gcode).returnMatched();
    }

    @Override
    public void matchWith(List<String> toMatch, Gcode course) {
        courses.get(course).putMatchRequest(toMatch.get(0), toMatch.get(1));
        saveState(COURSESFILENAME,courses);
    }

    @Override
    public List<Gcode> getEnrolledIn(String email) {
        List<Gcode> enrolled = new ArrayList<>();
        for(Course c : courses.values()) {
            for(String member : c.getRegisteredEmails()) {
                if(member.equals(email)) {
                    enrolled.add(c.getCode());
                    break;
                }
            }
        }
        return enrolled;
    }

    @Override
    public List<Gcode> getAllAdministrating(String email) {
        List<Gcode> administrating = new ArrayList<>();
        for(Course c : courses.values()) {
            if(c.getAdmin().equals(email)) {
                administrating.add(c.getCode());
            }
        }
        return administrating;
    }

    @Override
    public List<Course> getAllCourses() {
        List<Course> list = new ArrayList<>();
        for(Course c : courses.values()) {
            list.add(c);
        }
        return list;
    }

    @Override
    public Course getCourse(Gcode code) {
        return courses.get(code);
    }

}
