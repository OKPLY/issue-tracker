package issue_tracker.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Where;

import java.util.List;

@Data
@Entity
@Where(clause = "deleted=false")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(
            name = "name",
            unique = true,
            nullable = false
    )
    private String name;

    @OneToMany(mappedBy = "type")
    @JsonBackReference
    private List<Issue> issues;

    @JsonIgnore
    @Column(columnDefinition = "boolean default false")
    private boolean deleted;
}
