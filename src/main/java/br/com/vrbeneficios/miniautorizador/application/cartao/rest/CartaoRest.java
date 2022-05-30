package br.com.vrbeneficios.miniautorizador.application.cartao.rest;

import br.com.vrbeneficios.miniautorizador.application.cartao.dto.CartaoDTO;
import br.com.vrbeneficios.miniautorizador.application.cartao.service.CartaoService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Tag(name = "API responsavel por conter os serviços referentes a cadastro e consulta de cartão")
@RestController
@RequestMapping(value = "/cartoes")
public class CartaoRest {

    private final CartaoService cartaoService;

    public CartaoRest(CartaoService cartaoService) {
        this.cartaoService = cartaoService;
    }

    @PostMapping
    @Operation(summary = "Cadastra um novo cartão")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "", response = CartaoDTO.class),
            @ApiResponse(code = 422, message = "")
    })
    public ResponseEntity<CartaoDTO> cadastrarCartao(@RequestBody CartaoDTO cartaoDTO) {
        try {
            return ResponseEntity.status(201).body(cartaoService.criarCartao(cartaoDTO));
        }catch (Exception e){
            return ResponseEntity.status(422).body(cartaoDTO);
        }
    }

    @GetMapping("/{numeroCartao}")
    @Operation(summary = "Consulta um cartão")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = BigDecimal.class),
            @ApiResponse(code = 404, message = "")
    })
    public ResponseEntity<BigDecimal> obterSaldo(@PathVariable String numeroCartao) {
        try {
            return ResponseEntity.status(200).body(cartaoService.obterSaldoCartao(numeroCartao));
        }catch (Exception e){
            return ResponseEntity.status(404).build();
        }
    }

}
