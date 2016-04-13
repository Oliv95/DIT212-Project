package se.ice.client.utility;

/**
 * Created by Simon on 2016-04-13.
 */
public interface DataRequest {

    int createUser(String email);

    int createAdmin(String email);

    boolean createCourse(String name, int admin);

    boolean joinCourse(int userId, int courseId);

    void matchRequest(int senderId, int recepiantId, int courseId);

    String getUser(int userId);

    int[] getAllUsers(int courseId);

    int[] getAllMatchedWithMe(int userId);

}
