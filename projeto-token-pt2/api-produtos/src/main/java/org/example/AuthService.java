package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {

    @Value("${auth.api.url:http://localhost:8881/acesso/login}")
    private String authApiUrl;

    public boolean autenticar(String usuario, String senha) {
        RestTemplate restTemplate = new RestTemplate();

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsuario(usuario);
        loginDTO.setSenha(senha);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<LoginDTO> request = new HttpEntity<>(loginDTO, headers);

        try {
            ResponseEntity<Boolean> response = restTemplate.postForEntity(authApiUrl, request, Boolean.class);
            return response.getBody() != null && response.getBody();
        } catch (Exception e) {
            return false;
        }
    }
}
