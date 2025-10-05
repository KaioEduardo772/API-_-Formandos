package com.alunos.formandos.Service;

import com.alunos.formandos.Models.Aluno;
import com.alunos.formandos.Models.Curso;
import com.alunos.formandos.Repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository){
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> listarTodos(){
        return cursoRepository.findAll();
    }

    public Optional<Curso> buscarPorNome(String nome) {
        return cursoRepository.findByNome(nome);
    }

    public  Optional<Curso> buscarPorId(Long id){
        return cursoRepository.findById(id);
    }

    public Curso salvar (Curso curso){
        return  cursoRepository.save(curso);
    }

    public Curso atualizar(Long id, Curso curso){
        return cursoRepository.findById(id).map(c -> {
            c.setCampus(curso.getCampus());
            c.setNome(curso.getNome());
            c.setModalidade(curso.getModalidade());
            return cursoRepository.save(c);
        }).orElseThrow(() -> new RuntimeException("Curso não encontrado"));
    }

    public void deletar(Long id){
        cursoRepository.deleteById(id);
    }
}
