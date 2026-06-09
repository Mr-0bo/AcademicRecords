package com.universidad.repository;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz genérica del patrón Repository / DAO.
 * Todas las entidades comparten este contrato base.
 *
 * @param <T>  Tipo de la entidad
 * @param <ID> Tipo del identificador
 */
public interface BaseRepository<T, ID> {
    List<T> findAll();
    Optional<T> findById(ID id);
    T save(T entity);       // INSERT si id==0, UPDATE si id>0
    boolean delete(ID id);
}

