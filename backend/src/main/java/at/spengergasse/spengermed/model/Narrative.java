package at.spengergasse.spengermed.model;

import lombok.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "n_narrative")
@Builder
public class Narrative extends Element {

  public enum NarrativeCode{
    generated, extensions, additional, empty
  }

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "n_status", nullable = false)
  private NarrativeCode status;

  @NotNull
  @Lob //Large Object
  @Column(name = "n_div", nullable = false)
  private String div;
}
