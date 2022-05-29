package br.com.vrbeneficios.miniautorizador.application.cartao.rest;

import br.com.vrbeneficios.miniautorizador.application.cartao.dto.CartaoDTO;
import br.com.vrbeneficios.miniautorizador.application.cartao.service.CartaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cartoes")
public class CartaoRest {

    private final CartaoService cartaoService;

    public CartaoRest(CartaoService cartaoService) {
        this.cartaoService = cartaoService;
    }

    @PostMapping
    public ResponseEntity<CartaoDTO> cadastrarCartao(@RequestBody CartaoDTO cartaoDTO) {
        try {
            return ResponseEntity.status(201).body(cartaoService.criarCartao(cartaoDTO));
        }catch (Exception e){
            return ResponseEntity.status(422).body(cartaoDTO);
        }
    }

}
