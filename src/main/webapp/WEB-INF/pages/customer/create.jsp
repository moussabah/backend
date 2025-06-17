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
        <!-- Informations personnelles -->
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
            <label for="login" class="form-label">Identifiant (login)</label>
            <input type="text" class="form-control" id="login" name="login" required>
        </div>

        <div class="mb-3">
            <label for="password" class="form-label">Mot de passe</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>

        <div class="mb-3">
            <label for="imageFile" class="form-label">Photo</label>
            <input type="file" class="form-control" id="imageFile" name="imageFile" accept="image/*">
        </div>

        <!-- Adresse unique -->
        <fieldset class="border p-3 mb-4">
            <legend class="w-auto px-2">Adresse</legend>
            <div class="mb-3">
                <label for="streetNumber" class="form-label">Numéro de rue</label>
                <input type="number" class="form-control" id="streetNumber" name="addresses[0].streetNumber" placeholder="Ex: 12">
            </div>
            <div class="mb-3">
                <label for="street" class="form-label">Rue</label>
                <input type="text" class="form-control" id="street" name="addresses[0].street" placeholder="Ex: rue de Paris">
            </div>
            <div class="mb-3">
                <label for="city" class="form-label">Ville</label>
                <input type="text" class="form-control" id="city" name="addresses[0].city" placeholder="Ex: Paris">
            </div>
            <div class="mb-3">
                <label for="postalCode" class="form-label">Code postal</label>
                <input type="text" class="form-control" id="postalCode" name="addresses[0].postalCode" placeholder="Ex: 75000">
            </div>
            <div class="mb-3">
                <label for="country" class="form-label">Pays</label>
                <input type="text" class="form-control" id="country" name="addresses[0].country" placeholder="Ex: France">
            </div>
        </fieldset>

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
