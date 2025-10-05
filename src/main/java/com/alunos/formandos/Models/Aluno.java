package com.alunos.formandos.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "aluno")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAluno;

    @Column(name = "tipo_aluno")
    private String tipoAluno;

    private String nome;
    private String matricula;
    private String situacao;

    @Column(name = "data_ingresso")
    private LocalDate dataIngresso;

    @ManyToOne
    @JoinColumn(name = "id_curso")
    private Curso curso;
}
