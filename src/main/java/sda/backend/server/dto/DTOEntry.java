package sda.backend.server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sda.backend.server.model.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DTOEntry {

    private Long entryId;

    private String content;

    private String link;

    private String imagePath;

    private LocalDateTime createdDate;

    private EntryStatus status;

    private EntryType type;

    private Account account;

    private Set<Tag> tags;

    private List<Comment> comments;

    private Set<Like> likes;

}
