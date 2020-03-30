package br.com.fiap.controletransacoes.controller;

import br.com.fiap.controletransacoes.dto.TransacaoDTO;
import br.com.fiap.controletransacoes.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @GetMapping("/download-extrato/{cpf}")
    public ResponseEntity<InputStreamResource> getExtrato(@PathVariable String cpf) throws IOException {

        InputStreamResource resource = transacaoService.getExtrato(cpf);
        if(resource != null)
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + "extrato.txt")
                .contentType(MediaType.parseMediaType("application/octet-stream"))
//                .contentLength(resource.contentLength())
                .body(resource);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public List<TransacaoDTO> getAll(){
        return transacaoService.getAll();
    }

    @GetMapping("{cpf}")
    public List<TransacaoDTO> getByCliente(@PathVariable("cpf") String cpf){
        return transacaoService.getByCliente(cpf);
    }

    @PostMapping
    public TransacaoDTO salvarTransacao(@RequestBody TransacaoDTO transacaoDTO){
        return transacaoService.salvarTransacao(transacaoDTO);
    }

    @PutMapping("{id}")
    public TransacaoDTO atualizarTransacao(@PathVariable("id") Integer id, @RequestBody TransacaoDTO transacaoDTO){
        return transacaoService.atualizarTransacao(id, transacaoDTO);
    }

    @DeleteMapping("{id}")
    public void deletarTransacao(@PathVariable("id") Integer id){
        transacaoService.deletarTransacao(id);
    }
}
