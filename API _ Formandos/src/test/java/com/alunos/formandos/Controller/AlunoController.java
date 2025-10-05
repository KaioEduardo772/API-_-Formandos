package com.alunos.formandos.Controller;

import com.alunos.formandos.Models.Aluno;
import com.alunos.formandos.Service.AlunoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService){
        this.alunoService = alunoService;
    }

    @GetMapping
    public List<Aluno> listar(){
        return alunoService.listarTodos();
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Aluno> buscarPorNome(@PathVariable String nome) {
        Optional<Aluno> aluno = alunoService.buscarPorNome(nome);
        return aluno.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscar(@PathVariable Long id) {
        return alunoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Aluno criar(@RequestBody Aluno aluno) {
        return alunoService.salvar(aluno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizar(@PathVariable Long id, @RequestBody Aluno aluno) {
        try {
            return ResponseEntity.ok(alunoService.atualizar(id, aluno));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        alunoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
