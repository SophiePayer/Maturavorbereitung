package at.spengergasse.spengermed.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "dia_diagnosis")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Diagnosis extends BackboneElement{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dia_re_id", referencedColumnName = "id", nullable = false)
    private Reference condition;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dia_cc_id", referencedColumnName = "id", nullable = true)
    private CodeableConcept use;

    @Min(1)
    @Column(name = "dia_rank", nullable = true)
    private Integer rank;
}
