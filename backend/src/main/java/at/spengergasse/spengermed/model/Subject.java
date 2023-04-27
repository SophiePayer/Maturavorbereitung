package at.spengergasse.spengermed.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "su_subject")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subject extends BackboneElement{

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ch_su_id", referencedColumnName = "id")
    private List<Characteristic> characteristic = new ArrayList<Characteristic>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "an_su_id", referencedColumnName = "id")
    private List<Annotation> note = new ArrayList<Annotation>();

}
