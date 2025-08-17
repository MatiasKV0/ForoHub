package com.challenge.ForoHub.controller;

import com.challenge.ForoHub.domain.topico.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @GetMapping
    public ResponseEntity<Page<DatosTopico>> listaTopicos(@PageableDefault(size = 2) Pageable paginacion){
        return ResponseEntity.ok(repository.findAll(paginacion).map(DatosTopico::new));
    }

    @PostMapping
    public ResponseEntity<DatosTopico> creaTopico(@RequestBody @Valid ValidDatosTopico validDatosTopico, UriComponentsBuilder uriComponentsBuilder){
        Topico topico = repository.save(new Topico(validDatosTopico));
        DatosTopico datosTopico = new DatosTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFecha());

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosTopico);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizaTopico(@RequestBody @Valid ValidPutDatosTopico validPutDatosTopico){
        Topico topico = repository.getReferenceById(validPutDatosTopico.id());
        topico.actualizarDatos(validPutDatosTopico);
        return ResponseEntity.ok(new DatosTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminaTopico(@PathVariable Long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
