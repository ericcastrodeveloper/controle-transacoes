package br.com.fiap.controletransacoes.service.impl;

import br.com.fiap.controletransacoes.dto.TransacaoDTO;
import br.com.fiap.controletransacoes.entity.ClienteEntity;
import br.com.fiap.controletransacoes.entity.TransacaoEntity;
import br.com.fiap.controletransacoes.repository.ClienteRepository;
import br.com.fiap.controletransacoes.repository.TransacaoRepository;
import br.com.fiap.controletransacoes.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.ws.Response;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransacaoImpl implements TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<TransacaoDTO> getAll(){
        return transacaoRepository.findAll().stream().map(TransacaoDTO::new).collect(Collectors.toList());
    }

    public List<TransacaoDTO> getByCliente(String cpf){
        return transacaoRepository.findByClienteCpf(cpf).stream().map(TransacaoDTO::new).collect(Collectors.toList());
    }

    public TransacaoDTO salvarTransacao(TransacaoDTO transacaoDTO){
        TransacaoEntity transacaoEntity = new TransacaoEntity(transacaoDTO);

        ClienteEntity cliente = clienteRepository.findById(transacaoDTO.getClienteDTO().getCpf()).get();
        transacaoEntity.setCliente(cliente);

        return new TransacaoDTO(transacaoRepository.save(transacaoEntity));
    }

    public TransacaoDTO atualizarTransacao( Integer id, TransacaoDTO transacaoDTO){
        TransacaoEntity transacaoEntity = transacaoRepository.findById(id).get();

        transacaoEntity.getCliente().setNome(transacaoDTO.getClienteDTO().getNome());
        transacaoEntity.getCliente().setCpf(transacaoDTO.getClienteDTO().getCpf());

        transacaoEntity.getProduto().setNome(transacaoDTO.getProdutoDTO().getNome());
        transacaoEntity.getProduto().setQuantidade(transacaoDTO.getProdutoDTO().getQuantidade());
        transacaoEntity.getProduto().setValor(transacaoDTO.getProdutoDTO().getValor());

        return new TransacaoDTO(transacaoRepository.save(transacaoEntity));
    }

    public void deletarTransacao( Integer id){
        TransacaoEntity transacaoEntity = transacaoRepository.findById(id).get();
        transacaoRepository.delete(transacaoEntity);
    }

    @Override
    public ResponseEntity<InputStreamResource> getExtrato(String cpf){


        List<TransacaoDTO> listTransacao = getByCliente(cpf);

        if(listTransacao != null){

            File file = escreverExtrato(listTransacao);
            InputStreamResource resource = null;

            try{
                resource = new InputStreamResource(new FileInputStream(file));
            }catch(FileNotFoundException e){
                e.printStackTrace();

            }


            return ResponseEntity.ok()
                    // Content-Disposition
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                    // Content-Type
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    // Contet-Length
                    .contentLength(file.length()) //
                    .body(resource);
        }
        return new ResponseEntity<InputStreamResource>(HttpStatus.NO_CONTENT);
    }

    private File escreverExtrato(List<TransacaoDTO> listTransacao) {
        File file = new File("extrato.txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            for(TransacaoDTO transacaoDTO : listTransacao){
                fw.write(transacaoDTO.toString());
                fw.append("\n\r");
                fw.append("\n\r");

            }
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



        return file;

    }
}
