package at.spengergasse.spengermed.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="q_quantity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Quantity extends Element{

    @Column(name="q_value")
    private Double decimal;

    @Column(name="q_comparator")
    private String comparator;

    @Column(name="q_unit")
    private String unit;

    @Column(name="q_system")
    private String system;

    @Column(name="q_code")
    private String code;
}
