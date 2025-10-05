package com.alunos.formandos.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="campus")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Campus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCampus;

    @Column(nullable = false, unique = true)
    private String nome;
}
