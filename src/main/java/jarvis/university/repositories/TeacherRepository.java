package jarvis.university.repositories;

import jarvis.university.app.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;


public interface TeacherRepository extends CrudRepository<Teacher,Long> {
    Optional<Teacher> findByUsername(String username);
}
