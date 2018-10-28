package pk.edu.suk.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Arshay on 10/27/2018.
 */

@Data
@Entity
@EqualsAndHashCode
@Table(name = "user_table")
@ToString
public class User {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userFirstName;
    private String userLastName;

    private String userEmail;
    private String userPassword;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "roleId"))
    private List<Role> roles;

    public User(){

    }

    public User(String userFirstName, String userLastName, String userEmail, String userPassword, List<Role> roles) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.roles = roles;
    }

}
