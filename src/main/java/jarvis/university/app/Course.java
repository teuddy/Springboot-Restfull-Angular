package jarvis.university.app;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
public class Course {

    @Getter @GeneratedValue(generator = "increment") @GenericGenerator(name = "increment", strategy = "increment")
    @Id private Long identifier;
    @Getter @Setter @NotNull private String name;
    @Getter @Setter @NotNull private String description;
    @Getter(AccessLevel.PRIVATE) @ManyToOne private Teacher teacher;

    @JsonCreator
    public Course(@JsonProperty("Name") String name ,
                  @JsonProperty("Description") String description ,
                  @JsonProperty("Teacher")Teacher teacher){

        this.description = description;
        this.name = name;
        this.teacher= teacher;
    }


}
