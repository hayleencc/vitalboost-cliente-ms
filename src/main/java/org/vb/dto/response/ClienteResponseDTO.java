package org.vb.dto.response;

public class ClienteResponseDTO {
    private String id;
    private String rol;

    private String nombreCompleto;
    private String email;

    public String getId() { return id;  }

    public void setId(String id) {  this.id = id;   }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getEmail() {  return email;   }

    public void setEmail(String email) {    this.email = email;    }

    public String getRol() {    return rol;    }

    public void setRol(String rol) {    this.rol = rol;     }
}
