package org.example.authentication.controllers;

import jakarta.validation.Valid;
import org.example.user.domain.User;
import org.example.authentication.dto.AutheticationDTO;
import org.example.user.dto.LoginResponseDTO;
import org.example.user.dto.RegisterDTO;
import org.example.authentication.TokenService;
import org.example.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AutheticationDTO autheticationDTO) {
        System.out.println("Requisição ok");

        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
                autheticationDTO.login(),
                autheticationDTO.password()
        );

        System.out.println("Antes da autenticação");

        var auth = authenticationManager.authenticate(usernamePassword);

        System.out.println("Depois da autenticação");

        var token = tokenService.generateToken((User) auth.getPrincipal());

        System.out.println("Sucesso");
        return ResponseEntity.ok(new LoginResponseDTO(token, autheticationDTO.login()));
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO registerDTO) {
        System.out.println("Requisição ok");
        if (this.repository.findByLogin(registerDTO.login()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        User user = new User(registerDTO.login(), encryptedPassword, registerDTO.role());

        this.repository.save(user);
        System.out.println("Sucesso");
        return ResponseEntity.ok().build();
    }

}
