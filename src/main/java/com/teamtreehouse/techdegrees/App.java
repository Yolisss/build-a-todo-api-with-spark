package com.teamtreehouse.techdegrees;


import com.google.gson.Gson;
import com.teamtreehouse.techdegrees.Dao.Sql2oTodoDao;
import com.teamtreehouse.techdegrees.Model.Todo;
import com.teamtreehouse.techdegrees.Dao.TodoDao;
import org.sql2o.Sql2o;

import static spark.Spark.*;

public class App {

    public static void main(String[] args) {
        staticFiles.location("/public");

        //creates conn to db
        Sql2o sql2o = new Sql2o("jdbc:h2:~/todos.db;INIT=RUNSCRIPT from 'classpath:db/init.sql'", "", "");
        System.out.println("Database initialized successfully!");

        //handle db operations
        TodoDao dao = new Sql2oTodoDao(sql2o);
        Gson gson = new Gson();

        post("/api/v1/todos", "application/json", (req, res) ->{
            Todo newTodoItem = gson.fromJson(req.body(), Todo.class);
            dao.add(newTodoItem);
            res.status(201);
            return newTodoItem;
        }, gson::toJson);

        //GET list of todos
        get("/api/v1/todos", "application/json", (req, res) ->
                dao.findAll(), gson::toJson);

        //GET a specific item from list
        get("/api/v1/todos/:id", "application/json", (req, res) ->{
            int id = Integer.parseInt(req.params("id"));
            Todo specificTodo = dao.findById(id);
            if(specificTodo == null){
                 res.status(404);
               return "Todo item not found";
            }
            return specificTodo;
        }, gson::toJson);

        put("/api/v1/todos/:id", "application/json", (req, res) ->{
           int id = Integer.parseInt(req.params("id"));
           Todo existingItem = dao.findById(id);
           System.out.printf("updated item here: %s%n", existingItem);

           if(existingItem == null){
               res.status(404);
               return "To-do item not found";
           }

           //parse the req body (JSON) to get new values
            Todo updatedData =  gson.fromJson(req.body(), Todo.class);

           existingItem.setName(updatedData.getName());
           existingItem.setIsCompleted(updatedData.getIsCompleted());

           boolean success = dao.update(existingItem);

           if(success){
               res.status(200);
               return gson.toJson(existingItem);
           } else {
               res.status(500);
               return "Failed to update the todo item";
           }
        });

        delete("/api/v1/todos/:id", "application/json", (req, res) ->{
            int id = Integer.parseInt(req.params("id"));
            Todo existingItem = dao.findById(id);

            if(existingItem != null){
                dao.delete(id);
                res.status(204);
                return "";
            } else {
                res.status(404);
                return "Unable to find item you want to delete";
            }
        });

        // Ensuring all responses are in JSON format
        after((req, res) -> {
            res.type("application/json");
        });
    }
}
