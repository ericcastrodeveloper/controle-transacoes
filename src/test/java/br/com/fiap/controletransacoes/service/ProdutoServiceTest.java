package br.com.fiap.controletransacoes.service;

import br.com.fiap.controletransacoes.dto.ProdutoDTO;
import br.com.fiap.controletransacoes.entity.ProdutoEntity;
import br.com.fiap.controletransacoes.repository.ProdutoRepository;
import br.com.fiap.controletransacoes.service.impl.ProdutoServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService = new ProdutoServiceImpl();

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllTest(){
        when(produtoRepository.findAll()).thenReturn(getProdutoEntityListMock());
        List<ProdutoDTO> retorno = produtoService.getAll();
        Assert.assertNotNull(retorno);

    }

    @Test
    public void getByIdTest(){
        when(produtoRepository.findById(any(Integer.class))).thenReturn(Optional.of(getProdutoEntityMock()));
        ProdutoDTO retorno = produtoService.getById(1);
        Assert.assertNotNull(retorno);
    }

    @Test
    public void salvarProdutoTest(){
        when(produtoRepository.save(any(ProdutoEntity.class))).thenReturn(getProdutoEntityMock());
        ProdutoDTO retorno = produtoService.salvarProduto(getProdutoDTOMock());
        Assert.assertNotNull(retorno);

    }

    @Test
    public void updateProdutoTest(){
        when(produtoRepository.findById(any(Integer.class))).thenReturn(Optional.of(getProdutoEntityMock()));
        when(produtoRepository.save(any(ProdutoEntity.class))).thenReturn(getProdutoEntityMock());
        ProdutoDTO retorno = produtoService.atualizarProduto(1, getProdutoDTOMock());
        Assert.assertNotNull(retorno);
    }

    @Test
    public void deleteProdutoTest(){
        when(produtoRepository.findById(any(Integer.class))).thenReturn(Optional.of(getProdutoEntityMock()));
        doNothing().when(produtoRepository).deleteById(any(Integer.class));
        produtoService.deletarProduto(1);
    }

    private ProdutoEntity getProdutoEntityMock(){
        ProdutoEntity produtoEntity = new ProdutoEntity();
        produtoEntity.setQuantidade(5);
        produtoEntity.setValor(BigDecimal.valueOf(5000.00));
        produtoEntity.setNome("Teste");
        produtoEntity.setId(1);
        return produtoEntity;
    }

    private ProdutoDTO getProdutoDTOMock(){
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setNome("Teste");
        produtoDTO.setQuantidade(5);
        produtoDTO.setValor(BigDecimal.valueOf(5000.00));
        return produtoDTO;
    }

    private List<ProdutoDTO> getProdutoDTOListMock() {

        List<ProdutoDTO> listProdutoDTO = new ArrayList<>();

        for(int i= 0; i < 10; i++){
            ProdutoDTO produtoDTO = new ProdutoDTO();
            produtoDTO.setValor(BigDecimal.valueOf(i));
            produtoDTO.setQuantidade(i);
            produtoDTO.setNome("Produto "+i);
            listProdutoDTO.add(produtoDTO);
        }
        return listProdutoDTO;
    }

    private List<ProdutoEntity> getProdutoEntityListMock() {

        List<ProdutoEntity> listProdutoEntity = new ArrayList<>();

        for(int i= 0; i < 10; i++){
            ProdutoEntity produtoEntity = new ProdutoEntity();
            produtoEntity.setValor(BigDecimal.valueOf(i));
            produtoEntity.setQuantidade(i);
            produtoEntity.setNome("Produto "+i);
            listProdutoEntity.add(produtoEntity);
        }
        return listProdutoEntity;
    }
}
