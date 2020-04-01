package br.com.fiap.controletransacoes.repository;

import br.com.fiap.controletransacoes.entity.TransacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository that includes operations of a TransacaoEntity
 */
public interface TransacaoRepository extends JpaRepository<TransacaoEntity, Integer> {

    /**
     * Method that retrieves a TransacaoEntity by a Client cpf
     * @param cpf Cpf identifier of a Cliente to be retrieved
     */
    List<TransacaoEntity>  findByClienteCpf(String cpf);

    /**
     * Method that retrieves a TransacaoEntity by a Cartao id
     * @param id ID identifier of a Cartao to be retrieved
     */
    List<TransacaoEntity> findByCartaoEntityId(Integer id);

}
