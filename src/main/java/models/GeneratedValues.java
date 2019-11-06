package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table (name = "generated_values")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneratedValues {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="generatedValueId")
    private String generatedValueId;

    @Column(name="generatedValue")
    private String generatedValue;

}
