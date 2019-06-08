<%--
  Created by IntelliJ IDEA.
  User: vince
  Date: 24/05/2019
  Time: 20:00
  To change this template use File | Settings | File Templates.
--%>
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

  <H2>Liste des travaux en cours:</H2>


  <H2>Importer un fichier</H2>
  <form method="post" action="import_file" enctype="multipart/form-data">

    <p>
      <label for="description">Description du fichier : </label>
      <input type="text" name="description" id="description" />
    </p>

    <label for ="fichier">Fichier : </label>
    <input type="file" name="fichier" id="fichier" />
  </form>

  </body>
</html>
