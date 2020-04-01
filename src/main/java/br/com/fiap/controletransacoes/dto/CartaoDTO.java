package br.com.fiap.controletransacoes.dto;

import br.com.fiap.controletransacoes.entity.CartaoEntity;
import br.com.fiap.controletransacoes.enums.TipoCartao;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Data Transfer Object of CartaoEntity.
 *
 */
@Data
public class CartaoDTO {

    @SerializedName("nome_titular")
    @JsonProperty("nome_titular")
    private String nomeTitular;
    private String numero;
    private String bandeira;
    @SerializedName("mes_ano_validade")
    @JsonProperty("mes_ano_validade")
    private String mesAnoValidade;
    @SerializedName("codigo_seguranca")
    @JsonProperty("codigo_seguranca")
    private String codigoSeguranca;
    private String agencia;
    private String conta;
    @SerializedName("tipo_cartao")
    @JsonProperty("tipo_cartao")
    private TipoCartao tipoCartao;

    public CartaoDTO() {
    }

    public CartaoDTO(CartaoEntity cartaoEntity){
        this.nomeTitular = cartaoEntity.getNomeTitular();
        this.numero = cartaoEntity.getNumero();
        this.bandeira = cartaoEntity.getBandeira();
        this.mesAnoValidade = cartaoEntity.getMesAnoValidade();
        this.codigoSeguranca = cartaoEntity.getCodigoSeguranca();
        this.agencia = cartaoEntity.getAgencia();
        this.conta = cartaoEntity.getConta();
        this.tipoCartao = cartaoEntity.getTipoCartao();
    }
}
