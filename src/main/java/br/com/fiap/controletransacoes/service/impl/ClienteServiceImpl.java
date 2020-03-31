package br.com.fiap.controletransacoes.service.impl;

import br.com.fiap.controletransacoes.dto.ClienteDTO;
import br.com.fiap.controletransacoes.entity.ClienteEntity;
import br.com.fiap.controletransacoes.repository.ClienteRepository;
import br.com.fiap.controletransacoes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that implements Cliente operations
 *
 */
@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<ClienteDTO> getAll() {
        return clienteRepository.findAll().stream().map(ClienteDTO::new).collect(Collectors.toList());
    }

    @Override
    public ClienteDTO getByCpf(String cpf) {
        ClienteEntity clienteEntity = clienteRepository.findById(cpf).get();
        return new ClienteDTO(clienteEntity);
    }

    @Override
    public ClienteDTO salvarCliente(ClienteDTO clienteDTO) {
        ClienteEntity clienteEntity = clienteRepository.save(new ClienteEntity(clienteDTO));
        return new ClienteDTO(clienteEntity);
    }

    @Override
    public ClienteDTO atualizarCliente(String cpf, ClienteDTO clienteDTO) {
        ClienteEntity clienteEntity = clienteRepository.findById(cpf).get();

        clienteEntity.setNome(clienteDTO.getNome());

        clienteRepository.save(clienteEntity);

        return new ClienteDTO(clienteEntity);
    }

    @Override
    public void deletarCliente(String cpf) {

        clienteRepository.deleteById(cpf);

    }
}
