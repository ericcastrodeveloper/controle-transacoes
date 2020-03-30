package br.com.fiap.controletransacoes.entity;

import br.com.fiap.controletransacoes.dto.ClienteDTO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "TB_CLIENTE")
@Entity
public class ClienteEntity {

    @Id
    private String cpf;
    private String nome;

    public ClienteEntity() {
    }

    public ClienteEntity(ClienteDTO clienteDTO){
        this.cpf = clienteDTO.getCpf();
        this.nome = clienteDTO.getNome();
    }

}
