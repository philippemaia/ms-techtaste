package br.com.techtaste.mspedidos.utils;

import br.com.techtaste.mspedidos.dto.AutorizacaoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("ms-pagamentos")
public interface AutorizacaoPagamentoClient {

    @RequestMapping(method = RequestMethod.GET, value = "/pagamentos/autorizacao/{id}")
    AutorizacaoDto obterAutorizacao(@PathVariable String id);
}
