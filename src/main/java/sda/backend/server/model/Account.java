package sda.backend.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
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
@ToString(exclude = {"avatar", "entries", "comments", "followed", "follower", "likes"})
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
@JsonIgnoreType
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "created_data", nullable = false)
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

    @OneToMany(mappedBy = "account", cascade = {CascadeType.ALL})
    @JsonIgnore
    private List<Entry> sharedEntries = new ArrayList<>();

    @OneToMany(mappedBy = "account", cascade = {CascadeType.ALL})
    @JsonIgnore
    private List<Entry> entries = new ArrayList<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "follow",
            joinColumns = @JoinColumn(name = "followed_id"),
            inverseJoinColumns = @JoinColumn(name = "following_id"))
    @JsonIgnore
    private Set<Account> followers = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "followers")
    @JsonIgnore
    private Set<Account> following = new HashSet<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Like> likes = new HashSet<>();

    public void addEntry(Entry entry) {
        entries.add(entry);
        entry.setAccount(this);
    }

    public void removeEntry(Entry entry) {
        entries.remove(entry);
        entry.setAccount(null);
    }

    public void addSharedEntry(Entry entry) {
        sharedEntries.add(entry);
        entry.setAccount(this);
    }

    public void removeSharedEntry(SharedEntry entry) {
        entries.remove(entry);
        entry.setAccount(null);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setAccount(this);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
        comment.setAccount(null);
    }

    public void addFollower(Account follower) {
        followers.add(follower);
        follower.following.add(this);
    }

    public void removeFollower(Account follower) {
        followers.remove(follower);
        follower.following.remove(this);
    }

    public void addFollowing(Account followed) {
        followed.addFollower(this);
    }

    public void removeFollowing(Account followed) {
        followed.removeFollower(this);
    }

    public void addLike(Like like) {
        likes.add(like);
        like.setAccount(this);
        like.setCount(like.getCount() + 1);
    }

    public void removeLike(Like like) {
        likes.remove(like);
        like.setAccount(null);
        like.setCount(like.getCount() - 1);

    }


}
