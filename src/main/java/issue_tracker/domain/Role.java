package issue_tracker.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(
            name = "name",
            unique = true,
            nullable = false
    )
    private String name;

    @ManyToMany(mappedBy = "roles")
    @JsonManagedReference
    private List<Permission> permissions;
}
