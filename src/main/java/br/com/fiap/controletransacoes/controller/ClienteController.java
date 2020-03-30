package br.com.fiap.controletransacoes.controller;

import br.com.fiap.controletransacoes.dto.ClienteDTO;
import br.com.fiap.controletransacoes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<ClienteDTO> getAll(){
     return clienteService.getAll();
    }

    @GetMapping("{cpf}")
    public ClienteDTO getByCpf(@PathVariable String cpf){
        return clienteService.getByCpf(cpf);
    }

    @PostMapping
    public ClienteDTO salvarCliente(@RequestBody ClienteDTO clienteDTO){
        return clienteService.salvarCliente(clienteDTO);
    }

    @PutMapping("{cpf}")
    public ClienteDTO atualizarCliente(@PathVariable String cpf, @RequestBody ClienteDTO clienteDTO){
        return clienteService.atualizarCliente(cpf, clienteDTO);
    }
    @DeleteMapping("{cpf}")
    public void deletarCliente(@PathVariable String cpf){
        clienteService.deletarCliente(cpf);
    }


}
