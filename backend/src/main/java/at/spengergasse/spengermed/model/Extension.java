package at.spengergasse.spengermed.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

@Entity
@Table(name = "ex_extension")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Extension extends Element{

    @Column(name = "ex_uri")
    private String uri;

    //value[x] --> kann verschiedene Datentypen haben, daher Type *
    //ich nehme String, da auch Zahlen usw. gespeichert werden können

    @Column(name = "ex_value")
    private String value;
}
