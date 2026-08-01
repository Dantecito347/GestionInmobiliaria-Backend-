package com.utn.gestioninmobiliaria.dto;

public class RegisterRequestDTO {
    private String username;
    private String password;
    private String nombre;
    private String apellido;
    private Integer idTipoDoc;
    private String nroDocumento;
    private String email;
    private String telefono;
    private String cuilCuit;
    private String cbuAlias;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public Integer getIdTipoDoc() { return idTipoDoc; }
    public void setIdTipoDoc(Integer idTipoDoc) { this.idTipoDoc = idTipoDoc; }

    public String getNroDocumento() { return nroDocumento; }
    public void setNroDocumento(String nroDocumento) { this.nroDocumento = nroDocumento; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCuilCuit() { return cuilCuit; }
    public void setCuilCuit(String cuilCuit) { this.cuilCuit = cuilCuit; }

    public String getCbuAlias() { return cbuAlias; }
    public void setCbuAlias(String cbuAlias) { this.cbuAlias = cbuAlias; }
}    
