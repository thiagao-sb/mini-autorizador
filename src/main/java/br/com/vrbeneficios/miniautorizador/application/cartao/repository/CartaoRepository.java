package br.com.vrbeneficios.miniautorizador.application.cartao.repository;

import br.com.vrbeneficios.miniautorizador.domain.model.Cartao;
import br.com.vrbeneficios.miniautorizador.domain.model.CartaoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, CartaoId> {

    Optional<Cartao> findCartaoByNumeroCartao(final String numeroCartao);

}
