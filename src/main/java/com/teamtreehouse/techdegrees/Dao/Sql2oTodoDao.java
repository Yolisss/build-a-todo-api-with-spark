package com.teamtreehouse.techdegrees.Dao;

import com.teamtreehouse.techdegrees.Model.Todo;
import com.teamtreehouse.techdegrees.exc.DaoException;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

//NOW: setup db connection and create the todos table

//DONE: create the todos table on initialization if it doesn't exist

public class Sql2oTodoDao implements TodoDao {

    private final Sql2o sql2o;

    public Sql2oTodoDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    //CREATE
    @Override
    public void add(Todo todo) throws DaoException {
        String sql = "INSERT INTO todos(name, isCompleted) VALUES (:name, :isCompleted)";
        //open db connection
        try(Connection con = sql2o.open()){
            //create query; passing data from user
            int id = (int) con.createQuery(sql)
            .bind(todo)
                    .executeUpdate()
                    .getKey();
            todo.setId(id);
        } catch(Sql2oException ex){
            ex.printStackTrace();
            throw new DaoException(ex, "Problem adding todo item");
        }

    }

    //READ
    @Override
    public List<Todo> findAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM todos")
                    .executeAndFetch(Todo.class);
        }
    }

    //READ
    @Override
    public Todo findById(int id) {
         try(Connection con = sql2o.open()){
             return con.createQuery("SELECT * FROM todos WHERE id = :id")
             .addParameter("id", id)
                     .executeAndFetchFirst(Todo.class);
         }
    }
    //UPDATE
    pub
    //DELETE

}
