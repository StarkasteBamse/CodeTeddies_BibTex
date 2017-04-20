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
 * @param <T>
 */
public interface DAO<T> {
    void delete(T key);
    void add(T key);
    void update(T key);
    List<T> getAll();
}
