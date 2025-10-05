package com.alunos.formandos.Repository;
import com.alunos.formandos.Models.Campus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CampusRepository extends JpaRepository<Campus, Long> {
    Optional<Campus> findByNome(String nome);
}

