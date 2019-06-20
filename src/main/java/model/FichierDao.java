package model;

        import java.util.List;

public interface FichierDao {
    public void ajouter( Fichier fichier );
    public List<Fichier> lister();
}
