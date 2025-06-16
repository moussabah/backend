<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Créer un Client</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body class="bg-light">

<div class="container mt-5">
    <h2 class="mb-4">Créer un nouveau client</h2>

    <form method="post" enctype="multipart/form-data" class="needs-validation" novalidate>
        <!-- Infos perso -->
        <div class="mb-3">
            <label for="lastname" class="form-label">Nom</label>
            <input type="text" class="form-control" id="lastname" name="lastname" required>
        </div>

        <div class="mb-3">
            <label for="firstname" class="form-label">Prénom</label>
            <input type="text" class="form-control" id="firstname" name="firstname" required>
        </div>

        <div class="mb-3">
            <label for="phone" class="form-label">Téléphone</label>
            <input type="text" class="form-control" id="phone" name="phone">
        </div>

        <div class="mb-3">
            <label for="imageFile" class="form-label">Photo</label>
            <input type="file" class="form-control" id="imageFile" name="imageFile" accept="image/*">
        </div>

        <!-- Adresse : simple formulaire d'ajout inline -->
        <fieldset class="border p-3 mb-4">
            <legend class="w-auto px-2">Ajouter une adresse</legend>
            <div class="mb-3">
                <label for="street" class="form-label">Rue</label>
                <input type="text" class="form-control" id="street" name="address.street" placeholder="Ex: 12 rue de Paris">
            </div>
            <div class="mb-3">
                <label for="city" class="form-label">Ville</label>
                <input type="text" class="form-control" id="city" name="address.city" placeholder="Ex: Paris">
            </div>
            <div class="mb-3">
                <label for="zipcode" class="form-label">Code postal</label>
                <input type="text" class="form-control" id="zipcode" name="address.zipcode" placeholder="Ex: 75000">
            </div>
        </fieldset>

        <!-- Items favoris -->
        <div class="mb-3">
            <label class="form-label">Articles favoris</label>
            <div>
                <c:forEach items="${items}" var="item">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" name="itemIds" value="${item.ref}" id="item-${item.ref}">
                        <label class="form-check-label" for="item-${item.ref}">
                            ${item.name}
                        </label>
                    </div>
                </c:forEach>
            </div>
        </div>

        <button type="submit" class="btn btn-success">Créer</button>
        <a href="${pageContext.request.contextPath}/mvcCustomers/findall" class="btn btn-secondary ms-2">Annuler</a>
    </form>
</div>

<script>
(() => {
  'use strict'
  const forms = document.querySelectorAll('.needs-validation');
  Array.from(forms).forEach(form => {
    form.addEventListener('submit', event => {
      if (!form.checkValidity()) {
        event.preventDefault();
        event.stopPropagation();
      }
      form.classList.add('was-validated');
    }, false);
  });
})();
</script>

</body>
</html>
