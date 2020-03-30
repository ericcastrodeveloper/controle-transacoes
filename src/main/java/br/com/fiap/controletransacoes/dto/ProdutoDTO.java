package br.com.fiap.controletransacoes.dto;

import br.com.fiap.controletransacoes.entity.ProdutoEntity;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutoDTO {

    private String nome;
    private BigDecimal valor;
    private Integer quantidade;

    public ProdutoDTO() {
    }

    public ProdutoDTO(ProdutoEntity produtoEntity){
        this.nome = produtoEntity.getNome();
        this.valor = produtoEntity.getValor();
        this.quantidade = produtoEntity.getQuantidade();
    }

    @Override
    public String toString() {
        return "Produto {" +
                "nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                '}';
    }
}
