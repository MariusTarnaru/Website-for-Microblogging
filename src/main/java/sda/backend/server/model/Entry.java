package sda.backend.server.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(columnDefinition = "enum(ORIGINAL,EDITED)")
    @Enumerated(EnumType.STRING)
    private EntryStatus status;

    @Column(columnDefinition = "enum(ENTRY,COMMENT,SHARED_ENTRY)")
    @Enumerated(EnumType.STRING)
    private EntryType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "entries")
    private List<Tag> tags;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "entry")
    private List<Comment> comments;

}
