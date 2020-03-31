package br.com.fiap.controletransacoes.service;

import br.com.fiap.controletransacoes.dto.ClienteDTO;

import java.util.List;

/**
 * Cliente Service
 * Interface that contains Cliente operations
 */
public interface ClienteService {

    List<ClienteDTO> getAll();
    ClienteDTO getByCpf(String cpf);
    ClienteDTO salvarCliente(ClienteDTO clienteDTO);
    ClienteDTO atualizarCliente(String cpf, ClienteDTO clienteDTO);
    void deletarCliente(String cpf);
}
