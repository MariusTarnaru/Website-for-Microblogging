package sda.backend.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "like_id", unique = true)
    private Long likeId;

    @Column
    private Long count;

    @Column(name = "created_data")
    @DateTimeFormat(pattern = "yyyy.MM.dd hh:mm:ss")
    private LocalDateTime createdData;

    @ManyToOne(
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.REMOVE
            }
    )
    @JsonIgnore
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.REMOVE
            }
    )
    @JsonIgnore
    @JoinColumn(name = "entry_id")
    private Entry entry;

}
