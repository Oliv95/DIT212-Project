package se.ice.client.utility;

/**
 * Created by niclas on 2016-04-19.
 */
public class MockupServerSession {

    public static MockupServer server = new MockupServer();

    // Session specific data
    String email = "name@mail.com";
    String name = "name";
    String password = "password";

    public MockupServerSession(){

        server.createAdmin(email,name,password);

    }

    

}
