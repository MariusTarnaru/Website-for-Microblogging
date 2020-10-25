package sda.backend.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @DateTimeFormat(pattern = "yyyy.MM.dd hh:mm:ss")
    private LocalDateTime cratedAccount;
    /*private CurrentDateTimeProvider createdAccount;*/

    @Column(name = "account_status")
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Entry> entry;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "avatar_id", referencedColumnName = "avatar_Id")
    private Avatar avatar;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Comment> comments;

}
