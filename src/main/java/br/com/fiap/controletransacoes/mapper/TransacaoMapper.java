package br.com.fiap.controletransacoes.mapper;

import br.com.fiap.controletransacoes.dto.CartaoDTO;
import br.com.fiap.controletransacoes.dto.ClienteDTO;
import br.com.fiap.controletransacoes.dto.ProdutoDTO;
import br.com.fiap.controletransacoes.dto.TransacaoDTO;
import br.com.fiap.controletransacoes.entity.ProdutoEntity;
import br.com.fiap.controletransacoes.entity.TransacaoEntity;

import java.util.ArrayList;
import java.util.List;

public class TransacaoMapper {

    public static List<TransacaoDTO> toDtoList(List<TransacaoEntity> listaTransacaoEntity){
        List<TransacaoDTO> listTransacaoDTO = new ArrayList<>();

        for(TransacaoEntity transacaoEntity : listaTransacaoEntity){

            TransacaoDTO transacaoDTO = new TransacaoDTO();

            transacaoDTO.setClienteDTO(new ClienteDTO());
            transacaoDTO.getClienteDTO().setCpf(transacaoEntity.getCliente().getCpf());
            transacaoDTO.getClienteDTO().setNome(transacaoEntity.getCliente().getNome());

            List<ProdutoDTO> listaProdutoDTO = new ArrayList<>();

            for(ProdutoEntity produtoEntity : transacaoEntity.getListaProduto()){
                ProdutoDTO produtoDTO = new ProdutoDTO();
                produtoDTO.setNome(produtoEntity.getNome());
                produtoDTO.setQuantidade(produtoEntity.getQuantidade());
                produtoDTO.setValor(produtoEntity.getValor());
                listaProdutoDTO.add(produtoDTO);
            }

            transacaoDTO.setCartaoDTO(new CartaoDTO(transacaoEntity.getCartaoEntity()));

            transacaoDTO.setListProdutoDTO(listaProdutoDTO);

            transacaoDTO.setValorTotal(transacaoEntity.getValorTotal());
            transacaoDTO.setDataTransacao((transacaoEntity.getDataTransacao().toString()));

            listTransacaoDTO.add(transacaoDTO);
        }
        return listTransacaoDTO;
    }
}
