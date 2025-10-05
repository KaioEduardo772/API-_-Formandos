package com.alunos.formandos.Service;

import com.alunos.formandos.Models.Aluno;
import com.alunos.formandos.Repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> buscarPorId(Long id) {
        return alunoRepository.findById(id);
    }

    public Optional<Aluno> buscarPorNome(String nome) {
        return alunoRepository.findByNome(nome);
    }

    public Aluno salvar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public Aluno atualizar(Long id, Aluno aluno) {
        return alunoRepository.findById(id).map(a -> {
            a.setNome(aluno.getNome());
            a.setMatricula(aluno.getMatricula());
            a.setSituacao(aluno.getSituacao());
            a.setDataIngresso(aluno.getDataIngresso());
            a.setCurso(aluno.getCurso());
            return alunoRepository.save(a);
        }).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }

    public void deletar(Long id) {
        alunoRepository.deleteById(id);
    }
}

