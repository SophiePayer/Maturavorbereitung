package at.spengergasse.spengermed.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "perf_performer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Performer extends BackboneElement{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "perf_cc_id", referencedColumnName = "id", nullable = true)
    private CodeableConcept function;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "perf_re_actor", referencedColumnName = "id", nullable = true)
    private Reference actor;
}
