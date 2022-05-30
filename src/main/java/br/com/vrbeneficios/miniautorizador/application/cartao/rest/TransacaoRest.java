package br.com.vrbeneficios.miniautorizador.application.cartao.rest;

import br.com.vrbeneficios.miniautorizador.application.cartao.dto.CartaoDTO;
import br.com.vrbeneficios.miniautorizador.application.cartao.dto.TransacoesDTO;
import br.com.vrbeneficios.miniautorizador.application.cartao.service.CartaoService;
import br.com.vrbeneficios.miniautorizador.domain.model.enums.RetornoTrasacaoEnum;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "API responsavel por realizar trasações referentes ao cartão")
@RestController
@RequestMapping(value = "/transacoes")
public class TransacaoRest {

    private final CartaoService cartaoService;

    public TransacaoRest(CartaoService cartaoService) {
        this.cartaoService = cartaoService;
    }

    @PostMapping
    @Operation(summary = "Realizar transação")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "", response = CartaoDTO.class),
            @ApiResponse(code = 422, message = "")
    })
    public ResponseEntity<String> realizarTransacao(@RequestBody TransacoesDTO transacoesDTO) {
        try {
            final RetornoTrasacaoEnum retornoTrasacaoEnum = cartaoService.realizarTransacao(transacoesDTO);
            if(RetornoTrasacaoEnum.OK.equals(retornoTrasacaoEnum)){
                return ResponseEntity.status(201).body(retornoTrasacaoEnum.name());
            }
            return ResponseEntity.status(422).body(retornoTrasacaoEnum.name());
        }catch (Exception e){
            return ResponseEntity.status(422).body(RetornoTrasacaoEnum.CARTAO_INEXISTENTE.name());
        }

    }

}
