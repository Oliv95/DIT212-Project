package domain.Factory;

import domain.interfaces.ICourse;
import domain.interfaces.ICourseRepo;
import domain.interfaces.IUser;
import domain.interfaces.IUserRepo;

/**
 * Created by oliv on 4/23/16.
 */
public class DomainFactory {

    //TODO fix some kind of setup so that state is ok
    private static final ICourseRepo ActiveCourseRepo = null;
    private static final IUserRepo ActiveUserRepo     = null;
    private static final ICourse course               = null;
    private static final IUser user                   = null;

    public static ICourse getCourseDomain() {
        return course;
    }

    public static IUser getUserDomain() {
        return user;
    }

}
