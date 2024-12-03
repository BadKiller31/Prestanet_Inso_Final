package Inso.Examen.api.controller;

import Inso.Examen.domain.dto.UserDTO;
import Inso.Examen.domain.entity.User;
import Inso.Examen.infra.security.JwtService;
import Inso.Examen.api.core.impl.UserDetailsServiceImpl;
import Inso.Examen.infra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @Autowired
    public AuthController(JwtService jwtService, UserDetailsServiceImpl userDetailsService,
                          AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        if (userDTO.getUsername() == null || userDTO.getPassword() == null) {
            return ResponseEntity.badRequest().body("Username and password are required");
        }

        try {
            // Autenticación del usuario
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userDTO.getUsername(), 
                            userDTO.getPassword()
                    )
            );

            // Si la autenticación es exitosa, generamos el token
            String token = jwtService.generateToken(authentication);

            // Retornamos el token en un objeto para facilitar la interpretación del cliente
            return ResponseEntity.ok().body(Map.of("token", token));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred during authentication: " + e.getMessage());
        }
    }

    // Registro de nuevo usuario (si lo necesitas)
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        // Verificamos si el usuario ya existe
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
        }

        // Creamos un nuevo usuario
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));  // Cifrado de contraseña
        user.setRole(userDTO.getRole());  // Asignación de rol

        userRepository.save(user);  // Guardamos el nuevo usuario

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }
}
