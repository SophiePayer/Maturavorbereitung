package at.spengergasse.spengermed.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BackboneElement extends Element{

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ex_be_id", referencedColumnName = "id", nullable = true)
    private List<Extension> modifierExtension = new ArrayList<>();
}
