package models.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;

@Entity
@Table(name = "user_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Indexed
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="userId")
    private int userId;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name = "role")
    private RoleEnum role;

    @Column(name = "status")
    private StatusEnum statusEnum;

}
