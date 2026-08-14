package com.utn.gestioninmobiliaria.dto;
import lombok.Data;

@Data
public class ResetPasswordRequestDTO {
    private String token;
    private String newPassword;

    public ResetPasswordRequestDTO() {
    }

    public ResetPasswordRequestDTO(String token, String newPassword) {
        this.token = token;
        this.newPassword = newPassword;
    }

    public String getToken() {
        return token;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    
}
