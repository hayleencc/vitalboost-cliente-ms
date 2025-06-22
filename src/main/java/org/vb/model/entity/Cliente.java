package org.vb.model.entity;


import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.util.UUID;
import java.time.Instant;

@Entity
@Table(name="clientes")
@EntityListeners(AuditingEntityListener.class)

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nombre_completo", nullable = false)
    private String nombreCompleto;

    @Column(nullable = false, unique = true)
    private String email;

    @CreatedDate
    @Column(name = "fecha_registro", nullable = false, updatable = false)
    private Instant fechaRegistro;

    public Cliente(){}

    public Cliente(UUID id, String nombreCompleto, String email){
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Instant getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Instant fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
