package sda.backend.server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sda.backend.server.model.AccountStatus;
import sda.backend.server.model.AccountType;
import sda.backend.server.model.Avatar;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DTOAccount {

    private Long accountId;

    private String email;

    String password;

    private String username;

    private String displayName;

    private LocalDateTime createdAccount;

    private AccountStatus accountStatus;

    private Avatar avatar;

    private AccountType accountType;

}
