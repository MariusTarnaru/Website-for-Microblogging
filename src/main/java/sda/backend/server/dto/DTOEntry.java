package sda.backend.server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sda.backend.server.model.EntryStatus;
import sda.backend.server.model.EntryType;

import java.time.LocalDateTime;

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

}
