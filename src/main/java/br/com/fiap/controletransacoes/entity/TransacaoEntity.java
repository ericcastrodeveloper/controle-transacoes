package br.com.fiap.controletransacoes.entity;


import br.com.fiap.controletransacoes.dto.ProdutoDTO;
import br.com.fiap.controletransacoes.dto.TransacaoDTO;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
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
    private List<ProdutoEntity> listaProduto;
    @Column(name = "data_transacao")
    @CreatedDate
    private Date dataTransacao;

    public TransacaoEntity() {
    }

    public TransacaoEntity(TransacaoDTO transacaoDTO){
        this.setCliente(new ClienteEntity());
        this.getCliente().setCpf(transacaoDTO.getClienteDTO().getCpf());
        this.getCliente().setNome(transacaoDTO.getClienteDTO().getNome());

        List<ProdutoEntity> listProdutoEntity = new ArrayList<>();
        for(ProdutoDTO produtoDTO : transacaoDTO.getListProdutoDTO()){
            ProdutoEntity produtoEntity = new ProdutoEntity();
            produtoEntity.setNome(produtoDTO.getNome());
            produtoEntity.setValor(produtoDTO.getValor());
            produtoEntity.setQuantidade(produtoDTO.getQuantidade());

            listProdutoEntity.add(produtoEntity);
        }
        this.setListaProduto(listProdutoEntity);

    }


}
