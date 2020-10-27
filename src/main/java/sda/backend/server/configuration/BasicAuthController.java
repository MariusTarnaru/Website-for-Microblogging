package sda.backend.server.configuration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sda.backend.server.service.AuthenticationService;

@RestController
@RequestMapping("/api")
public class BasicAuthController {

    @GetMapping(path = "/auth")
    public AuthenticationService basicauth() {
        return new AuthenticationService("You are authenticated");
    }
}
