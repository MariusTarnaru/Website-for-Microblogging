package sda.backend.server.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "tag_id")
    private Long tagId;

    @Column(length = 50)
    private String content;

    @Column
    private String link;

    @ManyToMany( mappedBy = "tags")

    private List<Entry> entries;

}
