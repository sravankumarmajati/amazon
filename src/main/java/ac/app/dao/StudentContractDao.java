package ac.app.dao;

import ac.app.contract.student.logic.entity.Student;
import ac.app.contract.student.logic.entity.StudentResult;

import java.util.List;

/**
 * Author: chenquan
 * Date: 2018/11/9
 * Package: ac.app.dao
 * Description:
 */
public interface StudentContractDao {
    boolean registerStudent(List<Student> students);

    boolean changeStudent(Student student);

    Student getStudent(String id);

    List<Student> getStudents(List<String> ids);

    StudentResult isContains(Student student);
}
