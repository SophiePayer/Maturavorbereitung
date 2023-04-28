package at.spengergasse.spengermed.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "obsd_observationdefinition")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ObservationDefinition extends DomainResource{
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "i_obsd_id", referencedColumnName = "id", nullable = true)
    private List<Identifier> identifier = new ArrayList<Identifier>();
}
