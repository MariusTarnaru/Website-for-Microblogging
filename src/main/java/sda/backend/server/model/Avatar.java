package sda.backend.server.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString
@Table(name = "avatar")
public class Avatar {

    @Id
    @Column(nullable = false, unique = true, name = "avatar_id")
    private Long avatarId;
    @Column(nullable = false, name = "account_id")
    private Long accountId;
    @Column
    private String path;



    public Avatar(Long avatarId, Long accountId, String path) {
        this.avatarId = avatarId;
        this.accountId = accountId;
        this.path = path;
    }
    public Avatar(){
    }

    public Long getAvatarId() {
        return avatarId;
    }

    public Avatar setAvatarId(Long avatarId) {
        this.avatarId = avatarId;
        return this;
    }

    public Long getAccountId() {
        return accountId;
    }

    public Avatar setAccountId(Long accountId) {
        this.accountId = accountId;
        return this;
    }

    public String getPath() {
        return path;
    }

    public Avatar setPath(String path) {
        this.path = path;
        return this;
    }
}
