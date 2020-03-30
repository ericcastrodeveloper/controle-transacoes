package br.com.fiap.controletransacoes.service;

import br.com.fiap.controletransacoes.dto.ClienteDTO;
import br.com.fiap.controletransacoes.entity.ClienteEntity;
import br.com.fiap.controletransacoes.repository.ClienteRepository;
import br.com.fiap.controletransacoes.service.impl.ClienteServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService = new ClienteServiceImpl();

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllTest(){
        when(clienteRepository.findAll()).thenReturn(getClienteEntityListMock());
        List<ClienteDTO> retorno = clienteService.getAll();
        Assert.assertNotNull(retorno);

    }

    @Test
    public void getByIdTest(){
        when(clienteRepository.findById(anyString())).thenReturn(Optional.of(getClienteEntityMock()));
        ClienteDTO retorno = clienteService.getByCpf("12345678910");
        Assert.assertNotNull(retorno);
    }

    @Test
    public void salvarProdutoTest(){
        when(clienteRepository.save(any(ClienteEntity.class))).thenReturn(getClienteEntityMock());
        ClienteDTO retorno = clienteService.salvarCliente(getClienteDTOMock());
        Assert.assertNotNull(retorno);

    }

    @Test
    public void updateProdutoTest(){
        when(clienteRepository.findById(anyString())).thenReturn(Optional.of(getClienteEntityMock()));
        when(clienteRepository.save(any(ClienteEntity.class))).thenReturn(getClienteEntityMock());
        ClienteDTO retorno = clienteService.atualizarCliente("12345678910", getClienteDTOMock());
        Assert.assertNotNull(retorno);
    }

    @Test
    public void deleteProdutoTest(){
        when(clienteRepository.findById(anyString())).thenReturn(Optional.of(getClienteEntityMock()));
        doNothing().when(clienteRepository).deleteById(anyString());
        clienteService.deletarCliente("12345678910");
    }

    private List<ClienteEntity> getClienteEntityListMock(){

        List<ClienteEntity> listaClienteEntity = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            ClienteEntity clienteEntity = new ClienteEntity();
            clienteEntity.setCpf("1234567891"+i);
            clienteEntity.setNome("Teste_"+i);
            listaClienteEntity.add(clienteEntity);
        }
        return listaClienteEntity;
    }

    private List<ClienteDTO> getClienteDTOListMock(){

        List<ClienteDTO> listaClienteEntity = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setCpf("1234567891"+i);
            clienteDTO.setNome("Teste_"+i);
            listaClienteEntity.add(clienteDTO);
        }
        return listaClienteEntity;
    }

    private ClienteEntity getClienteEntityMock(){
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setNome("Test");
        clienteEntity.setCpf("12345678910");
        return clienteEntity;
    }

    private ClienteDTO getClienteDTOMock(){
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setCpf("12345678910");
        clienteDTO.setNome("Teste");
        return clienteDTO;
    }
}
