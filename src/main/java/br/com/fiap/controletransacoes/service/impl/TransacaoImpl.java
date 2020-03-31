package br.com.fiap.controletransacoes.service.impl;

import br.com.fiap.controletransacoes.dto.CreateTransacaoDTO;
import br.com.fiap.controletransacoes.dto.TransacaoDTO;
import br.com.fiap.controletransacoes.entity.ClienteEntity;
import br.com.fiap.controletransacoes.entity.ProdutoEntity;
import br.com.fiap.controletransacoes.entity.TransacaoEntity;
import br.com.fiap.controletransacoes.mapper.ProdutoMapper;
import br.com.fiap.controletransacoes.repository.ClienteRepository;
import br.com.fiap.controletransacoes.repository.TransacaoRepository;
import br.com.fiap.controletransacoes.service.TransacaoService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransacaoImpl implements TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Value("${extratoFile}")
    private String extratoFile;

    public List<TransacaoDTO> getAll(){
        return transacaoRepository.findAll().stream().map(TransacaoDTO::new).collect(Collectors.toList());
    }

    public List<TransacaoDTO> getByCliente(String cpf){
        return transacaoRepository.findByClienteCpf(cpf).stream().map(TransacaoDTO::new).collect(Collectors.toList());
    }

    public TransacaoDTO salvarTransacao(CreateTransacaoDTO createTransacaoDTO){


        TransacaoEntity transacaoEntity = new TransacaoEntity(createTransacaoDTO);


        BigDecimal valorTotal = setValorTotal(transacaoEntity);
        transacaoEntity.setValorTotal(valorTotal);

        ClienteEntity cliente = clienteRepository.findById(createTransacaoDTO.getCpf()).get();
        transacaoEntity.setCliente(cliente);

        return new TransacaoDTO(transacaoRepository.save(transacaoEntity));
    }

    public TransacaoDTO atualizarTransacao( Integer id, TransacaoDTO transacaoDTO){
        TransacaoEntity transacaoEntity = transacaoRepository.findById(id).get();

        List<ProdutoEntity> listProdutoEntity = ProdutoMapper.toEntity(transacaoDTO.getListProdutoDTO());

        transacaoEntity.setListaProduto(listProdutoEntity);

        transacaoEntity.setCliente(new ClienteEntity(transacaoDTO.getClienteDTO()));

        return new TransacaoDTO(transacaoRepository.save(transacaoEntity));
    }

    public void deletarTransacao( Integer id){
        TransacaoEntity transacaoEntity = transacaoRepository.findById(id).get();
        transacaoRepository.delete(transacaoEntity);
    }

    @Override
    public InputStreamResource getExtrato(String cpf) throws IOException {


        List<TransacaoDTO> listTransacao = getByCliente(cpf);

        if(listTransacao.size() > 0){

            InputStreamResource resource = null;

            try{
                File file = escreverExtrato(listTransacao);

                resource = new InputStreamResource(new FileInputStream(file));
            }catch(IOException e) {
                e.printStackTrace();
                throw e;
            }

            return resource;
        }
        return null;
    }

    private File escreverExtrato(List<TransacaoDTO> listTransacao) throws IOException {
        File file = new File(extratoFile);
        FileWriter fw = null;

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        String listJson = gson.toJson(listTransacao);

        fw = new FileWriter(file);
        fw.write(listJson);

        fw.flush();
        fw.close();

        return file;
    }

    private BigDecimal setValorTotal(TransacaoEntity transacaoEntity) {

        BigDecimal soma = new BigDecimal(0);
        List<ProdutoEntity> listProdutos = transacaoEntity.getListaProduto();
        for(int i= 0; i < listProdutos.size(); i++){
           soma = soma.add(listProdutos.get(i).getValor().multiply(BigDecimal.valueOf(listProdutos.get(i).getQuantidade())));

        }
        return soma;
    }

}
