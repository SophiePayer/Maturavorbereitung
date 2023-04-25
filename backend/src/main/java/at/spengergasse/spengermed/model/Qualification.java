package at.spengergasse.spengermed.model;

import at.spengergasse.spengermed.model.BackboneElement;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "qu_qualification")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Qualification extends BackboneElement {

    //identifer
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "i_qu_id", referencedColumnName = "id", nullable = true)
    private List<Identifier> identifier = new ArrayList<>();

    //code
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "qu_cc_id", referencedColumnName = "id", nullable = false)
    private CodeableConcept code;

    //period
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "qu_pe_id", referencedColumnName = "id", nullable = true)
    private Period period;

    //issuer
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "qu_re_id", referencedColumnName = "id", nullable = true)
    private Reference reference;

}
