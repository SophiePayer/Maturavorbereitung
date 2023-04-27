package at.spengergasse.spengermed.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "uc_usageContext")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsageContext extends Element{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "co_uc_id", referencedColumnName = "id", nullable = false)
    private Coding code;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cc_uc_id", referencedColumnName = "id", nullable = false)
    private CodeableConcept valueCodeableConcept;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "re_uc_id", referencedColumnName = "id", nullable = false)
    private Reference valueReference;
}
