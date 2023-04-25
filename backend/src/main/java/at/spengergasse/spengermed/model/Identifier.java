package at.spengergasse.spengermed.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "i_identifier")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Identifier extends Element{

  public enum UseCode{
    usual, official, temp, secondary, old
  }

  @Enumerated(EnumType.STRING)
  @Column(name = "i_use")
  private UseCode use;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "i_cc_id", referencedColumnName = "id", nullable = true)
  private CodeableConcept type;

  @Column(name = "i_system")
  private String system;

  @Column(name = "i_value")
  private String value;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "i_pe_id", referencedColumnName = "id", nullable = true)
  private Period period;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "i_re_id", referencedColumnName = "id", nullable = true)
  private Reference assigner;
}
