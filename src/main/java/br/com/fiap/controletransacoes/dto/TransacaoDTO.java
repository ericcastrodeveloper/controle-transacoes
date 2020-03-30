package br.com.fiap.controletransacoes.dto;

import br.com.fiap.controletransacoes.entity.ProdutoEntity;
import br.com.fiap.controletransacoes.entity.TransacaoEntity;
import lombok.Data;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class TransacaoDTO {

    private ClienteDTO clienteDTO;
    private List<ProdutoDTO> listProdutoDTO;
    private ZonedDateTime dataTransacao;

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

        this.setDataTransacao(convertToZonedDateTime(transacaoEntity.getDataTransacao()));
    }

    private ZonedDateTime convertToZonedDateTime(Date dataCriacao) {
        if(dataCriacao!= null)
            return ZonedDateTime.ofInstant(dataCriacao.toInstant(), ZoneOffset.systemDefault());
        else
            return null;
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
