package at.spengergasse.spengermed.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "at_attachment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Attachment extends Element{

    //ContentType?
    @Column(name = "at_contenttype")
    private String contenttype;

    //Language?
    @Column(name = "at_language")
    private String language;

    @Lob
    @Column(name = "at_data")
    private String data;

    @Column(name = "at_url")
    private String url;

    @Column(name = "at_size")
    private Integer size;

    @Lob
    @Column(name = "at_hash")
    private String hash;

    @Column(name = "at_title")
    private String title;

    @Column(name = "at_creation")
    private LocalDateTime creation;
}
