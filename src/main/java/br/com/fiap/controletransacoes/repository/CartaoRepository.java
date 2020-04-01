package br.com.fiap.controletransacoes.repository;

import br.com.fiap.controletransacoes.entity.CartaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<CartaoEntity, String> {
}
