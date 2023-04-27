package at.spengergasse.spengermed.model;

import lombok.*;

import javax.persistence.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ev_evidenceReport")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EvidenceReport extends DomainResource{
    public enum StatusCode{
        draft, active, retired, unknown
    }

    @Column(name = "ev_url")
    private URI url;

    @Enumerated(EnumType.STRING)
    @Column(name = "ev_status", nullable = false)
    private EvidenceReport.StatusCode status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "uc_ev_id", referencedColumnName = "id")
    private List<UsageContext> useContext = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "i_ev_id", referencedColumnName = "id")
    private List<Identifier> identifier = new ArrayList<Identifier>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "su_ev_id", referencedColumnName = "id",nullable = false)
    private Subject subject;
}
