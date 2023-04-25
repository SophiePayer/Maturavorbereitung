package at.spengergasse.spengermed.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "a_address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address extends Element{

  public enum UseCode{
    home, work, temp, old, billing
  }

  public enum TypeCode{
    postal, physical, both
  }

  @Enumerated(EnumType.STRING)
  @Column(name = "a_use")
  private UseCode use;

  @Enumerated(EnumType.STRING)
  @Column(name = "a_type")
  private TypeCode type;

  @Column(name = "a_text")
  private String text;

  @ElementCollection
  @CollectionTable(name = "a_address_line")
  private List<String> line = new ArrayList<>();

  @Column(name = "a_city")
  private String city;

  @Column(name = "a_district")
  private String district;

  @Column(name = "a_state")
  private String state;

  @Column(name = "a_postalcode")
  private String postalCode;

  @Column(name = "a_country")
  private String country;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "a_pe_id", referencedColumnName = "id", nullable = true)
  private Period period;

}
