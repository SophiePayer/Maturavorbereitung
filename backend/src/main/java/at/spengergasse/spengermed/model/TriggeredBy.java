package at.spengergasse.spengermed.model;

import at.spengergasse.spengermed.model.BackboneElement;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tr_triggeredby")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TriggeredBy extends BackboneElement{
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tr_re_id", referencedColumnName = "id", nullable = true)
    private Reference observation;

}
