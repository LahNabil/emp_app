package net.lahlalia.emp_api.services;

import lombok.RequiredArgsConstructor;
import net.lahlalia.emp_api.dtos.AuthenticationRequest;
import net.lahlalia.emp_api.dtos.AuthenticationResponse;
import net.lahlalia.emp_api.dtos.RegistrationRequest;
import net.lahlalia.emp_api.entities.User;
import net.lahlalia.emp_api.repositories.RoleRepository;
import net.lahlalia.emp_api.repositories.UserRepository;
import net.lahlalia.emp_api.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    public void register(RegistrationRequest request) {
        var userRole= roleRepository.findByName("USER")
                .orElseThrow(()-> new IllegalStateException("ROLE USER NOT initialized"));
        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .username(request.getUsername())
                .phone(request.getPhone())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enabled(true)
                .roles(List.of(userRole))
                .build();
        userRepository.save(user);

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var claims = new HashMap<String, Object>();
        var user = ((User)auth.getPrincipal());
        claims.put("fullname", user.fullName());
        var jwt = jwtService.generateToken(claims, user);
        return AuthenticationResponse.builder()
                .token(jwt).build();
    }
}
