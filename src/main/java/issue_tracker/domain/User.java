package issue_tracker.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @OneToMany(mappedBy = "creator")
    @JsonManagedReference
    private List<Issue> created_issues;

    @OneToMany(mappedBy = "resolver")
    @JsonManagedReference
    private List<Issue> assigned_issues;

    @ManyToMany()
    private List<Role> roles;
}
