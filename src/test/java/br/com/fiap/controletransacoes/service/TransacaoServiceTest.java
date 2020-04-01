package br.com.fiap.controletransacoes.service;

import br.com.fiap.controletransacoes.dto.TransacaoDTO;
import br.com.fiap.controletransacoes.entity.TransacaoEntity;
import br.com.fiap.controletransacoes.repository.ClienteRepository;
import br.com.fiap.controletransacoes.repository.TransacaoRepository;
import br.com.fiap.controletransacoes.service.impl.TransacaoImpl;
import br.com.fiap.controletransacoes.utils.MockObjectsUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.util.ArrayList;
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
        when(clienteRepository.findById(anyString())).thenReturn(Optional.of(MockObjectsUtils.getClienteEntityMock()));
        when(transacaoRepository.save(any(TransacaoEntity.class))).thenReturn(MockObjectsUtils.getTransacaoEntityMock());

        TransacaoDTO retorno = transacaoService.salvarTransacao(MockObjectsUtils.getCreateTransacaoDTOMock());

        Assert.assertNotNull(retorno);
    }


    @Test
    public void atualizarTransacaoTest(){
        when(transacaoRepository.findById(any(Integer.class))).thenReturn(Optional.of(MockObjectsUtils.getTransacaoEntityMock()));
        when(transacaoRepository.save(any(TransacaoEntity.class))).thenReturn(MockObjectsUtils.getTransacaoEntityMock());
        TransacaoDTO retorno = transacaoService.atualizarTransacao(1, MockObjectsUtils.getTransacaoDTOMock());

        Assert.assertNotNull(retorno);
    }

    @Test
    public void deletarTransacaoTest(){
        when(transacaoRepository.findById(any(Integer.class))).thenReturn(Optional.of(MockObjectsUtils.getTransacaoEntityMock()));
        doNothing().when(transacaoRepository).deleteById(any(Integer.class));
        transacaoService.deletarTransacao(1);
    }

    @Test
    public void getExtratoTest() throws IOException {

        List<TransacaoEntity> listTransacaoEntity = new ArrayList<>();
        listTransacaoEntity.add(MockObjectsUtils.getTransacaoEntityMock());
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
        listTransacaoEntity.add(MockObjectsUtils.getTransacaoEntityMock());
        ReflectionTestUtils.setField(transacaoService, "extratoFile", "");
        when(transacaoRepository.findByClienteCpf(anyString())).thenReturn(listTransacaoEntity);

        transacaoService.getExtrato("12345678910");
    }

    @Test
    public void getExtratoCartaoTest() throws IOException {

        List<TransacaoEntity> listTransacaoEntity = new ArrayList<>();
        listTransacaoEntity.add(MockObjectsUtils.getTransacaoEntityMock());
        ReflectionTestUtils.setField(transacaoService, "extratoFile", "extrato.json");
        when(transacaoRepository.findByCartaoEntityId(any(Integer.class))).thenReturn(listTransacaoEntity);

        transacaoService.getExtratoByCartao(1);
    }

    @Test
    public void getExtratoCartaoTestEmpty() throws IOException {

        List<TransacaoEntity> listTransacaoEntity = new ArrayList<>();
        when(transacaoRepository.findByCartaoEntityId(any(Integer.class))).thenReturn(listTransacaoEntity);

        transacaoService.getExtratoByCartao(1);
    }

    @Test(expected = Exception.class)
    public void getExtratoCartaoTestThrowFileNotFoundException() throws IOException {
        List<TransacaoEntity> listTransacaoEntity = new ArrayList<>();
        listTransacaoEntity.add(MockObjectsUtils.getTransacaoEntityMock());
        ReflectionTestUtils.setField(transacaoService, "extratoFile", "");
        when(transacaoRepository.findByCartaoEntityId(any(Integer.class))).thenReturn(listTransacaoEntity);

        transacaoService.getExtratoByCartao(1);
    }

}
