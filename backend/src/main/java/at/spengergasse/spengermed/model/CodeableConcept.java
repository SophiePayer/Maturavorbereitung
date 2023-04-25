package at.spengergasse.spengermed.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cc_codeableconcept")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CodeableConcept extends Element{

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "co_cc_id", referencedColumnName = "id", nullable = true)
  private List<Coding> coding = new ArrayList<Coding>();

  @Column(name = "cc_text")
  private String text;
}
