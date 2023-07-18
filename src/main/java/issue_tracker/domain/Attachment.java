package issue_tracker.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "url",
            nullable = false
    )
    private String url;

    @Column(
            name = "created_at",
            nullable = false
    )
    @CreationTimestamp
    @JsonIgnore
    private LocalDateTime createdAt;

    @Column(
            name = "updated_at",
            nullable = false
    )
    @UpdateTimestamp
    @JsonIgnore
    private LocalDateTime updatedAt;

    @Column(
            name = "deleted",
            nullable = false,
            columnDefinition = "boolean default false"
    )
    @JsonIgnore
    private Boolean deleted = false;
}

