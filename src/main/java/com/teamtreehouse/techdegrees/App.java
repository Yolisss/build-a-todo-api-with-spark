package com.teamtreehouse.techdegrees;


import org.sql2o.Sql2o;

import static spark.Spark.*;

public class App {

    public static void main(String[] args) {
        //our db
        String datasource = "jdbc:h2:~/todos.db";
        if(args.length > 0){
            //if it doesn't pass both params
            if(args.length != 2){
                System.out.println("java Api <port> <datasource>");
                System.exit(0);
            }
            //writing out port from string number
            //runs port we're running on
            port(Integer.parseInt(args[0]));
            datasource= args[1];
        }

        //INSTANTIATING sql2o
        //talking to the db
        Sql2o sql2o = new Sql2o(
                String.format("%s;INIT=RUNSCRIPT from 'classpath:db/init.sql'", datasource), "", "");

        System.out.println(System.getProperty("java.class.path"));

        get("/blah", (req, res) -> "Hello!");

    }

}
