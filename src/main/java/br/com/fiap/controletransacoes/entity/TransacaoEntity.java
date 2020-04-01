package br.com.fiap.controletransacoes.entity;


import br.com.fiap.controletransacoes.dto.CreateTransacaoDTO;
import br.com.fiap.controletransacoes.dto.ProdutoDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class that represents a Transacao Entity.
 *
 */
@Getter
@Setter
@Table(name = "TB_TRANSACAO")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class TransacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private ClienteEntity cliente;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="TB_TRANSACAO_PRODUTO",
            joinColumns={@JoinColumn(name="TRANSACAO_ID")},
            inverseJoinColumns={@JoinColumn(name="PRODUTO_ID")})
    private List<ProdutoEntity> listaProduto;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cartao_id")
    private CartaoEntity cartaoEntity;
    @Column(name = "data_transacao")
    @CreatedDate
    private Date dataTransacao;
    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    public TransacaoEntity() {
    }


    public TransacaoEntity(CreateTransacaoDTO transacaoDTO){
        this.setCliente(new ClienteEntity());
        this.getCliente().setCpf(transacaoDTO.getCpf());

        List<ProdutoEntity> listProdutoEntity = new ArrayList<>();
        for(ProdutoDTO produtoDTO : transacaoDTO.getListProdutoDTO()){
            ProdutoEntity produtoEntity = new ProdutoEntity();
            produtoEntity.setNome(produtoDTO.getNome());
            produtoEntity.setValor(produtoDTO.getValor());
            produtoEntity.setQuantidade(produtoDTO.getQuantidade());

            listProdutoEntity.add(produtoEntity);
        }
        this.cartaoEntity = new CartaoEntity(transacaoDTO.getCartaoDTO());
        this.setListaProduto(listProdutoEntity);

    }


}
