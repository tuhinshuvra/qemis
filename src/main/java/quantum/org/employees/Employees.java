package quantum.org.employees;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="employees")
@Data
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column( length = 45, nullable = false, name = "first_name")
    private String firstName;

    @Column( length = 45, nullable = false, name = "last_name")
    private String lastName;

    @Column( length = 45, nullable = false, name = "designation")
    private String designation;

    @Column( length = 45, nullable = false, name = "salary")
    private String salary;

}

