<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Editeurs Sous-titres</title>
    <link rel="stylesheet" type="text/css" href="styles.css"/>
  </head>
  <body>
  <H1>Editeur de sous-titres</H1>

  <p><c:out value="bonjour vincent" /></p>

  <H2>Liste des fichiers:</H2>

  <!-- calcul du nombre de fichiers -->

  <c:if test="${ ! empty description} " >
     <p> description : ${description}</p>
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

      <input type="submit" value="import"/>
  </form>

  </body>
</html>
