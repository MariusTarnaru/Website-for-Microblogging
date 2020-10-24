package sda.backend.server.model;

import lombok.*;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@Builder
@NoArgsConstructor
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


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tag_entry",
            joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "entry_id"))
    private List<Entry> entries;

}
