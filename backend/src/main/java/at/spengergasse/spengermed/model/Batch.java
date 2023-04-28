package at.spengergasse.spengermed.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name="b_batch")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Batch extends BackboneElement{
    @Column(name="b_Iotnumber")
    private String iotNumber;

    @Column(name="b_expirationdate")
    private LocalDateTime expirationDate;
}
