package at.spengergasse.spengermed.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ra_riskassessment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RiskAssessment extends DomainResource{
    public enum StatusCode{
        registered, @JsonProperty("final") FINAL, amended, preliminary
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "i_ra_id", referencedColumnName = "id", nullable = true)
    private List<Identifier> identifier = new ArrayList<Identifier>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ra_re_id", referencedColumnName = "id", nullable = true)
    private Reference parent;

    @Enumerated(EnumType.STRING)
    @Column(name = "ra_status", nullable = false)
    private StatusCode status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pr_ra_id", referencedColumnName = "id")
    private List<Prediction> predictions;


}
