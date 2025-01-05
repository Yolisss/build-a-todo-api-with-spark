package com.teamtreehouse.techdegrees.Dao;

import com.teamtreehouse.techdegrees.Model.Todo;
import com.teamtreehouse.techdegrees.exc.DaoException;

import java.util.List;

public interface TodoDao {
    //add a to-do
    void add(Todo todo) throws DaoException;

    //findAll
    List<Todo> findAll();

    //findById
    Todo findById(int id);

    void update(Todo todo) throws DaoException;

    void delete(int id) throws DaoException;
}
