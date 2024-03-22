package com.example.findgame.security;

public class JwtResponse {

    private String token;
    private String role;
    private Long id;     // User's ID
    private String email;  // User's email


    // Constructors, getters, setters...

    public JwtResponse(String token, String role, Long id, String email) {
        this.token = token;
        this.role = role;
        this.id = id;
        this.email = email;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
