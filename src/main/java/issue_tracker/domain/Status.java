package issue_tracker.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(
            name = "name",
            unique = true,
            nullable = false
    )
    private String name;
}
