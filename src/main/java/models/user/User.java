package models.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Entity
@Table(name = "user_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="userId", updatable = false, nullable = false)
    private int userId;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name = "role")
    private RoleEnum role;

    @Column(name = "status")
    private StatusEnum statusEnum;

}
