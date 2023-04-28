package at.spengergasse.spengermed.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="in_ingredient")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ingredient extends BackboneElement {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="in_cr_id", referencedColumnName = "id", nullable = true) //eigentlich false
    private CodeableReference item;

    @Column(name="in_isactive")
    private boolean isActive;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="in_ra_id", referencedColumnName = "id")
    private Ratio strengthRatio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="in_cc_id", referencedColumnName = "id")
    private CodeableConcept strengthCC;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="in_q_id", referencedColumnName = "id")
    private Quantity strengthQuantity;
}
