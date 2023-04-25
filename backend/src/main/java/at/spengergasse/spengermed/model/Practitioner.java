package at.spengergasse.spengermed.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pra_practitioner")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Practitioner extends DomainResource{

    public enum GenderCode{
        male, female, other, unknown
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "i_pra_id", referencedColumnName = "id", nullable = true)
    private List<Identifier> identifier = new ArrayList<Identifier>();

    @Column(name = "pra_active")
    private Boolean active;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "hn_pra_id", referencedColumnName = "id", nullable = true)
    private List<HumanName> name = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cp_pra_id", referencedColumnName = "id", nullable = true)
    private List<ContractPoint> telecom = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "a_pra_id", referencedColumnName = "id", nullable = true)
    private List<Address> address = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "pra_gender")
    private GenderCode gender;

    @Column(name = "pra_birthdate")
    private LocalDate birthdate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "at_pra_id", referencedColumnName = "id", nullable = true)
    private List<Attachment> photo = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "be_pra_id", referencedColumnName = "id", nullable = true)
    private List<Qualification> qualification = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cc_pra_id", referencedColumnName = "id", nullable = true)
    private List<CodeableConcept> communication = new ArrayList<>();
}
