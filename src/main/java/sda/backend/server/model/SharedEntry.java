package sda.backend.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "shared_entry")
public class SharedEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    @Column(name = "shared_entry_id")
    private Long sharedEntryId;

    @Column(name = "quote")
    private String quote;

    @ManyToOne(
            cascade = {
                    CascadeType.ALL
            }
    )
    @JoinColumn(name = "entry_id")
    private Entry entry;

    @ManyToOne(
            cascade = {
                    CascadeType.ALL
            }
    )
    @JoinColumn(name = "account_id")
    private Account account;


}
