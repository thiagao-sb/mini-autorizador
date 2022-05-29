package br.com.vrbeneficios.miniautorizador.application.cartao.repository;

import br.com.vrbeneficios.miniautorizador.domain.model.cartao.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, String> {

}
