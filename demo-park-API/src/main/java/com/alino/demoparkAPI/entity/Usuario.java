package com.alino.demoparkAPI.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "usuarios")
public class Usuario implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID UUID;
    
    @Column(name = "usuario", nullable = false, unique = true, length = 100)
    private String usuario;

    @Column(name = "senha", nullable = false, length = 200)
    private String senha;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "cargo", nullable = false, length = 25)
    private Cargo cargo = Cargo.USUARIO;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "data_modificacao")
    private LocalDateTime dataModificacao;

    @Column(name = "criado_por")
    private String criadoPor;

    @Column(name = "modificado_por")
    private String modificadoPor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(UUID, usuario.UUID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(UUID);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + UUID +
                '}';
    }

}
