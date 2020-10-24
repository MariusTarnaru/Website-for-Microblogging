package sda.backend.server.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Builder
@Table(name = "comment")
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id")
    private Long commentId;

    @Column
    private String content;

    @Column(name = "created_data")
    @DateTimeFormat(pattern = "dd.MM.yyyy hh:mm")
    private LocalDateTime createdDate;

    @Column(name = "entry_id")
    private long entryId;

    @Column(name = "account_id")
    private long accountId;
}
