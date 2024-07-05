package com.challenge.forohub.domain.model.usuario;

import com.challenge.forohub.domain.dto.DatosModificarUsuario;
import com.challenge.forohub.domain.dto.DatosRegistroUsuario;
import com.challenge.forohub.domain.model.roles.Rol;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String correoElectronico;
    private String clave;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id")
    )
    private List<Rol> roles= new ArrayList<>();
    private boolean activo;



    public Usuario(DatosRegistroUsuario datos, String pass, Rol rol) {
        this.nombre = datos.nombre();
        this.correoElectronico = datos.CorreoElectronico();
        this.clave = pass;
        this.roles.add(rol);
        this.activo = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(roles -> new SimpleGrantedAuthority(roles.getNombre()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.clave;
    }

    @Override
    public String getUsername() {
        return this.correoElectronico;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void ModificarUsuario(DatosModificarUsuario datos, String pass, Rol rol) {

        if (datos.nombre() != null) {
            this.nombre = datos.nombre();
        }
        if (datos.correoElectronico() != null) {
            this.correoElectronico = datos.correoElectronico();
        }
        if (datos.clave() != null) {
            this.clave = datos.clave();
        }

        if (pass != null) {
            this.clave = pass;
        }

        if (rol != null) {
            this.roles.add(rol);
        }

    }

    public void desactivarUsuario() {
        this.activo = false;
    }
}
