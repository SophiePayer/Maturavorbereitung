package at.spengergasse.spengermed.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "con_condition")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Condition extends DomainResource{

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "i_con_id", referencedColumnName = "id", nullable = true)
    private List<Identifier> identifiers = new ArrayList<>();

    //Problem JonColumn Name??
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "con_cc_clinstat_id", referencedColumnName = "id", nullable = true)
    private CodeableConcept clinicalStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "con_cc_veristat_id", referencedColumnName = "id", nullable = true)
    private CodeableConcept verificationStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "con_cc_serverity_id", referencedColumnName = "id", nullable = true)
    private CodeableConcept severity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "con_cc_code_id", referencedColumnName = "id", nullable = true)
    private CodeableConcept code;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cc_con_category_id", referencedColumnName = "id", nullable = true)
    private List<CodeableConcept> category = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cc_con_bodysite_id", referencedColumnName = "id", nullable = true)
    private List<CodeableConcept> bodySite = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "con_re_subject_id", referencedColumnName = "id", nullable = false)
    private Reference subject;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "con_re_encounter_id", referencedColumnName = "id", nullable = true)
    private Reference encounter;
}
