package sda.backend.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "avatar")
public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true, name = "avatar_id")
    private Long avatarId;
    @Column
    private String path;

    @OneToOne(mappedBy = "avatar", fetch = FetchType.EAGER)
    @JoinColumn(name = "avatar", referencedColumnName = "avatar_id")
    @JsonIgnore
    private Account account;

}
