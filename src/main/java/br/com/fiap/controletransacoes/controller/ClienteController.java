package br.com.fiap.controletransacoes.controller;

import br.com.fiap.controletransacoes.dto.ClienteDTO;
import br.com.fiap.controletransacoes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
/**
 * Cliente Rest Controller
 * @name ClienteController
 * @contextPath /clientes
 */
@RestController
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    /**
     * Retrieves information about all Cliente.
     */
    @GetMapping
    public List<ClienteDTO> getAll(){
     return clienteService.getAll();
    }

    /**
     * Retrieves information about a Cliente.
     * @param cpf identifier to be retrieved
     */
    @GetMapping("{cpf}")
    public ClienteDTO getByCpf(@PathVariable String cpf){
        return clienteService.getByCpf(cpf);
    }

    /**
     * Add a new Cliente.
     * @param clienteDTO value of client to be stored
     *
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDTO salvarCliente(@RequestBody @Valid ClienteDTO clienteDTO){
        return clienteService.salvarCliente(clienteDTO);
    }

    /**
     * Update a Cliente.
     * @param cpf Cpf of the Cliente to updated
     * @param clienteDTO value of client to be stored
     *
     */
    @PutMapping("{cpf}")
    public ClienteDTO atualizarCliente(@PathVariable String cpf, @RequestBody ClienteDTO clienteDTO){
        return clienteService.atualizarCliente(cpf, clienteDTO);
    }

    /**
     * Delete a Cliente.
     * @param cpf Cpf of the Cliente to deleted
     *
     */
    @DeleteMapping("{cpf}")
    public void deletarCliente(@PathVariable String cpf){
        clienteService.deletarCliente(cpf);
    }


}
