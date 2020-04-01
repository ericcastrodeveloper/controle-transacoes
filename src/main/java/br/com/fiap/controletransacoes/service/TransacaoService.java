package br.com.fiap.controletransacoes.service;

import br.com.fiap.controletransacoes.dto.CreateTransacaoDTO;
import br.com.fiap.controletransacoes.dto.TransacaoDTO;
import org.springframework.core.io.InputStreamResource;

import java.io.IOException;
import java.util.List;

/**
 * Transacao Service
 * Interface that contains Transacao operations
 */
public interface TransacaoService {

    List<TransacaoDTO> getAll();
    List<TransacaoDTO> getByCliente(String cpf);
    TransacaoDTO salvarTransacao(CreateTransacaoDTO createTransacaoDTO);
    TransacaoDTO atualizarTransacao(Integer id, TransacaoDTO transacaoDTO);
    void deletarTransacao( Integer id);
    InputStreamResource getExtrato(String cpf) throws IOException;
    InputStreamResource getExtratoByCartao(Integer id) throws IOException;
}
