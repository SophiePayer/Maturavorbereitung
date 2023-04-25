package at.spengergasse.spengermed.model;

import at.spengergasse.spengermed.validators.PatientValid;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.sql.Ref;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "p_patient")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@PatientValid
public class Patient extends DomainResource {

  public enum GenderCode{
    male, female, other, unknown
  }

  @OneToMany(cascade = CascadeType.ALL) //n-Seite bei Identifier (daher Identifier = FK)
  @JoinColumn(name = "i_p_id", referencedColumnName = "id", nullable = true)
  private List<Identifier> identifier = new ArrayList<Identifier>();

  @Column(name = "p_active")
  private Boolean active;
  //BOOLEAN wird groß geschrieben, wenn es auch NULL sein kann; boolean(klein) muss einen Wert haben

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "hn_p_id", nullable = true, referencedColumnName = "id")
  private List<HumanName> name = new ArrayList<HumanName>();

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "cp_p_id", referencedColumnName = "id", nullable = true)
  private List<ContractPoint> telecom = new ArrayList<ContractPoint>();

  @Enumerated(EnumType.STRING)
  @Column(name = "p_gender")
  private GenderCode gender;

  @PastOrPresent
  @Column(name = "p_birthdate")
  private LocalDate birthDate;

  @Column(name = "p_deceasedboolean")
  private Boolean deceasedBoolean;

  @Column(name = "p_deceaseddatetime")
  private LocalDateTime deceasedDateTime;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "at_p_id", referencedColumnName = "id", nullable = true)
  private List<Attachment> photo = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "a_p_id", referencedColumnName = "id", nullable = true)
  private List<Address> address = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "re_p_id", referencedColumnName = "id", nullable = true)
  private List<Reference> generalPractitioner = new ArrayList<>();

}
