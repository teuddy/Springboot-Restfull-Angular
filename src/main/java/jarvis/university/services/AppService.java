package jarvis.university.services;

import com.google.common.collect.Lists;
import jarvis.university.app.Course;
import jarvis.university.app.Teacher;
import jarvis.university.controllers.JarvisController;
import jarvis.university.repositories.CourseRepository;
import jarvis.university.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Service
public class AppService {

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private CourseRepository courseRepository;


    public ResponseEntity<?> createTeacher(Teacher teacher){
        boolean avaible = teacherRepository.findByUsername(teacher.getUsername()).isPresent();
        if(!avaible){
            Teacher guarda = teacherRepository.save(teacher);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setLocation(ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(guarda.getUsername()).toUri());
            return new ResponseEntity<>(headers,HttpStatus.CREATED);
        }
        throw new UserNameAlreadyInUse(teacher.getUsername());
    }



    public Teacher getTeacher(String username){
        return teacherRepository.findByUsername(username)
                .orElseThrow(()-> new NotFound(username));
    }



    public List<Teacher> returnAllTeachers(){
      List<Teacher> lista = Lists.newArrayList(teacherRepository.findAll());
      lista.forEach(teacher->{
          Link ordersLink = linkTo(methodOn(JarvisController.class)
                  .getTeacher(teacher.getUsername())).withSelfRel();
          teacher.add(ordersLink);
      });
      return lista;
    }



    public ResponseEntity<?> UpdateTeacher(String name , Teacher teacher){
        Teacher exist = teacherRepository.findByUsername(name).get();
        if(exist==null){
            throw new NotFound("the teachers you want to update dont");
        }else{
            exist = teacher;
            teacherRepository.save(exist);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }



    public ResponseEntity<?> addCourse(String username , Course course){
        return teacherRepository.findByUsername(username).map(teachers -> {
            Course resultado = courseRepository.save(new Course(course.getName(),course.getDescription(),teachers));
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{nombrecurso}")
                    .buildAndExpand(resultado.getName()).toUri()
                        );
            return new ResponseEntity<>(headers,HttpStatus.CREATED);
        }).orElseThrow(()-> new NotFound("username"));
    }

    public  ResponseEntity<?> getteacherscourse(String username){
        Teacher teacher = teacherRepository.findByUsername(username).orElseThrow(()-> new NotFound(username));
        List<Course> allcoursesfromCurrentUser = Lists.newArrayList(courseRepository.findByTeacher(teacher));
        return new ResponseEntity<>(allcoursesfromCurrentUser,HttpStatus.FOUND);

    }




    public ResponseEntity<?> getAllCourses(){
        List<Course> courses = Lists.newArrayList(courseRepository.findAll());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(courses,headers,HttpStatus.OK);
    }
}


@ResponseStatus(value = HttpStatus.IM_USED)
class UserNameAlreadyInUse extends RuntimeException{
    public UserNameAlreadyInUse(String name){
        super("username: " + name + " is already taken");
    }
}

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class NotFound extends RuntimeException {
    public NotFound(String name) {
        super("user: " + name + " not found");
    }

}