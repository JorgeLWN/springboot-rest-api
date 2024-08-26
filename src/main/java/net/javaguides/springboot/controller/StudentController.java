package net.javaguides.springboot.controller;

import net.javaguides.springboot.SpringbootRestApiApplication;
import net.javaguides.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    // http://localhost:8080/student
    @GetMapping("student")
    public ResponseEntity<Student> getStudent(){
        Student student = new Student(
                1,
                "JorgeL",
                "Soriano"
        );
        //return new ResponseEntity<>(student, HttpStatus.OK);
        return  ResponseEntity.ok()
                .header("custom-header", "jorge")
                .body(student);
    }

    // http://localhost:8080/students
    @GetMapping
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1,"Jorge", "Soriano"));
        students.add(new Student(2,"Luis", "Castillo"));
        students.add(new Student(3,"Alejandro", "Castillo"));
        students.add(new Student(4,"Jose M", "Castillo"));

        return ResponseEntity.ok(students);
    }

    //Spring BOOT REST API with Path Variable
    // {id} - URI template variable
    // http://localhost:8080/students/1
    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId,
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName){
        Student student=  new Student(studentId,firstName,lastName);
        return ResponseEntity.ok(student);
    }


    // Spring boot REST API with Request Param
    // http://localhost:8080/students/query?id=1&firstName=Jorge&lastName=Soriano
    @GetMapping("query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int id,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName){
        Student student = new Student(id,firstName,lastName);
        return ResponseEntity.ok(student);
    }
    // Spring boot REST API that handle HTTP POST Request
    // @RequestMapping and @RequestBody
    @PostMapping("students/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstname());
        System.out.println(student.getLastname());
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }
    // Spring boot REST API that handle HTTP PUT Request - updating existing resource
    @PutMapping("{id}/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student,@PathVariable("id") int studentId){
        System.out.println(student.getFirstname());
        System.out.println(student.getLastname());
        return ResponseEntity.ok(student);
    }
    // Spring boot RESTAPI that handles HTTP DELETE Request -deleting the existing resourse
    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId){
        System.out.println(studentId);
        return ResponseEntity.ok("Student delete successfully!");
    }
}
