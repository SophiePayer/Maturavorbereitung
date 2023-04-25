package at.spengergasse.spengermed.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "en_encounter")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Encounter extends DomainResource{

    public enum StatusCode{
        planned("planned"),
        arrived("arrived"),
        triaged("triaged"),
        inprocess("in-process"),
        onleave("on-leave"),
        finished("finished"),
        cancelled("cancelled"),
        enteredinerror("entered-in-error"),
        unknown("unknown");

        private String value;
        private StatusCode(String value){
            this.value = value;
        }
        public String toString(){
            return this.value;
        }
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "i_en_id", referencedColumnName = "id", nullable = true)
    private List<Identifier> identifier = new ArrayList<>();

    @NotNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "en_status", nullable = false)
    private StatusCode status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "sh_en_id", referencedColumnName = "id", nullable = true)
    private List<StatusHistory> statusHistory = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cc_en_id", referencedColumnName = "id", nullable = true)
    private List<CodeableConcept> type = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "en_re_id", referencedColumnName = "id", nullable = true)
    private Reference subject;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "re_en_episodeOfCare_id", referencedColumnName = "id", nullable = true)
    private List<Reference> episodeOfCare = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "par_en_id", referencedColumnName = "id", nullable = true)
    private List<Participant> participant = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "re_en_appointment_id", referencedColumnName = "id", nullable = true)
    private List<Reference> appointment = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "en_pe_id", referencedColumnName = "id", nullable = true)
    private Period period;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "re_en_reasonReference_id")
    private List<Reference> reasonReference = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "dia_en_id", referencedColumnName = "id", nullable = true)
    private List<Diagnosis> diagnosis = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "en_re_partof_id", referencedColumnName = "id", nullable = true)
    private Reference partOf;
}
