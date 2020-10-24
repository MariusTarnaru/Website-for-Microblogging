package sda.backend.server.model;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter @ToString
@Table(name = "entry")
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "entry_id")
    private Long entryId;

    @Column
    private String content;

    @Column
    private String link;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "created_data")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy.MM.dd hh:mm:ss")
    private Date createdDate;

    @Column
    private EntryStatus status;

    @Column
    private EntryType type;

    /*@Column(name = "account_id")
    private long accountId;*/

    @ManyToOne
    @JoinColumn(name = "account_id")
    private long accountId;


}
