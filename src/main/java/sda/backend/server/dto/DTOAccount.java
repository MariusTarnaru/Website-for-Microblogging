package sda.backend.server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sda.backend.server.model.AccountStatus;
import sda.backend.server.model.AccountType;
import sda.backend.server.model.Avatar;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DTOAccount {

    private Long accountId;

    @Email(message = "Email type")
    private String email;
    @NotEmpty
    String password;
    @NotEmpty
    private String username;
    @NotEmpty
    private String displayName;

    private LocalDateTime cratedAccount;

    private AccountStatus accountStatus;

    private Avatar avatar;

    private AccountType accountType;

}
