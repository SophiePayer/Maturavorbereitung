package at.spengergasse.spengermed.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bu_bundle")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bundle extends Resource {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bu_i_id", referencedColumnName = "id", nullable = true)
    private Identifier identifer;

    @Min(0)
    @Column(name = "bu_total")
    private Integer total;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "lk_bu_id", referencedColumnName = "id", nullable = true)
    private List<Link> links = new ArrayList<>();


}
