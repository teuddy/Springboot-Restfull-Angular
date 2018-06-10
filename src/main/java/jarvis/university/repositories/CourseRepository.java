package jarvis.university.repositories;

import jarvis.university.app.Course;
import jarvis.university.app.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;



public interface CourseRepository extends CrudRepository<Course, Long> {
    Iterable<Course> findByTeacher(Teacher teacher);

}
