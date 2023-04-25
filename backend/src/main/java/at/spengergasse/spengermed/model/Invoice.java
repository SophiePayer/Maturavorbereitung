package at.spengergasse.spengermed.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="in_invoice")
@Builder
public class Invoice extends DomainResource{

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "li_in_id", referencedColumnName = "id", nullable = true)
    private List<LineItem> lineitem = new ArrayList<LineItem>();

}
