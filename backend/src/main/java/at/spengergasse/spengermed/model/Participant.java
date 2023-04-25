package at.spengergasse.spengermed.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "par_participant")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Participant extends BackboneElement{

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cc_par_id", referencedColumnName = "id", nullable = true)
    private List<CodeableConcept> type = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "par_pe_id", referencedColumnName = "id", nullable = true)
    private Period period;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "par_re_id", referencedColumnName = "id", nullable = true)
    private Reference individual;
}
