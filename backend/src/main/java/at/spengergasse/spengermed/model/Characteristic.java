package at.spengergasse.spengermed.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ch_characteristic")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Characteristic extends BackboneElement{
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "co_ch_id", referencedColumnName = "id", nullable = false)
    private Coding code;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cc_ch_id", referencedColumnName = "id", nullable = false)
    private CodeableConcept valueCodeableConcept;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "re_ch_id", referencedColumnName = "id", nullable = false)
    private Reference valueReference;
}
