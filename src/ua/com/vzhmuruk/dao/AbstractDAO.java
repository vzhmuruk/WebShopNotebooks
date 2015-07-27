package ua.com.vzhmuruk.dao;

import java.util.Collection;
import java.util.List;

public interface AbstractDAO<T> {
    boolean add(T item);
    boolean update(T edited);
    boolean delete(int id);
    T findById(long objid);
    boolean addAll(Collection<T> collection);
    List<T> loadAll();

}
