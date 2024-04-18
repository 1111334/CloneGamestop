package com.example.CloneGamestop;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.sql.SQLException;

@SpringBootApplication
//@ComponentScan("com.example.CloneGamestop")
public class CloneGamestopApplication {

    public static void main(String[] args) throws IOException, SQLException {
        SpringApplication.run(CloneGamestopApplication.class, args);
        //Properties props = new Properties();
        //InputStream input = Main.class.getResourceAsStream("/.env");
        //props.load(input);
        //String dbPassword = props.getProperty("DB_PASSWORD");
        // Use dbPassword for database connection...

        //Dotenv dotenv = Dotenv.load();
        //// Recupera variabili d'ambiente
        //String dbUrl = dotenv.get("DB_URL");
        //String dbUsername = dotenv.get("DB_USERNAME");
        //String dbPassword = dotenv.get("DB_PASSWORD");

        //// Usa le variabili per connettersi al database...
        //String url = dbUrl + "?user=" + dbUsername + "&password=" + dbPassword;
        //try (Connection ignored = DriverManager.getConnection(url)) {
        //    System.out.println("Connesso al database!");

        //    // Esegui query SQL (opzionale)
        //    // ...

        //} catch (SQLException e) {
        //    System.err.println("Errore durante la connessione al database: " + e.getMessage());
        //}

    }
}