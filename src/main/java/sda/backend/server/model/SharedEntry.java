package sda.backend.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shared_entry")
public class SharedEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "shared_entry_id")
    private Long sharedEntryId;

    @Column(name = "quote")
    private String quote;

    @ManyToOne(
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            }
    )
    @JoinColumn(name = "entry_id")
    private Entry entry;

    @ManyToOne(
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            }
    )
    @JoinColumn(name = "account_id")
    private Account account;


}
