package br.com.fiap.controletransacoes.repository;

import br.com.fiap.controletransacoes.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository that includes operations of a Cliente
 */
public interface ClienteRepository extends JpaRepository<ClienteEntity, String> {
}
