package ac.app.contract.student.logic;

import cn.hyperchain.annotations.StoreField;
import cn.hyperchain.core.Logger;
import ac.app.contract.student.logic.entity.Student;
import ac.app.contract.student.logic.entity.StudentResult;
import cn.hyperchain.contract.BaseContract;
import cn.hyperchain.core.HyperList;
import cn.hyperchain.core.HyperMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentContract extends BaseContract implements IStudent {

    private Logger logger = Logger.getLogger(StudentContract.class);

    @StoreField(type = "HyperMap")
    private Map<String, Student> students = new HyperMap<>();
    @StoreField
    private List<String> ids = new HyperList<>();

    @Override
    public Boolean registerStudent(List<Student> students) {
        for (Student student : students) {
            String id = student.getId();
            // check whether the id exists
            if (this.students.containsKey(id)) {
                return false;
            } else {
                this.students.put(id, student);
                ids.add(id);
            }
        }
        return true;
    }

    @Override
    public Boolean changeStudent(Student student) {
        String id = student.getId();
        // check whether the id exists
        if (!this.students.containsKey(id)) {
            return false;
        }
        this.students.put(id, student);
        return true;
    }

    @Override
    public Student getStudent(String id) {
        if (this.students.containsKey(id)) {
            return this.students.get(id);
        }
        return null;
    }

    @Override
    public List<Student> getStudents(List<String> ids) {
        List<Student> students = new ArrayList<>(ids.size());
        for (String id : ids) {
            Student stu = this.students.get(id);
            students.add(stu);
        }
        return students;
    }

    @Override
    public StudentResult isContains(Student student) {
        StudentResult studentResult;
        if (ids.contains(student.getId())) {
            studentResult = new StudentResult(200, "true");
        } else {
            studentResult = new StudentResult(400, "false");
        }
        return studentResult;
    }

    @Override
    public void onCreated() {
        logger.debug("HOOK: contract create");
    }

    @Override
    public void onPreCommit() {
        logger.debug("HOOK: contract preCommit");
    }

    @Override
    public void onCommitted() {
        logger.debug("HOOK: contract onCommit");
    }
}
