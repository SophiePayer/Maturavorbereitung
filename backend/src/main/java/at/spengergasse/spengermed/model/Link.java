package at.spengergasse.spengermed.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "lk_link")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Link extends BackboneElement{

    @Column(name = "lk_relation", nullable = false)
    private String relation;

    @Column(name = "lk_uri", nullable = false)
    private String uri;
}
