package sda.backend.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "entry")
public class Entry {
    @Id
    @EqualsAndHashCode.Include
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

    @ManyToOne(
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            }
    )
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;

    @ManyToMany(mappedBy = "entries",
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            }
    )
    private List<Tag> tags;

    @OneToMany(mappedBy = "entry", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "entry",
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            }
    )
    private Set<Like> likes;

    @OneToMany(mappedBy = "entry",
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            }
    )
    @JsonIgnore
    private Set<SharedEntry> sharedEntries;

    @OneToMany(mappedBy = "entry",
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            }
    )
    private List<TagEntry> tagEntries;

}
