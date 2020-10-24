package sda.backend.server.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private Long accountId;

    @Column(unique = true, nullable = false, name = "login_email")
    private String email;

    @Column(nullable = false)
    String password;

    @Column(unique = true, nullable = false, name = "account_name")
    private String username;


    @Column(unique = true, name = "display_name")
    private String displayName;

    @Column(name = "created_data")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy.MM.dd hh:mm:ss")
    private LocalDateTime cratedAccount;
    /*private CurrentDateTimeProvider createdAccount;*/

    @Column(name = "account_status")
    private AccountStatus accountStatus;

    @Column(name = "account_type")
    private AccountType accountType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private List<Entry> entry;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "avatar_id", referencedColumnName = "avatarId")
    private Avatar avatar;

}
