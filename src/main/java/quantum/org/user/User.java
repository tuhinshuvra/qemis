package quantum.org.user;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column( length = 45, nullable = false)
    private String password;

    @Column( length = 45, nullable = false, name = "first_name")
    private String firstName;

    @Column( length = 45, nullable = false, name = "last_name")
    private String lastName;

}

