package at.spengergasse.spengermed.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="hn_humanname")
@Builder
public class HumanName extends Element{

  public enum UseCode{
    usual, official, temp, nickname, anonymous, old, maiden
  }

  @Enumerated(EnumType.STRING)
  @Column(name = "hn_use")
  private UseCode use;

  @Column(name = "hn_text")
  private String text;

  @Column(name = "hn_family")
  private String family;

  @ElementCollection
  @CollectionTable(name = "given_humanname", joinColumns = @JoinColumn(name = "id"))
  private List<String> given = new ArrayList<>();

  @ElementCollection
  @CollectionTable(name = "prefix_humanname", joinColumns = @JoinColumn(name = "id"))
  private List<String> prefix = new ArrayList<>();

  @ElementCollection
  @CollectionTable(name = "suffix_humanname", joinColumns = @JoinColumn(name = "id"))
  private List<String> suffix = new ArrayList<>();

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "hn_pe_id", referencedColumnName = "id", nullable = true)
  private Period period;
}
