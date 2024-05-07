package com.studentscrud.dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {

    public void save(T t) throws SQLException;

    public List<T> findAll() throws SQLException;

    public T findByRA(String ra) throws SQLException;

    public void update(T t) throws SQLException;

    public void delete(Long ra) throws SQLException;
}
