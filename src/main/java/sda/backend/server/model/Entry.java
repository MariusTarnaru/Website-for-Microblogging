package sda.backend.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "entry_id")
    private Long entryId;

    @Column
    private String content;

    @Column
    private String link;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "created_data", nullable = false)
    @DateTimeFormat(pattern = "yyyy.MM.dd hh:mm:ss")
    private LocalDateTime createdDate;

    @Column(columnDefinition = "enum(ORIGINAL,EDITED)")
    @Enumerated(EnumType.STRING)
    private EntryStatus status;

    @Column(columnDefinition = "enum(ENTRY,COMMENT,SHARED_ENTRY)")
    @Enumerated(EnumType.STRING)
    private EntryType type;

    ///////////////////////////////////////////////////////////////////////
    @ManyToOne(
            cascade = {
                    CascadeType.ALL
            }
    )
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.REFRESH
    }
    )
    @JoinTable(name = "tag_entry",
    joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "entry_id")
    )
    private Set<Tag> tags = new HashSet<>();

    @OneToMany(mappedBy = "entry", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "entry",
            cascade = {
                    CascadeType.ALL
            }
    )
    private Set<Like> likes = new HashSet<>();

    @OneToMany(mappedBy = "entry", cascade = CascadeType.ALL)
    private List<SharedEntry> sharedEntries = new ArrayList<>();

    ///////////////////////////////////////////////////////////////////////
    public void addTag(Tag tag) {
        tags.add(tag);
        tag.getEntries().add(this);
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
        tag.getEntries().remove(this);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setEntry(this);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
        comment.setEntry(null);
    }

    public void addLike(Like like) {
        likes.add(like);
        like.setEntry(this);
        like.setCount(like.getCount() + 1);

    }

    public void removeLike(Like like) {
        likes.remove(like);
        like.setEntry(null);
        like.setCount(like.getCount() - 1);

    }

    public void addSharedEntry(SharedEntry entry) {
        sharedEntries.add(entry);
        entry.setEntry(this);
    }

    public void removeSharedEntry(SharedEntry entry) {
        sharedEntries.remove(entry);
        entry.setEntry(null);
    }

}
