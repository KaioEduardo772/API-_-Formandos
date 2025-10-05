package com.alunos.formandos.Controller;


import com.alunos.formandos.Models.Curso;
import com.alunos.formandos.Service.CursoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/curso")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService){
        this.cursoService = cursoService;
    }

    @GetMapping
    public List<Curso> listar (){
        return  cursoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscar (@PathVariable Long id){
        return cursoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Curso> buscarPorNome(@PathVariable String nome){
        Optional<Curso> curso = cursoService.buscarPorNome(nome);
        return curso.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Curso criar(@RequestBody Curso curso){
        return cursoService.salvar(curso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizar (@PathVariable Long id, @RequestBody Curso curso){
        try {
            return ResponseEntity.ok(cursoService.atualizar(id,curso));
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        cursoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
