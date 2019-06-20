package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory
{
    private String url;
    private String username;
    private String password;

    DaoFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DaoFactory getInstance()
    {
        try
        {
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException cnfe) {      }

        DaoFactory instance = new DaoFactory("jdbc:postgresql://localhost:5432/soustitres", "adm_sous_titres", "VinceOC!");
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    // Récupération du Dao
    public FichierDao getFichierDaoImpl() {
        return new FichierDaoImpl(this);
    }
}

