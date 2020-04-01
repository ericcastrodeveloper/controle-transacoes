package br.com.fiap.controletransacoes.service;

import br.com.fiap.controletransacoes.dto.ProdutoDTO;
import br.com.fiap.controletransacoes.entity.ProdutoEntity;
import br.com.fiap.controletransacoes.repository.ProdutoRepository;
import br.com.fiap.controletransacoes.service.impl.ProdutoServiceImpl;
import br.com.fiap.controletransacoes.utils.MockObjectsUtils;
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
        when(produtoRepository.findAll()).thenReturn(MockObjectsUtils.getProdutoEntityListMock());
        List<ProdutoDTO> retorno = produtoService.getAll();
        Assert.assertNotNull(retorno);

    }

    @Test
    public void getByIdTest(){
        when(produtoRepository.findById(any(Integer.class))).thenReturn(Optional.of(MockObjectsUtils.getProdutoEntityMock()));
        ProdutoDTO retorno = produtoService.getById(1);
        Assert.assertNotNull(retorno);
    }

    @Test
    public void salvarProdutoTest(){
        when(produtoRepository.save(any(ProdutoEntity.class))).thenReturn(MockObjectsUtils.getProdutoEntityMock());
        ProdutoDTO retorno = produtoService.salvarProduto(MockObjectsUtils.getProdutoDTOMock());
        Assert.assertNotNull(retorno);

    }

    @Test
    public void updateProdutoTest(){
        when(produtoRepository.findById(any(Integer.class))).thenReturn(Optional.of(MockObjectsUtils.getProdutoEntityMock()));
        when(produtoRepository.save(any(ProdutoEntity.class))).thenReturn(MockObjectsUtils.getProdutoEntityMock());
        ProdutoDTO retorno = produtoService.atualizarProduto(1, MockObjectsUtils.getProdutoDTOMock());
        Assert.assertNotNull(retorno);
    }

    @Test
    public void deleteProdutoTest(){
        when(produtoRepository.findById(any(Integer.class))).thenReturn(Optional.of(MockObjectsUtils.getProdutoEntityMock()));
        doNothing().when(produtoRepository).deleteById(any(Integer.class));
        produtoService.deletarProduto(1);
    }


}
