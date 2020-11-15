package sda.backend.server.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tag_id", unique = true, nullable = false)
    private Long tagId;

    @Column(length = 50)
    private String content;

    @Column
    private String link;

    @Column(name = "tag_entry_id")
    private Long tagEntryId;


    @ManyToMany(
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            }
    )
    @JoinTable(name = "tag_entry",
            joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "entry_id"))
    private List<Entry> entries;

}
