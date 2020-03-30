package br.com.fiap.controletransacoes.mapper;

import br.com.fiap.controletransacoes.dto.ProdutoDTO;
import br.com.fiap.controletransacoes.entity.ProdutoEntity;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProdutoMapperTest {

    @Test
    public void toDtoTest(){
        List<ProdutoEntity> listProdutoEntity = new ArrayList<>();
        ProdutoEntity produtoEntity = new ProdutoEntity();
        produtoEntity.setId(1);
        produtoEntity.setNome("Teste");
        produtoEntity.setValor(BigDecimal.valueOf(5000));
        produtoEntity.setQuantidade(1);
        listProdutoEntity.add(produtoEntity);
        ProdutoMapper.toDto(listProdutoEntity);
    }

    @Test
    public void toEntityTest(){
        List<ProdutoDTO> listProdutoDTO = new ArrayList<>();
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setNome("Teste");
        produtoDTO.setValor(BigDecimal.valueOf(5000));
        produtoDTO.setQuantidade(1);
        listProdutoDTO.add(produtoDTO);
        ProdutoMapper.toEntity(listProdutoDTO);
    }
}
