package br.com.fiap.controletransacoes.dto;

import br.com.fiap.controletransacoes.entity.ProdutoEntity;
import br.com.fiap.controletransacoes.entity.TransacaoEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class TransacaoDTO {
    @SerializedName("cliente")
    @JsonProperty("cliente")
    private ClienteDTO clienteDTO;
    @SerializedName("lista_produtos")
    @JsonProperty("lista_produtos")
    private List<ProdutoDTO> listProdutoDTO;
    @SerializedName("data_transacao")
    @JsonProperty("data_transacao")
    private String dataTransacao;
    @SerializedName("valor_total")
    @JsonProperty("valor_total")
    private BigDecimal valorTotal;

    public TransacaoDTO() {
    }


    public TransacaoDTO(TransacaoEntity transacaoEntity) {
        this.setClienteDTO(new ClienteDTO());
        this.getClienteDTO().setCpf(transacaoEntity.getCliente().getCpf());
        this.getClienteDTO().setNome(transacaoEntity.getCliente().getNome());

        List<ProdutoDTO> listaProdutoDTO = new ArrayList<>();

        for(ProdutoEntity produtoEntity : transacaoEntity.getListaProduto()){
            ProdutoDTO produtoDTO = new ProdutoDTO();
            produtoDTO.setNome(produtoEntity.getNome());
            produtoDTO.setQuantidade(produtoEntity.getQuantidade());
            produtoDTO.setValor(produtoEntity.getValor());
            listaProdutoDTO.add(produtoDTO);
        }

        this.setListProdutoDTO(listaProdutoDTO);

        this.setValorTotal(transacaoEntity.getValorTotal());
        this.setDataTransacao((transacaoEntity.getDataTransacao().toString()));
    }

    @Override
    public String toString() {
        return "Transacao:" +
                "\r\n" + clienteDTO +
                "\r\n" + listProdutoDTO +
                "\r\ndataTransacao=" + dataTransacao +
                "\r\n";
    }
}
