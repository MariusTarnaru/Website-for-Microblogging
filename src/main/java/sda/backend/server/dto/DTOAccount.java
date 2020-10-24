package sda.backend.server.dto;

import lombok.Builder;
import lombok.Data;
import sda.backend.server.model.AccountStatus;
import sda.backend.server.model.AccountType;

import java.time.LocalDateTime;

@Builder
@Data
public class DTOAccount {

    private Long accountId;

    private String email;

    String password;

    private String username;

    private String displayName;

    private LocalDateTime cratedAccount;

    private AccountStatus accountStatus;

    private String avatar;

    private AccountType accountType;

}
