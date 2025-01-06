package com.teamtreehouse.techdegrees.Dao;

import com.teamtreehouse.techdegrees.Model.Todo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static org.junit.Assert.*;

public class Sql2oTodoDaoTest {

    private Sql2oTodoDao dao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        //on initialization connection to db which creates our tables
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/init.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        //creating a dao
        dao = new Sql2oTodoDao(sql2o);
        //keep connection open through entire test so it doesn't get wiped out
        conn = sql2o.open();

        System.out.println("DAO initialized: " + dao);
        System.out.println("Connection established: " + conn);


        // Verify table creation
        String checkTables = "SHOW TABLES;";
        List<String> tables = conn.createQuery(checkTables).executeAndFetch(String.class);
        System.out.println("Tables in database: " + tables);
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingNewTodoSetsId() throws Exception {
        Todo todo = new Todo("Test", "true");
        int originalTodoId = todo.getId();

        dao.add(todo);

        assertNotEquals(originalTodoId, todo.getId());
    }
}