package es.dam.accesodatos.dao;

import es.dam.accesodatos.model.Autor;

import java.util.List;
import java.util.Optional;


public class AutorDaoJdbc implements Dao<Autor, Integer>{
    @Override
    public Integer create(Autor autor) {
        return 0;
    }

    @Override
    public Optional<Autor> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<Autor> findAll() {
        return List.of();
    }

    @Override
    public boolean update(Autor autor) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}
