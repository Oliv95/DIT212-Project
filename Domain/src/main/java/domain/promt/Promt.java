package domain.promt;

import domain.App;
import domain.User;
import domain.interfaces.Domain;
import domain.util.Gcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by oliv on 4/14/16.
 */
public class Promt {

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final String prompt = "> ";
    private final String helpText   = "0 CreateUser email,name,password" + System.lineSeparator() +
                                      "1 CreateAdmin email,name,password" + System.lineSeparator() +
                                      "2 createCourse courseName, email" + System.lineSeparator() +
                                      "3 joinCourse gCode, email" + System.lineSeparator() +
                                      "4 matchRequest sender, receiver,gCode" + System.lineSeparator() +
                                      "5 getUser email" + System.lineSeparator() +
                                      "6 getAllUsers gCode" + System.lineSeparator() +
                                      "7 getMatchedWithMe email gCode" + System.lineSeparator() +
                                      "exit for exit" + System.lineSeparator();

    private final String separator = " ";
    private final String terminationSymbol = "exit";
    private final Domain domain;
    /*
    Needed since you need to be able to get a gcode from a string
    Entries are made with the return value of createCourse
     */

    private final Map<String,Gcode> gcodeMap = new LinkedHashMap<>();

    public Promt(Domain domain){
        this.domain = domain;
    }

    private void start() throws IOException{
        //main loop
        while (true){
            String input = next().trim();
            if (input.equals(terminationSymbol)) {
                System.out.println("Goodbye");
                break;
            }
            int code;
            //get the code, needs to be a integer
            try {
                code = Integer.parseInt(input.split(separator)[0]);
            } catch (NumberFormatException e) {
                parseErrorMsg();
                //loop until integer is entered
                continue;
            }
            String[] options = input.split(separator);
            String email;
            String name;
            String password;
            String result;
            String courseName;
            Gcode gCode;
            //ugly switch statement that maps the code to a method in the domain interface
            switch (code) {
                //createUser
                case 0:
                    if (!checkArrayLength(4,options,"CreateUser")) {
                        break;
                    }
                    email = options[1];
                    name = options[2];
                    password = options[3];
                    result = domain.createUser(email,name,password);
                    if (result.equals("")) {
                        System.out.println("Could not create user with email " + email);
                        break;
                    }
                    System.out.println("Created User with email " + result);
                    break;
                //createAdmin
                case 1:
                    if (!checkArrayLength(4,options,"CreateAdmin")) {
                        break;
                    }
                    email = options[1];
                    name = options[2];
                    password = options[3];
                    result = domain.createAdmin(email,name,password);
                    //empty string is returned if method fails
                    if (result.equals("")) {
                        System.out.println("Could not create admin with email " + email);
                    }
                    System.out.println("Created User with email " + result);
                    break;
                //createCourse
                case 2:
                    if (!checkArrayLength(3,options,"CreateCourse")) {
                        break;
                    }
                    courseName = options[1];
                    email = options[2];
                    gCode = domain.createCourse(courseName,email);
                    //null returned if method fails
                    if (gCode == null) {
                        System.out.println("Error creating course " + courseName + " with user " + email);
                        break;
                    }
                    //make entry in gCode map so that a string can be translated to gCode later
                    gcodeMap.put(String.valueOf(gCode.getId()),gCode);
                    System.out.println("Created course " + courseName + " with code " + gCode);
                    break;
                //joinCourse
                case 3:
                    if (!checkArrayLength(3,options,"joinCourse")) {
                        break;
                    }
                    gCode = gcodeMap.get(options[1]);
                    //null returned if Gcode does not exist in map
                    if (gCode == null) {
                        System.out.println("No course with code " + gCode);
                        break;
                    }
                    email = options[2];
                    boolean joined = domain.joinCourse(gCode,email);
                    if (joined) {
                        System.out.println(email + " joined course " + gCode);
                    }
                    else {
                        System.out.println(email + " did not join course " + gCode);
                    }
                    break;
                //matchRequest
                case 4:
                    if (!checkArrayLength(4,options,"MatchRequest")) {
                        break;
                    }
                    String sender = options[1];
                    String receiver = options[2];
                    gCode = gcodeMap.get(options[1]);
                    //null returned if Gcode does not exist in map
                    if (gCode == null) {
                        System.out.println("Course with code " + gCode + " does not exist");
                        break;
                    }
                    domain.matchRequest(sender,receiver,gCode);
                    System.out.println("Match request send from " + sender +  " to  " + receiver);
                    break;
                //getUser
                case 5:
                    if (!checkArrayLength(2,options,"getUser")) {
                        break;
                    }
                    email = options[1];
                    User user = domain.getUser(email);
                    result = (user == null) ? ("No User with that email") : user.toString();
                    System.out.println(result);
                    break;
                //getAllUsers
                case 6:
                    if (!checkArrayLength(2,options,"getAllUsers")) {
                        break;
                    }
                    gCode = gcodeMap.get(options[1]);
                    if (gCode == null) {
                        System.out.println("Course with code " + gCode + " does not exist");
                        break;
                    }
                    User[] users = domain.getAllUsers(gCode);
                    System.out.println(Arrays.toString(users));
                    break;
                //getMatchedWithMe
                case 7:
                    if (!checkArrayLength(3,options,"getMatchedWithMe")) {
                        break;
                    }
                    email = options[1];
                    gCode = gcodeMap.get(options[2]);
                    if (gCode == null) {
                        System.out.println("Course with code " + gCode + " does not exist");
                        break;
                    }
                    User[] matches = domain.getMatchedWithMe(email,gCode);
                    System.out.println(Arrays.toString(matches));
                    break;
                //display help text
                case 8:
                    System.out.println(helpText);
                    break;
                //invalid code case
                default:
                    System.out.println("Enter number between 0-8 inclusive");
                    break;
            }
        }
    }

    /*
    Checks if and array is of proper length, prints error msg if array to short
     */
    private boolean checkArrayLength(int desiredLength,String[] array,String methodName){
        if ( array.length < desiredLength) {
            desiredLength--; //error msg should not include the method code as length
            System.out.println(methodName + " needs " + desiredLength + " arguments");
            return false;
        }
        return true;
    }

    /*
    prints a error msg for invalid code
     */
    private void parseErrorMsg(){
        System.out.println("Invalid code, enter digit between 0 and 7 inclusive");
    }

    /*
    Returns the next line typed at the console, also prints help text
     */
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
