package at.spengergasse.spengermed.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "i_immunization")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Immunization extends DomainResource{

    @Column(name = "i_iotnumber")
    private String iotnumber;

    @PastOrPresent
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "i_expirationdate")
    private LocalDate expirationdate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "i_re_immunization", referencedColumnName = "id", nullable = true)
    private Reference patient;

    @Column(name = "i_occurrencedatetime", nullable = true)
    private LocalDate occurrenceDateTime;

    @Column(name = "i_occurrencestring", nullable = true)
    private String occurrenceString;

    /*@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "dq_ra_id", referencedColumnName = "id", nullable = true)
    private List<SimpleQuantity> dosequantity = new ArrayList<SimpleQuantity>();
    */
}
