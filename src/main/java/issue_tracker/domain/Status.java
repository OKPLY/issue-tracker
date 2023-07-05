package issue_tracker.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Where(clause = "deleted=false")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(
            name = "name",
            unique = true,
            nullable = false
    )
    private String name;

    @JsonIgnore
    private boolean deleted = false;

    private LocalDateTime createdAt = LocalDateTime.now();
}
