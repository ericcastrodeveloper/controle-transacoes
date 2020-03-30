package br.com.fiap.controletransacoes.entity;


import br.com.fiap.controletransacoes.dto.TransacaoDTO;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

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
    @ManyToOne(cascade = CascadeType.ALL)
    private ProdutoEntity produto;
    @Column(name = "data_transacao")
    @CreatedDate
    private Date dataTransacao;

    public TransacaoEntity() {
    }

    public TransacaoEntity(TransacaoDTO transacaoDTO){
        this.setCliente(new ClienteEntity());
        this.setProduto(new ProdutoEntity());
        this.getCliente().setCpf(transacaoDTO.getClienteDTO().getCpf());
        this.getCliente().setNome(transacaoDTO.getClienteDTO().getNome());
        this.getProduto().setNome(transacaoDTO.getProdutoDTO().getNome());
        this.getProduto().setValor(transacaoDTO.getProdutoDTO().getValor());
        this.getProduto().setQuantidade(transacaoDTO.getProdutoDTO().getQuantidade());
    }


}
