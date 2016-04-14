package domain.promt;

import domain.App;
import domain.User;
import domain.interfaces.Domain;
import domain.util.Gcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by oliv on 4/14/16.
 */
public class Promt {

    //TODO protect vs indexoutofbounds

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final String prompt = "> ";
    private final String helpText   = "0 CreateUser email,name,password" + System.lineSeparator() +
                                      "1 CreateAdmin email,name,password" + System.lineSeparator() +
                                      "2 createCourse courseName, email" + System.lineSeparator() +
                                      "3 joinCourse gcode, email" + System.lineSeparator() +
                                      "4 matchRequest sender, receiver,gcode" + System.lineSeparator() +
                                      "5 getUser email" + System.lineSeparator() +
                                      "6 getAllUsers gcode" + System.lineSeparator() +
                                      "7 getMatchedWithMe email gcode" + System.lineSeparator();

    private final String separator = " ";
    private Domain domain;

    public Promt(Domain domain){
        this.domain = domain;
    }

    private void start() throws IOException{

        while (true){
            String input = next().trim();
            checkTermination(input);
            int code;
            try {
                code = Integer.parseInt(input.split(separator)[0]);
            } catch (NumberFormatException e) {
                parseErrorMsg();
                continue;
            }
            String[] options = input.split(separator);
            String email;
            String name;
            String password;
            String result;
            String courseName;
            Gcode gCode;
            switch (code) {
                //createUser
                case 0:
                    email = options[1];
                    name = options[2];
                    password = options[3];
                    result = domain.createUser(email,name,password);
                    System.out.println(result);
                    break;
                //createAdmin
                case 1:
                    email = options[1];
                    name = options[2];
                    password = options[3];
                    result = domain.createAdmin(email,name,password);
                    System.out.println(result);
                    break;
                //createCourse
                case 2:
                    email = options[1];
                    courseName = options[2];
                    domain.createCourse(courseName,email);
                    break;
                //joinCourse
                case 3:
                    int id = Integer.parseInt(options[1]);
                    gCode = Gcode.makeGcode(id);
                    email = options[2];
                    domain.joinCourse(gCode,email);
                    break;
                //matchRequest
                case 4:
                    String sender = options[1];
                    String receiver = options[2];
                    Gcode gcode = Gcode.makeGcode(Integer.parseInt(options[3]));
                    domain.matchRequest(sender,receiver,gcode);
                    break;
                //getUsers
                case 5:
                    email = options[1];
                    User user = domain.getUser(email);
                    System.out.println(user);
                    break;
                //getAllUsers
                case 6:
                    gcode = Gcode.makeGcode(Integer.parseInt(options[1]));
                    User[] users = domain.getAllUsers(gcode);
                    System.out.println(Arrays.toString(users));
                    break;
                //getMatchedWithMe
                case 7:
                    email = options[1];
                    gcode = Gcode.makeGcode(Integer.parseInt(options[2]));
                    User[] matches = domain.getMatchedWithMe(email,gcode);
                    System.out.println(Arrays.toString(matches));
                    break;
                case 8:
                    System.out.println(helpText);
                    break;
                default:
                    System.out.println("Enter number between 0-8 inclusive");
                    break;
            }
        }
    }

    private void parseErrorMsg(){
        System.out.println("Invalid code, enter digit between 0 and 7 inclusive");
    }

    private void checkTermination(String input) {
        if (input.equals("-1")) {
            System.out.println("Goodbye");
            System.exit(0);
        }
    }

    private String next() throws IOException{
        System.out.println("enter '8' for help");
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
}
