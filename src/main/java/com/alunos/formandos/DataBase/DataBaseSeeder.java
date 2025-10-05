package com.alunos.formandos.DataBase;

import com.alunos.formandos.Models.Aluno;
import com.alunos.formandos.Models.Campus;
import com.alunos.formandos.Models.Curso;
import com.alunos.formandos.Repository.AlunoRepository;
import com.alunos.formandos.Repository.CampusRepository;
import com.alunos.formandos.Repository.CursoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class DataBaseSeeder implements CommandLineRunner {

    private final CampusRepository campusRepo;
    private final CursoRepository cursoRepo;
    private final AlunoRepository alunoRepo;

    @Override
    public void run(String... args) throws Exception {
        if (alunoRepo.count() > 0) {
            System.out.println("Banco já populado, ignorando seed inicial.");
            return;
        }

        String csvFile = "src/main/resources/PDA_2022-2024_1.2_Alunos_Anonimo.csv";
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(csvFile),
                StandardCharsets.UTF_8
        )
        );

        String line;

        while ((line = br.readLine()) != null) {
            String[] dados = line.split(";");
            if (dados.length < 8) continue;

            String tipoAluno = dados[0].trim();
            String nome = dados[2].trim();
            String modalidade = dados[3].trim();
            String cursoNome = dados[4].trim();
            String campusNome = dados[5].trim();
            String situacao = dados[6].trim();
            String matricula = dados[7].trim();
            String dataIngressoStr = (dados.length > 8) ? dados[8].trim() : null;

            Campus campus = campusRepo.findAll()
                    .stream().filter(c -> c.getNome().equals(campusNome))
                    .findFirst()
                    .orElseGet(() -> campusRepo.save(new Campus(null, campusNome)));

            Curso curso = cursoRepo.findAll()
                    .stream().filter(c -> c.getNome().equals(cursoNome))
                    .findFirst()
                    .orElseGet(() -> cursoRepo.save(new Curso(null, modalidade, cursoNome, campus)));

            LocalDate dataIngresso = null;
            if (dataIngressoStr != null && !dataIngressoStr.isEmpty()) {
                DateTimeFormatter formatter;
                if (dataIngressoStr.matches("\\d{1,2}/\\d{1,2}/\\d{4}")) {
                    // Ano com 4 dígitos
                    formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                } else if (dataIngressoStr.matches("\\d{1,2}/\\d{1,2}/\\d{2}")) {
                    // Ano com 2 dígitos
                    formatter = DateTimeFormatter.ofPattern("d/M/yy");
                } else {
                    throw new IllegalArgumentException("Formato de data inválido: " + dataIngressoStr);
                }
                dataIngresso = LocalDate.parse(dataIngressoStr, formatter);
            }

            Aluno aluno = new Aluno(null, tipoAluno, nome, matricula, situacao, dataIngresso, curso);
            alunoRepo.save(aluno);
        }

        System.out.println("Banco populado com dados do CSV!");
    }
}
