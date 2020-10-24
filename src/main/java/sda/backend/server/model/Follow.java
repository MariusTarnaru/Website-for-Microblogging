package sda.backend.server.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@Table(name = "follow")
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "following_id")
    private Long followingId;

    @Column(name = "account_id_followed")
    private Long accountIdFollowed;

    @Column(name = "account_id_follows")
    private Long accountIdFollows;

    @Column(name = "created_data")
    @DateTimeFormat(pattern = "yyyy.MM.dd hh:mm:ss")
    private LocalDateTime createdData;

}
