package br.com.fiap.controletransacoes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class CreateTransacaoDTO {

    @SerializedName("cliente_cpf")
    @JsonProperty("cliente_cpf")
    private String cpf;
    @SerializedName("lista_produtos")
    @JsonProperty("lista_produtos")
    private List<ProdutoDTO> listProdutoDTO;

}
