package com.alunos.formandos.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="curso")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCurso;

    private String modalidade;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_campus", nullable = false)
    private Campus campus;
}
