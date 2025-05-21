package com.sakila.interfaces;

import java.util.List;

public interface iDatapost<T> {
    void post(T obj);
    T get(int id);
    List<T> getAll();
    void put(T obj);
    void delete(int id);
}
