package at.spengergasse.spengermed.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class DomainResource extends Resource {

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "dr_n_id", referencedColumnName = "id", nullable = true)
  private Narrative text;
}
