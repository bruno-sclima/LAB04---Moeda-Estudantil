package com.lab04.moedaEstudantil.repository;
import com.lab04.moedaEstudantil.model.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TransacaoRepository extends CrudRepository<Transacao, Long>{

}
