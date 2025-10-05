package com.alunos.formandos.Controller;

import com.alunos.formandos.Models.Campus;
import com.alunos.formandos.Service.CampusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/campus")
public class CampusController {

    private final CampusService campusService;

    public CampusController(CampusService campusService){
        this.campusService = campusService;
    }

    @GetMapping
    public List<Campus> listar(){
        return campusService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Campus> buscar (@PathVariable Long id){
        return campusService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Campus> buscarPorNome(@PathVariable String nome){
        Optional<Campus> campus = campusService.buscarPorNome(nome);
        return campus.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Campus criar(@RequestBody Campus campus){
        return campusService.salvar(campus);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Campus> atualizar (@PathVariable Long id, @RequestBody Campus campus){
        try {
            return ResponseEntity.ok(campusService.atualizar(id,campus));
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar (@PathVariable Long id){
        campusService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
