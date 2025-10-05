package com.alunos.formandos.Controller;

import com.alunos.formandos.Security.JwtUntil;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    // Exemplo básico: login fake
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> user) {
        String username = user.get("username");
        String password = user.get("password");

        // Aqui você validaria no banco de dados. Vou simplificar:
        if ("admin".equals(username) && "123".equals(password)) {
            String token = JwtUntil.generateToken(username);
            return Map.of("token", token);
        } else {
            throw new RuntimeException("Usuário ou senha inválidos");
        }
    }

    // Teste de rota pública
    @GetMapping("/publico")
    public String publico() {
        return "Essa rota é pública";
    }

    // Teste de rota protegida
    @GetMapping("/privado")
    public String privado() {
        return "Se você está vendo isso, passou pelo JWT!";
    }
}
