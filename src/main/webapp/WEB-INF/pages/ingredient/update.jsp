<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Modifier un ingrédient</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body class="bg-light">

<div class="container mt-5">
    <h2 class="mb-4">Modifier l'ingrédient</h2>

    <form action="${pageContext.request.contextPath}/mvcIngredients/update/${ingredient.id}" method="post" class="needs-validation" novalidate>

        <!-- ID en lecture seule -->
        <div class="mb-3">
            <label class="form-label">Identifiant</label>
            <input type="text" class="form-control" value="${ingredient.id}" disabled />
            <input type="hidden" name="id" value="${ingredient.id}" />
        </div>

        <!-- Champ version caché -->
        <input type="hidden" name="version" value="${ingredient.version}" />

        <div class="mb-3">
            <label for="name" class="form-label">Nom</label>
            <input type="text" class="form-control" id="name" name="name" value="${ingredient.name}" required />
        </div>

        <!-- quantité et unité masqués car non souhaités -->

        <button type="submit" class="btn btn-primary">Enregistrer</button>
        <a href="${pageContext.request.contextPath}/mvcIngredients/findall" class="btn btn-secondary ms-2">Annuler</a>
    </form>
</div>

<script>
(() => {
  'use strict'
  const forms = document.querySelectorAll('.needs-validation')
  Array.from(forms).forEach(form => {
    form.addEventListener('submit', event => {
      if (!form.checkValidity()) {
        event.preventDefault()
        event.stopPropagation()
      }
      form.classList.add('was-validated')
    }, false)
  })
})()
</script>

</body>
</html>
