package ac.app.controller;

import ac.app.base.constant.Code;
import ac.app.base.response.BasicResult;
import ac.app.contract.student.logic.entity.Student;
import ac.app.contract.student.logic.entity.StudentResult;
import ac.app.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@Api(value = "student", description = "学生信息 API")
@RequestMapping(value = "/v1")
public class StudentAPIController {

    private StudentService studentService;

    @Autowired
    public StudentAPIController(StudentService studentService) {
        this.studentService = studentService;
    }

    @ApiOperation(value = "注册学生", notes = "", response = Void.class, tags = {}, position = 0)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Boolean.class) })
    @RequestMapping(value = "/reg_student",
            produces = { "application/json" },
            method = RequestMethod.POST)
    public ResponseEntity<BasicResult> registerStudent(
            @ApiParam(value = "学生ID", required = true) @RequestBody Student student
    ) {
        boolean res = studentService.registerStudent(student);
        BasicResult<Boolean> br = new BasicResult<>(Code.SUCCESS, res);
        return new ResponseEntity<>(br, HttpStatus.OK);
    }

    @ApiOperation(value = "修改学生信息", notes = "", response = Void.class, tags = {}, position = 1)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Boolean.class) })
    @RequestMapping(value = "/change_student",
            produces = { "application/json" },
            method = RequestMethod.POST)
    public ResponseEntity<BasicResult> changeStudent(
            @ApiParam(value = "学生ID", required = true) @RequestBody Student student
    ) {
        boolean res = studentService.changeStudent(student);
        BasicResult<Boolean> br = new BasicResult<>(Code.SUCCESS, res);
        return new ResponseEntity<>(br, HttpStatus.OK);
    }

    @ApiOperation(value = "获取学生信息", notes = "", response = Void.class, tags = {}, position = 2)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Boolean.class) })
    @RequestMapping(value = "/get_student",
            produces = { "application/json" },
            method = RequestMethod.POST)
    public ResponseEntity<BasicResult> getStudent(
            @ApiParam(value = "学生ID", required = true) @RequestBody String id
    ) {
        Student res = studentService.getStudent(id);
        BasicResult<Student> br = new BasicResult<>(Code.SUCCESS, res);
        return new ResponseEntity<>(br, HttpStatus.OK);
    }

    @ApiOperation(value = "批量获取学生信息", notes = "", response = Void.class, tags = {}, position = 3)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Boolean.class) })
    @RequestMapping(value = "/get_students",
            produces = { "application/json" },
            method = RequestMethod.POST)
    public ResponseEntity<BasicResult> getStudents(
            @ApiParam(value = "学生IDs", required = true) @RequestBody List<String> ids
            ) {
        List<Student> res = studentService.getStudents(ids);
        BasicResult<List> br = new BasicResult<>(Code.SUCCESS, res);
        return new ResponseEntity<>(br, HttpStatus.OK);
    }

    @ApiOperation(value = "是否包含学生", notes = "", response = Void.class, tags = {}, position = 4)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Boolean.class) })
    @RequestMapping(value = "/is_contains",
            produces = { "application/json" },
            method = RequestMethod.POST)
    public ResponseEntity<BasicResult> idContains(
            @ApiParam(value = "学生ID", required = true) @RequestBody Student student
    ) {
        StudentResult res = studentService.isContains(student);
        BasicResult<StudentResult> br = new BasicResult<>(Code.SUCCESS, res);
        return new ResponseEntity<>(br, HttpStatus.OK);
    }
}
