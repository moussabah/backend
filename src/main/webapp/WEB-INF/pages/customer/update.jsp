<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Modifier un Client</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body class="bg-light">

<div class="container mt-5">
    <h2 class="mb-4">Modifier le client</h2>

    <form method="post" enctype="multipart/form-data" class="needs-validation" novalidate>

        <!-- ID et version cachés -->
        <input type="hidden" name="id" value="${customer.id}" />
        <input type="hidden" name="version" value="${customer.version}" />

        <div class="mb-3">
            <label for="lastname" class="form-label">Nom</label>
            <input type="text" class="form-control" id="lastname" name="lastname" value="${customer.lastname}" required />
        </div>

        <div class="mb-3">
            <label for="firstname" class="form-label">Prénom</label>
            <input type="text" class="form-control" id="firstname" name="firstname" value="${customer.firstname}" required />
        </div>

        <div class="mb-3">
            <label for="phone" class="form-label">Téléphone</label>
            <input type="text" class="form-control" id="phone" name="phone" value="${customer.phone}" />
        </div>

        <div class="mb-3">
            <label class="form-label">Photo actuelle</label><br>
            <c:if test="${not empty customer.photo}">
                <a href="${pageContext.request.contextPath}/images/customers/${customer.photo}" target="_blank" rel="noopener noreferrer">
                    <img src="${pageContext.request.contextPath}/images/customers/${customer.photo}" alt="photo" style="max-height: 60px;" class="img-thumbnail">
                </a>
            </c:if>
            <input type="hidden" name="photo" value="${customer.photo}" />
        </div>

        <div class="mb-3">
            <label for="imageFile" class="form-label">Changer la photo</label>
            <input type="file" class="form-control" id="imageFile" name="imageFile" accept="image/*" />
            <div class="form-text">Laissez vide pour conserver la photo actuelle.</div>
        </div>

        <div class="mb-3">
            <label class="form-label">Articles favoris</label>
            <div class="form-check">
                <c:forEach items="${items}" var="item">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" name="itemIds" value="${item.ref}" id="item-${item.ref}"
                            <c:if test="${customer.items.contains(item)}">checked</c:if> />
                        <label class="form-check-label" for="item-${item.ref}">
                            ${item.name}
                        </label>
                    </div>
                </c:forEach>
            </div>
        </div>

        <fieldset class="border p-3 mb-4">
            <legend class="w-auto px-2">Adresses existantes</legend>
            <c:forEach items="${customer.addresses}" var="address" varStatus="status">
                <div class="border rounded p-3 mb-3">
                    <input type="hidden" name="addresses[${status.index}].id" value="${address.id}" />
                    <div class="mb-3">
                        <label for="street-${status.index}" class="form-label">Rue</label>
                        <input type="text" class="form-control" id="street-${status.index}" name="addresses[${status.index}].street" value="${address.street}" required />
                    </div>
                    <div class="mb-3">
                        <label for="city-${status.index}" class="form-label">Ville</label>
                        <input type="text" class="form-control" id="city-${status.index}" name="addresses[${status.index}].city" value="${address.city}" required />
                    </div>
                    <div class="mb-3">
                        <label for="postalCode-${status.index}" class="form-label">Code postal</label>
                       <input type="text" class="form-control" id="postalCode-${status.index}" name="addresses[${status.index}].postalCode" value="${address.postalCode}" required />

                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="delete-${status.index}" name="addresses[${status.index}].delete" value="true" />
                        <label class="form-check-label" for="delete-${status.index}">Supprimer cette adresse</label>
                    </div>
                </div>
            </c:forEach>
        </fieldset>

        <fieldset class="border p-3 mb-4">
            <legend class="w-auto px-2">Ajouter une nouvelle adresse</legend>
            <div class="mb-3">
                <label for="newStreet" class="form-label">Rue</label>
                <input type="text" class="form-control" id="newStreet" name="newAddress.street" placeholder="Ex: 12 rue de Paris" />
            </div>
            <div class="mb-3">
                <label for="newCity" class="form-label">Ville</label>
                <input type="text" class="form-control" id="newCity" name="newAddress.city" placeholder="Ex: Paris" />
            </div>
            <div class="mb-3">
                <label for="newPostalCode" class="form-label">Code postal</label>
               <input type="text" class="form-control" id="newPostalCode" name="newAddress.postalCode" placeholder="Ex: 75000" />

            </div>
        </fieldset>

        <button type="submit" class="btn btn-primary">Mettre à jour</button>
        <a href="${pageContext.request.contextPath}/mvcCustomers/findall" class="btn btn-secondary ms-2">Annuler</a>
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
