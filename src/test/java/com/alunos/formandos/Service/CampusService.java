package com.alunos.formandos.Service;

import com.alunos.formandos.Models.Campus;
import com.alunos.formandos.Repository.CampusRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampusService {
    private final CampusRepository campusRepository;

    public CampusService(CampusRepository campusRepository){
        this.campusRepository = campusRepository;
    }

    public List<Campus> listarTodos(){
        return campusRepository.findAll();
    }

    public Optional<Campus> buscarPorNome(String nome){
        return campusRepository.findByNome(nome);
    }

    public Optional<Campus> buscarPorId(Long id){
        return campusRepository.findById(id);
    }

    public Campus salvar (Campus campus){
        return campusRepository.save(campus);
    }

    public Campus atualizar (Long id, Campus campus){
        return campusRepository.findById(id).map(c -> {
            c.setNome(campus.getNome());
            return campusRepository.save(c);
        }).orElseThrow(() -> new RuntimeException("Campus não encontrado"));
    }

    public void deletar (Long id){
        campusRepository.deleteById(id);
    }
}
