package sda.backend.server.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@Table(name = "avatar")
public class Avatar {

    @Id
    @Column(nullable = false, unique = true, name = "avatar_id")
    private Long avatarId;
    @Column(nullable = false, name = "account_id")
    private Long accountId;
    @Column
    private String path;

    @OneToOne(mappedBy = "avatar")
    private Account avatarAccount;

}
