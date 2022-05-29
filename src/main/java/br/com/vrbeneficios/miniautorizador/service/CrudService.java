package br.com.vrbeneficios.miniautorizador.service;

import br.com.vrbeneficios.miniautorizador.exeception.DataIntegrityException;
import br.com.vrbeneficios.miniautorizador.exeception.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, ID> {

    default T buscarPorId(ID id) {
        Optional<T> obj = getRepository().findById(id);
        obj.orElseThrow(() -> new ObjectNotFoundException(id.toString(), getClass()));
        return obj.get();
    }

    @Transactional
    default T salvar(T obj) {
        try {
            return getRepository().save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException(DataIntegrityException.MSG_SALVAR, obj.getClass());
        }
    }

    @Transactional
    default List<T> salvarTodos(List<T> objs) {
        try {
            return getRepository().saveAll(objs);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException(DataIntegrityException.MSG_SALVAR_LISTA);
        }
    }

    @Transactional
    default void remover(ID id) {
        T obj = buscarPorId(id);
        try {
            getRepository().delete(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException(DataIntegrityException.MSG_REMOVER, obj.getClass());
        }
    }

    JpaRepository<T, ID> getRepository();
}
