package org.example;

import org.jsoup.nodes.Document;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Indexer {
    static Connection connection = null;

    //constructor of Indexer
    Indexer(Document document, String url){
        //Selecting the important elements of the document
        String title = document.title();
        String link = url;
        String text = document.text();

        try {
            //save these statements to database
            connection = DatabaseConnection.getConnection();
            //to insert statements to database
            PreparedStatement preparedStatement = connection.prepareStatement("Insert into pages values(?,?,?);");
            preparedStatement.setString(1,title);
            preparedStatement.setString(2,link);
            preparedStatement.setString(3,text);
            preparedStatement.executeUpdate();
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
}
