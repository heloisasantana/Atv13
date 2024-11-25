package com.example.atv13.controller;

import java.util.List;

public interface ICRUDDao<T> {
    void insert(T t) throws Exception;
    int update(T t) throws Exception;
    void delete(T t) throws Exception;
    T findOne(int id) throws Exception;
    List<T> findAll() throws Exception;
}

