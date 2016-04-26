package domain.Repos;

import domain.Admin;
import domain.User;
import domain.interfaces.IUserRepo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oliv on 4/23/16.
 */
public class LocalUserRepo implements IUserRepo{

    private List<User> users = new ArrayList<>();
    private List<Admin> admins = new ArrayList<>();
    private final String SEPERATOR = File.separator;
    private final String PATH = "src"+SEPERATOR+"main"+SEPERATOR+"java"+SEPERATOR+"domain"+SEPERATOR+"SaveFiles"+SEPERATOR;
    private final String USERSFILENAME = "Users.ser";
    private final String ADMINSFILENAME = "Admins.ser";

    public LocalUserRepo(){
        saveState(USERSFILENAME,users);
        saveState(ADMINSFILENAME,admins);
        readUsers();
        readAdmins();
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
            objectInStream = new ObjectInputStream(inStream); result = objectInStream.readObject();
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

    private void readUsers() {
        users = (List<User>) readState(USERSFILENAME);
    }

    private void readAdmins() {
        admins = (List<Admin>) readState(ADMINSFILENAME);
    }

    @Override
    public User getUser(String email) {
        for (User user : users) {
            String userEmail = user.getEmail();
            if (userEmail.equals(email)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public Admin getAdmin(String email) {
        for (Admin admin : admins) {
            String userEmail = admin.getEmail();
            if (userEmail.equals(email)) {
                return admin;
            }
        }
        return null;
    }

    @Override
    public void createUser(String email, String name, String pw) {
        User user = new User(email,name,pw);
        users.add(user);
        saveState(USERSFILENAME,users);
    }

    @Override
    public void createAdmin(String email, String name, String pw) {
        Admin admin = new Admin(email,name,pw);
        admins.add(admin);
        saveState(ADMINSFILENAME,admins);
    }

}
