package sda.backend.server.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "like_id", unique = true)
    private Long likeId;

    @Column
    private Long count;

    @Column(name = "created_data")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy.MM.dd hh:mm:ss")
    private LocalDateTime createdData;

    @Column(name = "entry_id", nullable = false)
    private Long entryId;

    @Column(name = "account_id", nullable = false)
    private Long accountId;
}
