package br.com.fiap.controletransacoes.entity;

import br.com.fiap.controletransacoes.dto.ProdutoDTO;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Table(name = "TB_PRODUTO")
@Entity
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private BigDecimal valor;
    private Integer quantidade;

    public ProdutoEntity() {
    }

    public ProdutoEntity(ProdutoDTO produtoDTO){
        this.nome = produtoDTO.getNome();
        this.valor = produtoDTO.getValor();
        this.quantidade = produtoDTO.getQuantidade();
    }
}
