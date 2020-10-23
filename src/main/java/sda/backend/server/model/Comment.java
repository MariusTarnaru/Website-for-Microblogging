package sda.backend.server.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comment")
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id")
    private Long commentId;

    @Column
    private String content;

    @Column(name = "created_data")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd.MM.yyyy hh:mm")
    private Date createdDate;

    @Column(name = "entry_id")
    private long entryId;

    @Column(name = "account_id")
    private long accountId;
}
