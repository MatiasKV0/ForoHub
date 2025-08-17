package com.challenge.ForoHub.domain.topico;

import com.challenge.ForoHub.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fecha;

    public Topico(@Valid ValidDatosTopico validDatosTopico) {
        this.titulo=validDatosTopico.titulo();
        this.mensaje=validDatosTopico.mensaje();
        this.fecha= LocalDateTime.now();
    }

    public void actualizarDatos(ValidPutDatosTopico validPutDatosTopico) {
        if(validPutDatosTopico.titulo() != null){
            this.titulo = validPutDatosTopico.titulo();
        }
        if(validPutDatosTopico.mensaje() != null){
            this.mensaje = validPutDatosTopico.mensaje();
        }
    }
}
