package br.com.fiap.controletransacoes.repository;

import br.com.fiap.controletransacoes.entity.TransacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<TransacaoEntity, Integer> {

    List<TransacaoEntity>  findByClienteCpf(String cpf);

}
