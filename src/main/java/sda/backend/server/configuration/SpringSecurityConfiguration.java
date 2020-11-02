package sda.backend.server.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sda.backend.server.exception.UserNotFoundException;
import sda.backend.server.model.Account;
import sda.backend.server.repository.AccountRepository;

import java.util.Optional;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/register","/login");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and().cors().and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(user -> {
                            Optional<Account> accountFromDB = accountRepository.findByEmail(user);
                            if (accountFromDB.isPresent()) {
                                return new User(
                                        accountFromDB.get().getEmail(),
                                        accountFromDB.get().getPassword(),
                                        true,
                                        true,
                                        true,
                                        true,
                                        AuthorityUtils.createAuthorityList("USER"));
                            } else {
                                throw new UserNotFoundException();
                            }
                        }
                )
                .passwordEncoder(new BCryptPasswordEncoder())
        ;
    }
}


