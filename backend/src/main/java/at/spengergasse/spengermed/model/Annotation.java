package at.spengergasse.spengermed.model;

import lombok.*;

import javax.persistence.*;
import java.net.URI;
import java.time.LocalDateTime;

@Entity
@Table(name = "an_annotation")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Annotation extends Element {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "re_an_id", referencedColumnName = "id")
    private Reference authorReference;

    @Column(name = "an_authorString")
    private String authorString;

    @Column(name = "an_time")
    private LocalDateTime time;

    @Column(name = "an_markdown", nullable = false)
    private String text;
}
