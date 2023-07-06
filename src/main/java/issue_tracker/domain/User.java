package issue_tracker.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(
            name = "email",
            unique = true,
            nullable = false
    )
    private String email;

    @Column(
            name = "password",
            nullable = false
    )
    private String password;

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


    @OneToMany(mappedBy = "creator")
    @JsonBackReference
    private List<Issue> created_issues;

    @OneToMany(mappedBy = "resolver")
    @JsonBackReference
    private List<Issue> assigned_issues;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();
     public void addRole(Role role){
         roles.add(role);
     }
}
