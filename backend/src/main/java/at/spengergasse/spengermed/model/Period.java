package at.spengergasse.spengermed.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "pe_period")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Period extends Element{

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
  @Column(name = "pe_start")
  private LocalDateTime start;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
  @Column(name = "pe_end")
  private LocalDateTime end;
}
