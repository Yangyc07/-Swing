package utils;

import com.yang.dao.StudentDao;

public class StudentFactory {

    private StudentFactory() {

    }

    public static StudentDao getStudentDao() {
        // TODO Auto-generated method stub
        return new StudentDao();
    }
}
