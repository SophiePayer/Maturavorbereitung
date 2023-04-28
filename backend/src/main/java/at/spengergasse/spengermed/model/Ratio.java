package at.spengergasse.spengermed.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="ra_ratio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ratio extends Element {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="ra_q_id", referencedColumnName = "id")
    private Quantity numerator;

   /* @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="ra_sq_id", referencedColumnName = "id")
    private SimpleQuantity denominator;*/


}
