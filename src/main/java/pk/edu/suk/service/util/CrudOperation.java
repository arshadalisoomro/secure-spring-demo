package pk.edu.suk.service.util;

import java.util.List;

/**
 * Created by Arshay on 9/4/2018.
 */
public interface CrudOperation<T> {
    boolean save(T t);
    void delete(T t);
    List<T> findAll();
    T findById(Long id);
    void deleteById(Long id);
}
