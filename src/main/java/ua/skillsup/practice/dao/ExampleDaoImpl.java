package ua.skillsup.practice.dao;

import ua.skillsup.practice.dao.entity.ExampleEntity;

import java.util.ArrayList;
import java.util.List;

public class ExampleDaoImpl implements ExampleDao {
    private final List<ExampleEntity> list = new ArrayList<>();

    public ExampleDaoImpl() {
    }

    public boolean store(ExampleEntity entity) throws ExampleNetworkException {
        if(list.stream().map(e -> e.getTitle().toUpperCase())
                .filter( s -> s.equals(entity.getTitle().toUpperCase())).count() > 0) {
            return false;
        }

        list.add(entity);
        return true;
    }

    public List<ExampleEntity> findAll() throws ExampleNetworkException {
        return list;
    }
}
