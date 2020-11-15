package sda.backend.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tag_entry")
public class TagEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tag_entry_id", nullable = false, unique = true)
    private Long tagEntryId;

    @Column(name = "tag_id", nullable = false)
    private Long tagId;

    @ManyToOne(
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            }
    )
    @JoinColumn(name = "entry_id")
    @JsonIgnore
    private Entry entry;
}
