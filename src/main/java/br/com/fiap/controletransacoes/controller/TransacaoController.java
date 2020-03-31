package br.com.fiap.controletransacoes.controller;

import br.com.fiap.controletransacoes.dto.CreateTransacaoDTO;
import br.com.fiap.controletransacoes.dto.TransacaoDTO;
import br.com.fiap.controletransacoes.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
/**
 * Transacao Rest Controller
 * @name TransacaoController
 * @contextPath /transacoes
 */
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
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + "extrato.json")
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Retrieves information about all Cliente.
     */
    @GetMapping
    public List<TransacaoDTO> getAll(){
        return transacaoService.getAll();
    }

    /**
     * Retrieves information about a Transacao by Cliente Cpf.
     * @param cpf identifier of Client to be retrieved
     */
    @GetMapping("{cpf}")
    public List<TransacaoDTO> getByCliente(@PathVariable("cpf") String cpf){
        return transacaoService.getByCliente(cpf);
    }

    /**
     * Add a new Transacao.
     * @param createTransacaoDTO value of transacao to be stored
     *
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransacaoDTO salvarTransacao(@RequestBody @Valid CreateTransacaoDTO createTransacaoDTO){
        return transacaoService.salvarTransacao(createTransacaoDTO);
    }

    /**
     * Update a Transacao.
     * @param id Cpf of the Cliente to updated
     * @param transacaoDTO value of client to be stored
     *
     */
    @PutMapping("{id}")
    public TransacaoDTO atualizarTransacao(@PathVariable("id") Integer id, @RequestBody TransacaoDTO transacaoDTO){
        return transacaoService.atualizarTransacao(id, transacaoDTO);
    }

    /**
     * Delete a Cliente.
     * @param id Id of the Transacao to deleted
     *
     */
    @DeleteMapping("{id}")
    public void deletarTransacao(@PathVariable("id") Integer id){
        transacaoService.deletarTransacao(id);
    }
}
