package at.spengergasse.spengermed.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "re_reference")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reference extends Element{

  @Column(name = "re_reference")
  private String reference;

  @Column(name = "re_type")
  private String type;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "re_i_id", referencedColumnName = "id", nullable = true)
  private Identifier identifier;

  @Column(name = "re_display")
  private String display;
}
