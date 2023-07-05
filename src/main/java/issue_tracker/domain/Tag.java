package issue_tracker.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Where(clause = "deleted=false")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(
            name = "name",
            unique = true,
            nullable = false
    )
    private String name;

    @ManyToMany(mappedBy = "tags")
    @JsonBackReference
    private List<Issue> issues;

    @JsonIgnore
    private boolean deleted = false;

    private LocalDateTime createdAt = LocalDateTime.now();
}
