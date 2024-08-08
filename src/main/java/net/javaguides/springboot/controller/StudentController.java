package net.javaguides.springboot.controller;

import net.javaguides.springboot.SpringbootRestApiApplication;
import net.javaguides.springboot.bean.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    // http://localhost:8080/student
    @GetMapping("student")
    public Student getStudent(){
        Student student = new Student(
                1,
                "JorgeL",
                "Soriano"
        );
        return student;
    }

    // http://localhost:8080/students
    @GetMapping("students")
    public List<Student> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1,"Jorge", "Soriano"));
        students.add(new Student(2,"Luis", "Castillo"));
        students.add(new Student(3,"Alejandro", "Castillo"));
        students.add(new Student(4,"Jose M", "Castillo"));

        return students;
    }

    //Spring BOOT REST API with Path Variable
    // {id} - URI template variable
    // http://localhost:8080/students/1
    @GetMapping("students/{id}/{first-name}/{last-name}")
    public Student studentPathVariable(@PathVariable("id") int studentId,
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName){
        return new Student(studentId,firstName,lastName);
    }


    // Spring boot REST API with Request Param
    // http://localhost:8080/students/query?id=1&firstName=Jorge&lastName=Soriano
    @GetMapping("students/query")
    public Student studentRequestVariable(@RequestParam int id,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName){
        return new Student(id,firstName,lastName);
    }
}
