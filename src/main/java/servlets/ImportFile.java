package servlets;

import model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalTime;
import javax.servlet.http.Part;

public class ImportFile extends HttpServlet
{

    public static final int TAILLE_TAMPON = 10240;
    public static final String CHEMIN_FICHIERS = "C:/OC_TEMP_FILES/files/";

    public String nomFichier;
    public String description;

    private FichierDao fichierDao;



    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.fichierDao = daoFactory.getFichierDaoImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        this.description = request.getParameter("description");
        request.setAttribute("description" , this.description);

        //utilisation du DAO factory etc

        // On récupère le champ du fichier
        Part part = request.getPart("fichier");

        // On vérifie qu'on a bien reçu un fichier
        this.nomFichier = getNomFichier(part);

        // Si on a bien un fichier
        if (nomFichier != null && ! nomFichier.isEmpty())
        {
            String nomfic = part.getName();

            // Corrige un bug du fonctionnement d'Internet Explorer
            nomFichier = nomFichier.substring(nomFichier.lastIndexOf('/') + 1).substring(nomFichier.lastIndexOf('\\') + 1);

            // On écrit définitivement le fichier sur le disque
            this.ecrireFichier(part, nomFichier, CHEMIN_FICHIERS);

            //on parse le fichier
            Fichier parsedFile = this.ParseFichier(this.nomFichier, this.description);

            // creation du fichier en base ?!
            fichierDao.ajouter(parsedFile);
            request.setAttribute("nomfic", nomFichier);
        }

        //this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        request.getRequestDispatcher("/index.jsp").forward(request,response);
        //response.sendRedirect("/welcome");
    }

    private void ecrireFichier( Part part, String nomFichier, String chemin ) throws IOException {
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;

        try
        {
            entree = new BufferedInputStream(part.getInputStream(), TAILLE_TAMPON);
            sortie = new BufferedOutputStream(new FileOutputStream(new File(chemin + nomFichier)), TAILLE_TAMPON);

            byte[] tampon = new byte[TAILLE_TAMPON];
            int longueur;
            while ((longueur = entree.read(tampon)) > 0)
            {
                sortie.write(tampon, 0, longueur);
            }
        }
        finally
        {
            try
            {
                if ( sortie != null)
                    sortie.close();
            } catch (IOException ignore) {      }
            try
            {
                if (entree != null)
                        entree.close();
            } catch (IOException ignore) {    }
        }
    }

    private static String getNomFichier( Part part )
    {
        for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) )
        {
            if ( contentDisposition.trim().startsWith( "filename" ) )
            {
                return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
            }
        }
        return null;
    }


    /**
     * Lecture du fichier et parsing pour créer un objet Fichier et des objets LigneFichier
     * @param nomfic
     * @param description
     * @return
     */


    /*
    3
00:00:04,389 --> 00:00:06,318
et mes recherches se concentrent sur
l'utilisabilité de la vie privée
     */

    private Fichier ParseFichier(String nomfic, String description)
    {
        Fichier parsedFile = new Fichier (nomfic, description);
        String newLine;
        int count = 0;

        int num=0;
        int positionTiret=0;
        String time="";

        String debut;
        String fin;
        String texte="";

        try {
            FileReader fr = new FileReader(this.nomFichier);
            BufferedReader br = new BufferedReader(fr);

            try {
                newLine = br.readLine();
                while ( newLine != null)
                {

                    // recuperation du numero de ligne
                    if (newLine.length() <= 8){
                        try {
                            num = Integer.parseInt(newLine);
                        }
                        catch (NumberFormatException nfe) { System.out.println("ERREUR DE FORMAT DE NUMERO !"); }
                    }

                    //recherche de --> dans la chaine et on split avec String substr??
                    if (newLine.contains("-->") )
                    {
                         positionTiret = newLine.indexOf('-');
                         debut = newLine.substring(0, positionTiret);
                         fin = newLine.substring(positionTiret + 2, newLine.length() - 1);
                    }

                    //recupération du texte
                    if ( (newLine.length() > 10) && ( ! newLine.contains("-->")) ){
                        texte = newLine;
                    }

                    //pour chaque sequence de ligne lue, on cree un objet LigneFichier
                    if ( newLine.equals("") )
                    {
                        LigneFichier ligne = new LigneFichier(num, time, texte);
                        parsedFile.ajouterLigneFichier(ligne);
                    }

                    count++;
                    newLine = br.readLine();

                }

                return parsedFile;

            } catch (IOException ioe) {
                    System.out.println("erreur de lecture " + this.nomFichier + " : " + ioe.getMessage() );}
        }
        catch (FileNotFoundException fnfe) {
                System.out.println("Fichier " + this.nomFichier + " non trouvé : " + fnfe.getMessage() );}

        return null;
    }

}