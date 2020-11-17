package sda.backend.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "comment_id")
    private Long commentId;

    @Column
    private String content;

    @Column(name = "created_data", nullable = false)
    @DateTimeFormat(pattern = "dd.MM.yyyy hh:mm")
    private LocalDateTime createdDate;

    @ManyToOne(
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            }
    )
    @JoinColumn(name = "entry_id", nullable = false)
    private Entry entry;

    @ManyToOne(
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            }
    )
    @JoinColumn(name = "account_id", nullable = false)
    @JsonIgnore
    private Account account;





}
