/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.util.List;

/**
 *
 * @author Willburner
 * @param <Reference> 
 */
public interface DAO<Reference> {
    void delete(Reference key);
    void add(Reference key);
    void update(Reference key);
    List<Reference> getAll();
}
