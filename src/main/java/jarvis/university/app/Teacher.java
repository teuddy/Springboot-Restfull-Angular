package jarvis.university.app;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@JsonIgnoreProperties({"password"})
public class Teacher extends ResourceSupport{

    @Id @GeneratedValue private Long id;
    @OneToMany(mappedBy = "teacher")
    @Getter @Setter private List<Course> courses;
    @Setter @Getter private String username;
    @JsonIgnore @Getter(AccessLevel.PRIVATE) private String password;


    @JsonCreator
    public Teacher(@JsonProperty("Username") String username
            ,@JsonProperty("Password") String password){

        this.username = username;
        this.password = password;
    }

    @JsonCreator
    Teacher(){

    }

}

