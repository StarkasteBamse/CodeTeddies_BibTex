package database;

import java.util.List;

/**
 *
 * @author Willburner
 * @param <Reference> 
 */
public interface DAO<Reference> {
    void delete(Reference key);
    void clearDatabase();
    void add(Reference key);
    void update(Reference key);
    List<Reference> getAll();
}
