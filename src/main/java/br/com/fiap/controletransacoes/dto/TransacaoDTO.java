package br.com.fiap.controletransacoes.dto;

import br.com.fiap.controletransacoes.entity.TransacaoEntity;
import lombok.Data;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

@Data
public class TransacaoDTO {

    private ClienteDTO clienteDTO;
    private ProdutoDTO produtoDTO;
    private ZonedDateTime dataTransacao;

    public TransacaoDTO() {
    }

    public TransacaoDTO(ClienteDTO clienteDTO, ProdutoDTO produtoDTO) {
        this.clienteDTO = clienteDTO;
        this.produtoDTO = produtoDTO;
    }

    public TransacaoDTO(TransacaoEntity transacaoEntity) {
        this.setClienteDTO(new ClienteDTO());
        this.setProdutoDTO(new ProdutoDTO());
        this.getClienteDTO().setCpf(transacaoEntity.getCliente().getCpf());
        this.getClienteDTO().setNome(transacaoEntity.getCliente().getNome());
        this.getProdutoDTO().setNome(transacaoEntity.getProduto().getNome());
        this.getProdutoDTO().setQuantidade(transacaoEntity.getProduto().getQuantidade());
        this.getProdutoDTO().setValor(transacaoEntity.getProduto().getValor());
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
                "\r\n" + produtoDTO +
                "\r\ndataTransacao=" + dataTransacao +
                "\r\n";
    }
}
