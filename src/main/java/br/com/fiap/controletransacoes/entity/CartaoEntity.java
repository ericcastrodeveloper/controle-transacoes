package br.com.fiap.controletransacoes.entity;

import br.com.fiap.controletransacoes.dto.CartaoDTO;
import br.com.fiap.controletransacoes.enums.TipoCartao;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Class that represents a Cartao Entity.
 *
 */
@Getter
@Setter
@Entity
@Table(name = "TB_CARTAO")
public class CartaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String numero;
    @Column(name = "nome_titular")
    private String nomeTitular;
    private String bandeira;
    @Column(name = "mes_ano_validade")
    private String mesAnoValidade;
    @Column(name = "codigo_seguranca")
    private String codigoSeguranca;
    @Column(name = "tipo_cartao")
    private TipoCartao tipoCartao;
    private String agencia;
    private String conta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cartaoEntity")
    private List<TransacaoEntity> listaTransacoes;

    public CartaoEntity() {
    }

    public CartaoEntity (CartaoDTO cartaoDTO){
        this.setNomeTitular(cartaoDTO.getNomeTitular());
        this.setNumero(cartaoDTO.getNumero());
        this.setBandeira(cartaoDTO.getBandeira());
        this.setMesAnoValidade(cartaoDTO.getMesAnoValidade());
        this.setCodigoSeguranca(cartaoDTO.getCodigoSeguranca());
        this.setTipoCartao(cartaoDTO.getTipoCartao());
        this.setAgencia(cartaoDTO.getAgencia());
        this.setConta(cartaoDTO.getConta());

    }
}
