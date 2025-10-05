package com.alunos.formandos.Repository;
import com.alunos.formandos.Models.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    Optional<Aluno> findByNome(String nome);
}
