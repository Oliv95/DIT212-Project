package domain.promt;

import domain.App;
import domain.interfaces.Domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by oliv on 4/14/16.
 */
public class Promt {

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final String prompt = "> ";
    private final String helpText   = "This is help text";
    private final String separator = " ";
    private Domain domain;

    public Promt(Domain domain){
        this.domain = domain;
    }

    private void start() throws IOException{

        while (true){
            String input = next().trim();
            if (input.equals("-1")){
                break;
            }
            int code;
            try {
                code = Character.getNumericValue(input.charAt(0));
            } catch (NumberFormatException e) {
                parseErrorMsg();
                continue;
            }
            String[] options = input.split(separator);
            String email;
            String result;
            String courseName;
            String gCode;
            switch (code) {
                //createUser
                case 0:
                    email = options[1];
                    result = domain.createUser(email);
                    System.out.println(result);
                    break;
                //createAdmin
                case 1:
                    email = options[1];
                    result = domain.createAdmin(email);
                    System.out.println(result);
                    break;
                //createCourse
                case 2:
                    email = options[1];
                    courseName = options[2];
                    domain.createCourse(email,null);
                    break;
                //joinCourse
                case 3:
                    gCode = options[1];
                    email = options[2];
                    domain.joinCourse(gCode,null);
                    break;
                //matchRequest
                case 4:
                    String sender = options[1];
                    String reciver = options[2];
                    String gcode = options[3];
                    domain.matchRequest(0,0,gcode);
                    break;
                //getUsers
                case 5:
                    email = options[1];
                    domain.getUser(email);
                    break;
                //getAllUsers
                case 6:
                    gcode = options[1];
                    String[] users = null;//domain.getAllUsers(0);
                    System.out.println(Arrays.toString(users));
                    break;
                //getMatchedWithMe
                case 7:
                    email = options[1];
                    String[] matches = null; //domain.getMatchedWithMe(0);
                    System.out.println(Arrays.toString(matches));
                    break;
                case 8:
                    System.out.println(helpText);
                    break;
                default:
                    break;
            }

        }
    }

    private void parseErrorMsg(){
        System.out.println("Invalid code, enter digit between 0 and 7 inclusive");
    }

    private String next() throws IOException{
        System.out.print(prompt);
        return reader.readLine();
    }

    public static void main(String[] args) {
        try {
            new Promt(new App()).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*

    class createUser implements Function<String,String> {

        @Override
        public String apply(String s) {
            return domain.createUser(s);
        }
    }

    class createAdmin implements Function<String,String> {

        @Override
        public String apply(String s) {
            return domain.createAdmin(s);
        }
    }

    // String,String  inout
    class createCourse implements Function<Pair<String,Admin>,Void> {

        @Override
        public Void apply(Pair<String,Admin> pair) {
            String email = pair.getLeft();
            Admin admin = pair.getRight();
            return domain.createCourse(email,admin);
        }
    }

    //String inout String inout return bool
    class joinCourse implements Function<Pair<String,User>,Boolean> {

        @Override
        public Boolean apply(Pair<String,User> pair) {
            String gcode = pair.getLeft();
            User email = pair.getRight();
            return domain.joinCourse(gcode,email);
        }
    }

    // inout String String  Gcode outout return void
    class matchRequest implements Function<Triplet<String,String,String>,Void> {

        @Override
        public Void apply(Triplet<String, String, String> stringStringStringTriplet) {
            return null;
        }
    }
    //input string return user
    class getUsers implements Function<String, User> {

        @Override
        public User apply(String s) {
            return null;
        }
    }
    //input string Gcode return User[]
    class getAllUsers implements Function<String,User[]> {

        @Override
        public User[] apply(String s) {
            return null;
        }
    }

    //input String , Gcode return user[]
    class getMatchedWithMe implements Function<String,User[]> {

        @Override
        public User[] apply(String s) {
            return null;
        }
    }

*/
}
