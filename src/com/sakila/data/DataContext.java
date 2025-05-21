package com.sakila.data;

import java.sql.Connection;
import java.util.List;

public abstract class DataContext<T> {

    protected final Connection conn;

    public DataContext(Connection conn) {
        this.conn = conn;
    }

    public final void post(T obj) {
        crear(obj);
    }

    public final T get(int id) {
        return leer(id);
    }

    public final List<T> getAll() {
        return leerTodo();
    }

    public final void put(T obj) {
        actualizar(obj);
    }

    public final void delete(int id) {
        eliminar(id);
    }

    // Métodos abstractos a implementar por las clases hijas (ActorManager, FilmManager...)
    protected abstract void crear(T obj);
    protected abstract T leer(int id);
    protected abstract List<T> leerTodo();
    protected abstract void actualizar(T obj);
    protected abstract void eliminar(int id);
}
