package at.spengergasse.spengermed.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "pay_payload")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payload extends BackboneElement{

    @Column(name = "pay_contentstring",nullable = true)
    private String contentstring;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pay_at_id", referencedColumnName = "id", nullable = true)
    private Attachment contentattachment;
}
