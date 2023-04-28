package at.spengergasse.spengermed.model;

import lombok.*;
import org.hibernate.annotations.Cache;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.crypto.dsig.CanonicalizationMethod;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="obs_observation")
public class Observation extends DomainResource {
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "i_obs_id", referencedColumnName = "id", nullable = true)
    private List<Identifier> identifier = new ArrayList<Identifier>();

    @Column(name = "obs_canonical", nullable = false)
    private String canonical;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "obs_re_id", referencedColumnName = "id", nullable = true)
    private Reference instantiatesReference;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "obs_obsdef_id", referencedColumnName = "id")
    private ObservationDefinition observationDefinition;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ob_obs_basedon", referencedColumnName = "id")
    private List<Reference> basedon = new ArrayList<Reference>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "obs_tr_id", referencedColumnName = "id", nullable = true)
    private List<TriggeredBy> triggeredby = new ArrayList<TriggeredBy>();

}
