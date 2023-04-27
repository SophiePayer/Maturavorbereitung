package at.spengergasse.spengermed.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name="med_medication")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Medication extends DomainResource{

    public enum StatusCode{
        //active("active"),
       // inactive("inactive"),
       // enteredInError("entered-in-error");

        active, inactive, enteredInError
    }
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="i_med_id",referencedColumnName = "id")
    private List<Identifier> identifier = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="med_cc_id", referencedColumnName = "id")
    private CodeableConcept code;

    @Enumerated(value = EnumType.STRING)
    @Column(name="med_status")
    private StatusCode status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="med_q_id", referencedColumnName = "id")
    private Quantity totalVolume;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="in_med_id", referencedColumnName = "id")
    private List<Ingredient> ingredient = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="med_b_id", referencedColumnName = "id")
    private Batch batch;



}
