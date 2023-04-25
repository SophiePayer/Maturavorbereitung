package at.spengergasse.spengermed.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="li_lineitem")
@Builder
public class LineItem extends BackboneElement{

    public enum TypeCode{
        base, subcharge, deduction, discount, tax, informational
    }

    @Min(1)
    @Column(name = "li_sequence", nullable = true)
    private Integer sequence;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "li_re_id", referencedColumnName = "id", nullable = true)
    private Reference chargeItemReference;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "li_cc_id", referencedColumnName = "id", nullable = true)
    private CodeableConcept chargeItemCodableConcept;

    @Enumerated(EnumType.STRING)
    @Column(name = "li_type", nullable = false)
    private TypeCode type;

}
