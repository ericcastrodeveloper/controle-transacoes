package br.com.fiap.controletransacoes.mapper;

import br.com.fiap.controletransacoes.dto.ProdutoDTO;
import br.com.fiap.controletransacoes.entity.ProdutoEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that map a Produto to ProdutoDTO
 * or a ProdutoDTO to a Produto.
 */
public class ProdutoMapper {

    public static List<ProdutoDTO> toDto(List<ProdutoEntity> listaProdutoEntity){

        List<ProdutoDTO> listaProdutoDTO = new ArrayList<>();

        for(ProdutoEntity produtoEntity : listaProdutoEntity){
            ProdutoDTO produtoDTO = new ProdutoDTO();
            produtoDTO.setNome(produtoEntity.getNome());
            produtoDTO.setQuantidade(produtoEntity.getQuantidade());
            produtoDTO.setValor(produtoEntity.getValor());
            listaProdutoDTO.add(produtoDTO);
        }

        return listaProdutoDTO;
    }

    public static List<ProdutoEntity> toEntity(List<ProdutoDTO> listaProdutoDTO){
        List<ProdutoEntity> listProdutoEntity = new ArrayList<>();
        for(ProdutoDTO produtoDTO : listaProdutoDTO){
            ProdutoEntity produtoEntity = new ProdutoEntity();
            produtoEntity.setNome(produtoDTO.getNome());
            produtoEntity.setValor(produtoDTO.getValor());
            produtoEntity.setQuantidade(produtoDTO.getQuantidade());

            listProdutoEntity.add(produtoEntity);
        }
        return listProdutoEntity;
    }
}
