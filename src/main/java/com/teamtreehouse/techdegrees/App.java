package com.teamtreehouse.techdegrees;


import com.google.gson.Gson;
import com.teamtreehouse.techdegrees.Dao.Sql2oTodoDao;
import com.teamtreehouse.techdegrees.Model.Todo;
import com.teamtreehouse.techdegrees.Dao.TodoDao;
import org.sql2o.Sql2o;

import static spark.Spark.*;

public class App {

    public static void main(String[] args) {

//        //creates conn to db
//        Sql2o sql2o = new Sql2o("jdbc:h2:~/todos.db;INIT=RUNSCRIPT from 'classpath:db/init.sql'");
//        System.out.println("Database initialized successfully!");
//
//        //handle db operations
//        TodoDao dao = new Sql2oTodoDao(sql2o);
//        Gson gson = new Gson();
//
//        //get("/api/v1/todos", "application/json", (req, res) ->
//                        dao.findAll(), gson::toJson);



        try {
            Sql2o sql2o = new Sql2o("jdbc:h2:~/todos.db;INIT=RUNSCRIPT from 'classpath:db/init.sql'", "", "");
            System.out.println("Database initialized successfully!");

            TodoDao dao = new Sql2oTodoDao(sql2o);
            Gson gson = new Gson();

            get("/api/v1/todos", "application/json", (req, res) ->
                    dao.findAll(), gson::toJson);

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }

        // Ensuring all responses are in JSON format
        after((req, res) -> {
            res.type("application/json");
        });
    }

}
