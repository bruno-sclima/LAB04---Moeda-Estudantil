package com.lab04.moedaEstudantil.repository;
import com.lab04.moedaEstudantil.model.Aluno;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AlunoRepository extends CrudRepository<Aluno, Long>{

}
