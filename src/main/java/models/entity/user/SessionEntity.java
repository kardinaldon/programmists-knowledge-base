package models.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "session_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Immutable
public class SessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sessionId", updatable = false, nullable = false)
    private long sessionId;

    @NaturalId
    @Column(name = "sessionValue")
    private String sessionValue;

}
