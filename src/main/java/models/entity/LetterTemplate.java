package models.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table (name = "letter_template")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class LetterTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "letterId", updatable = false, nullable = false)
    private int letterId;

    @Column(name = "letterHeader")
    private String letterHeader;

    @Column(name = "letterText")
    private String letterText;
}
