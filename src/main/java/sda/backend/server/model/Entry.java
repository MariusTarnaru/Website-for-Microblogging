package sda.backend.server.model;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
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
    @DateTimeFormat(pattern = "yyyy.MM.dd hh:mm:ss")
    private LocalDateTime createdDate;

    @Column
    private EntryStatus status;

    @Column
    private EntryType type;

    /*@Column(name = "account_id")
    private long accountId;*/
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToMany
    private List<Tag> tags;


    public Entry() {
    }
}
