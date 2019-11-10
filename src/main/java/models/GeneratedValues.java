package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.NaturalId;
import javax.persistence.*;

@Entity
@Table (name = "generated_values")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Immutable
public class GeneratedValues {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="generatedValueId", updatable = false, nullable = false)
    private int generatedValueId;

    @NaturalId
    @Column(name="generatedValue")
    private String generatedValue;

}
