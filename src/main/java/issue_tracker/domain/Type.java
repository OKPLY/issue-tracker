package issue_tracker.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(
            name = "name",
            unique = true,
            nullable = false
    )
    private String name;

    @OneToMany(mappedBy = "type")
    @JsonBackReference
    private List<Issue> issues;
}
