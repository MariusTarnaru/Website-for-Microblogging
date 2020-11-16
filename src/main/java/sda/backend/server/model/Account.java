package sda.backend.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"avatar", "entries", "comments", "followed", "follower", "likes", "sharedEntries"})
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
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
    private LocalDateTime createdAccount;

    @Column(name = "account_status", columnDefinition = "enum(ACTIVE,INACTIVE,BLOCKED)")
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    @Column(name = "account_type", columnDefinition = "enum(PUBLIC, PRIVATE)")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "avatar_id", referencedColumnName = "avatar_Id")
    private Avatar avatar;

    @OneToMany(
            mappedBy = "account",
            cascade = {
                    CascadeType.ALL
            }
    )
    @JsonIgnore
    private List<Entry> entries = new ArrayList<>();

    @OneToMany(mappedBy = "account",
            cascade = {
                    CascadeType.ALL
            }
    )
    @JsonIgnore
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "account",
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.REMOVE
            }
    )
    @JsonIgnore
    private List<Followed> followed = new ArrayList<>();

    @OneToMany(mappedBy = "account",
            cascade = {
                    CascadeType.ALL
            }
    )
    @JsonIgnore
    private List<Follower> follower = new ArrayList<>();

    @OneToMany(mappedBy = "account",
            cascade = {
                    CascadeType.ALL
            }
    )
    @JsonIgnore
    Set<Like> likes = new HashSet<>();

    @OneToMany(mappedBy = "account",
            cascade = {
                    CascadeType.ALL
            }
    )
    @JsonIgnore
    Set<SharedEntry> sharedEntries = new HashSet<>();

    public void addEntry(Entry newEntry) {
        if (entries == null) {
            entries = new ArrayList<>();
        }
        entries.add(newEntry);
        newEntry.setAccount(this);
    }

}
