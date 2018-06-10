package jarvis.university.controllers;

import jarvis.university.app.Course;
import jarvis.university.app.Teacher;
import jarvis.university.services.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class JarvisController {

    @Autowired
    private AppService appService;


    @PostMapping(value = "/teachers", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTeacher(@RequestBody Teacher teacher){
        return appService.createTeacher(teacher);
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/teachers" , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Teacher> getAllTeachers(){
        return appService.returnAllTeachers();
    }


    @GetMapping(value = "/teachers/{username}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public Teacher getTeacher(@PathVariable String username){
        return appService.getTeacher(username);
    }

    @PutMapping(value = "/teachers/{username}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> UpdateTeacher(@PathVariable String username , @RequestBody Teacher teacher){
       return appService.UpdateTeacher(username, teacher);
    }

   @PostMapping(value = "/teachers/{teacher}/courses", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addMeACourse(@PathVariable String teacher , @RequestBody Course course){
        return appService.addCourse(teacher,course);
    }

    @GetMapping(value = "/teachers/{teacher}/courses", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> returnTeacherCOurses(@PathVariable String teacher){
        return appService.getteacherscourse(teacher);
    }

    
    @GetMapping(value = "/courses")
    public ResponseEntity<?> getcoursesFromCurrentUser(){
        return appService.getAllCourses();
    }
}
