package br.com.fiap.controletransacoes.repository;

import br.com.fiap.controletransacoes.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository that includes operations of a ProdutoEntity
 */
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Integer> {
}
