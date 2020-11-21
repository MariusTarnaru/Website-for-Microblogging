package sda.backend.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
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
@JsonIgnoreType
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

    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    private List<Entry> entries;

}
