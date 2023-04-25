package at.spengergasse.spengermed.model;

import jdk.jfr.Percentage;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;

@Entity
@Table(name = "pr_prediction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Prediction extends BackboneElement{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pr_cc_id", referencedColumnName = "id", nullable = true)
    private CodeableConcept outcome;

    @Max(100)
    @Column(name = "pr_prohabilitydecimal", nullable = true)
    private Integer prohabilityDecimal;

    @Column(name = "pr_prohabilityrange", nullable = true)
    private Integer prohabilityRange;
}
