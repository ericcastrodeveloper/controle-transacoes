package br.com.fiap.controletransacoes.service;

import br.com.fiap.controletransacoes.dto.ClienteDTO;
import br.com.fiap.controletransacoes.dto.CreateTransacaoDTO;
import br.com.fiap.controletransacoes.dto.ProdutoDTO;
import br.com.fiap.controletransacoes.dto.TransacaoDTO;
import br.com.fiap.controletransacoes.entity.ClienteEntity;
import br.com.fiap.controletransacoes.entity.ProdutoEntity;
import br.com.fiap.controletransacoes.entity.TransacaoEntity;
import br.com.fiap.controletransacoes.repository.ClienteRepository;
import br.com.fiap.controletransacoes.repository.TransacaoRepository;
import br.com.fiap.controletransacoes.service.impl.TransacaoImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


public class TransacaoServiceTest {

    @Mock
    private TransacaoRepository transacaoRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private TransacaoService transacaoService = new TransacaoImpl();

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllTest(){
        when(transacaoRepository.findAll()).thenReturn(new ArrayList<>());
        List<TransacaoDTO> retorno = transacaoService.getAll();

        Assert.assertNotNull(retorno);
    }

    @Test
    public void getByClienteTest(){
        when(transacaoRepository.findByClienteCpf(anyString())).thenReturn(new ArrayList<>());
        List<TransacaoDTO> retorno = transacaoService.getByCliente("12345678910");

        Assert.assertNotNull(retorno);
    }

    @Test
    public void salvarTransacaoTest(){
        when(clienteRepository.findById(anyString())).thenReturn(Optional.of(getClienteEntityMock()));
        when(transacaoRepository.save(any(TransacaoEntity.class))).thenReturn(getTransacaoEntityMock());

        TransacaoDTO retorno = transacaoService.salvarTransacao(getCreateTransacaoDTOMock());

        Assert.assertNotNull(retorno);
    }


    @Test
    public void atualizarTransacaoTest(){
        when(transacaoRepository.findById(any(Integer.class))).thenReturn(Optional.of(getTransacaoEntityMock()));
        when(transacaoRepository.save(any(TransacaoEntity.class))).thenReturn(getTransacaoEntityMock());
        TransacaoDTO retorno = transacaoService.atualizarTransacao(1, getTransacaoDTOMock());

        Assert.assertNotNull(retorno);
    }

    @Test
    public void deletarTransacaoTest(){
        when(transacaoRepository.findById(any(Integer.class))).thenReturn(Optional.of(getTransacaoEntityMock()));
        doNothing().when(transacaoRepository).deleteById(any(Integer.class));
        transacaoService.deletarTransacao(1);
    }

    @Test
    public void getExtratoTest() throws IOException {

        List<TransacaoEntity> listTransacaoEntity = new ArrayList<>();
        listTransacaoEntity.add(getTransacaoEntityMock());
        ReflectionTestUtils.setField(transacaoService, "extratoFile", "extrato.json");
        when(transacaoRepository.findByClienteCpf(anyString())).thenReturn(listTransacaoEntity);

        transacaoService.getExtrato("12345678910");
    }

    @Test
    public void getExtratoTestEmpty() throws IOException {

        List<TransacaoEntity> listTransacaoEntity = new ArrayList<>();
        when(transacaoRepository.findByClienteCpf(anyString())).thenReturn(listTransacaoEntity);

        transacaoService.getExtrato("12345678910");
    }

    @Test(expected = Exception.class)
    public void getExtratoTestThrowFileNotFoundException() throws IOException {
        List<TransacaoEntity> listTransacaoEntity = new ArrayList<>();
        listTransacaoEntity.add(getTransacaoEntityMock());
        ReflectionTestUtils.setField(transacaoService, "extratoFile", "");
        when(transacaoRepository.findByClienteCpf(anyString())).thenReturn(listTransacaoEntity);

        transacaoService.getExtrato("12345678910");
    }

    private CreateTransacaoDTO getCreateTransacaoDTOMock() {
        CreateTransacaoDTO transacaoDTO = new CreateTransacaoDTO();
        transacaoDTO.setListProdutoDTO(getProdutoDTOListMock());
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setCpf("12345678910");
        transacaoDTO.setCpf(clienteDTO.getCpf());
        return transacaoDTO;
    }

    private TransacaoEntity getTransacaoEntityMock(){
        TransacaoEntity transacaoEntity = new TransacaoEntity();
        transacaoEntity.setCliente(getClienteEntityMock());
        transacaoEntity.setListaProduto(getProdutoEntityListMock());
        transacaoEntity.setDataTransacao(new Date());
        return transacaoEntity;
    }

    private TransacaoDTO getTransacaoDTOMock(){
        TransacaoDTO transacaoDTO = new TransacaoDTO();
        transacaoDTO.setListProdutoDTO(getProdutoDTOListMock());
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setCpf("12345678910");
        clienteDTO.setNome("teste");
        transacaoDTO.setClienteDTO(clienteDTO);
        transacaoDTO.setDataTransacao(new Date().toString());
        transacaoDTO.toString();
        return transacaoDTO;
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

    private ClienteEntity getClienteEntityMock(){
        ClienteEntity clienteEntity = new ClienteEntity();

        clienteEntity.setNome("teste");
        clienteEntity.setCpf("12345678910");
        return clienteEntity;
    }
}
