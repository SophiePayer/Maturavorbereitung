package at.spengergasse.spengermed.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "gr_group")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Group extends BackboneElement{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gr_cc_id", referencedColumnName = "id", nullable = true)
    private CodeableConcept code;

    @Column(name = "gr_description")
    private String description;

}
