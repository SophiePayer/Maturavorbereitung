package at.spengergasse.spengermed.model;

import lombok.*;
import org.aspectj.apache.bcel.classfile.Code;

import javax.persistence.*;

@Entity
@Table(name="cr_codeablereference")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CodeableReference extends Element{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cr_cc_id", referencedColumnName = "id")
    private CodeableConcept concept;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cr_re_id", referencedColumnName = "id")
    private Reference reference;

}
