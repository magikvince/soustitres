package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FichierDaoImpl implements FichierDao
{
    private DaoFactory daoFactory;

    FichierDaoImpl (DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void ajouter(Fichier fichier) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();

            preparedStatement = connexion.prepareStatement("INSERT INTO fichier(nom, description) VALUES(?, ?);");
            preparedStatement.setString(1, fichier.getNom());
            preparedStatement.setString(2, fichier.getDescription());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Fichier> lister() {
        List<Fichier> fichiers = new ArrayList<Fichier>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT nom, description FROM fichier;");

            while (resultat.next()) {
                String nom = resultat.getString("nom");
                String description = resultat.getString("description");

                Fichier fichier = new Fichier(nom, description);
                fichiers.add(fichier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fichiers;
    }
}
