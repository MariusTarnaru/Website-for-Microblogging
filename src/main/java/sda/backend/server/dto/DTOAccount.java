package sda.backend.server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import sda.backend.server.model.AccountStatus;
import sda.backend.server.model.AccountType;
import sda.backend.server.model.Avatar;

import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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

    private LocalDateTime cratedAccount;

    private AccountStatus accountStatus;

    private Avatar avatar;

    private AccountType accountType;

}
