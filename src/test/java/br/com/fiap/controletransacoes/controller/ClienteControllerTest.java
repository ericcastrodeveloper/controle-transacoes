package br.com.fiap.controletransacoes.controller;

import br.com.fiap.controletransacoes.dto.ClienteDTO;
import br.com.fiap.controletransacoes.entity.ClienteEntity;
import br.com.fiap.controletransacoes.repository.ClienteRepository;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties ="spring.datasource.url=jdbc:h2:mem:db;DB_CLOSE_ON_EXIT=FALSE")
public class ClienteControllerTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClienteController clienteController;


    @Test
    public void getAllTest() throws Exception {

        ClienteEntity clienteEntity = getClienteEntityMock();
        clienteRepository.save(clienteEntity);

        this.mockMvc.perform(get("/clientes")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getByIdTest() throws Exception {

        ClienteEntity clienteEntity = getClienteEntityMock();
        clienteRepository.save(clienteEntity);

        this.mockMvc.perform(get("/clientes/12345678910")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.nome", is(clienteEntity.getNome())))
                .andExpect(status().isOk());
    }

    @Test
    public void salvarClienteTest() throws Exception {

        Gson gson = new Gson();

        ClienteDTO clienteDTO = getClienteDTOMock();
        String clienteDTOString = gson.toJson(clienteDTO);

        this.mockMvc.perform(post("/clientes/")
                .content(clienteDTOString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void atualizarClienteTest() throws Exception {

        ClienteEntity clienteEntity = getClienteEntityMock();
        clienteEntity = clienteRepository.save(clienteEntity);

        Gson gson = new Gson();

        String clienteDTOString = gson.toJson(getClienteDTOMock());

        this.mockMvc.perform(put("/clientes/{id}", clienteEntity.getCpf())
                .content(clienteDTOString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.nome", is(clienteEntity.getNome())))
                .andExpect(status().isOk());
    }

    @Test
    public void deletarCliente() throws Exception {

        ClienteEntity clienteEntity = getClienteEntityMock();
        clienteEntity = clienteRepository.save(clienteEntity);

        Gson gson = new Gson();

        String clienteDTOString = gson.toJson(getClienteDTOMock());

        this.mockMvc.perform(delete("/clientes/{id}", clienteEntity.getCpf())
                .content(clienteDTOString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
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
        clienteEntity.setNome("Teste");
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
