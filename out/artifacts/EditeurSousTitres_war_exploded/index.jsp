<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Editeurs Sous-titres</title>
    <link rel="stylesheet" type="text/css" href="styles.css"/>
  </head>
  <body>
  <H1>Editeur de sous-titres</H1>

  <H2>Liste des fichiers:</H2>

  <!-- calcul du nombre de fichiers -->
    <c:out value="bonjour Anthony" />

  <c:if test="${ ! empty nomfic } " >
      <p> <c:out value=" nomfic : ${nomfic} ${nomFicher} "/></p>
  </c:if>

  <c:if test="${ empty description }" >
     <p> <c:out value=" description : ${description} "/></p>
  </c:if>

  <H2>Liste des travaux en cours:</H2>

  <H2>Importer un fichier dans la base de données</H2>
  <form method="post" action="/import" enctype="multipart/form-data">

    <p>
      <label for="description">Description du fichier : </label>
      <input type="text" name="description" id="description" />
    </p>

    <p>
      <label for ="fichier">Fichier : </label>
      <input type="file" name="fichier" id="fichier" /><br/>
    </p>

    <p>
      <input type="submit" value="IMPORT DU FICHIER"/>
    </p>
  </form>

  </body>
</html>
