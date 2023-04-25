package at.spengergasse.spengermed.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "me_measure")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Measure extends DomainResource{

    @Column(name = "me_url")
    private String url;

    @OneToMany(cascade = CascadeType.ALL) //n-Seite bei Identifier (daher Identifier = FK)
    @JoinColumn(name = "i_me_id", referencedColumnName = "id", nullable = true)
    private List<Identifier> identifier = new ArrayList<Identifier>();

    @Column(name = "me_version")
    private String version;

    @Column(name = "me_date")
    private LocalDateTime date;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "me_cc_id", referencedColumnName = "id", nullable = true)
    private CodeableConcept subjectCodeableConcept;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "me_re_id", referencedColumnName = "id", nullable = true)
    private Reference subjectReference;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "me_gr_id", referencedColumnName = "id", nullable = true)
    private Group group;

}
