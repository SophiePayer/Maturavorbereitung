package at.spengergasse.spengermed.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "sh_statushistory")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatusHistory extends BackboneElement{

    public enum StatusCode{
        planned("planned"),
        arrived("arrived"),
        triaged("triaged"),
        inprogress("in-progress"),
        onleave("on-leave"),
        finished("finished"),
        cancelled("cancelled"),
        enteredinerror("entered-in-error"),
        unknown("unknown");
        private String value;

        private StatusCode(String value) {

            this.value = value;
        }

        @Override
        public String toString() {

            return this.value;
        }
    }

    @NotNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "sh_status", nullable = false)
    private StatusCode status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sh_pe_id", referencedColumnName = "id", nullable = false)
    private Period period;
}
