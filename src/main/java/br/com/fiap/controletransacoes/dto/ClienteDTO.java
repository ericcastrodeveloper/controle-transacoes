package br.com.fiap.controletransacoes.dto;

import br.com.fiap.controletransacoes.entity.ClienteEntity;
import lombok.Data;
/**
 * Data Transfer Object of ClienteEntity.
 *
 */
@Data
public class ClienteDTO {

    private String nome;
    private String cpf;

    public ClienteDTO() {
    }

    public ClienteDTO(ClienteEntity clienteEntity){
        this.nome = clienteEntity.getNome();
        this.cpf = clienteEntity.getCpf();
    }

    @Override
    public String toString() {
        return "Cliente {" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}
