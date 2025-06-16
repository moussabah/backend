<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Modifier un Item</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body class="bg-light">

<div class="container mt-5">
    <h2 class="mb-4">Modifier l'Item</h2>

    <form action="${pageContext.request.contextPath}/mvcItems/update/${item.ref}" method="post" class="needs-validation" enctype="multipart/form-data"  novalidate>

        <!-- Référence en lecture seule (non modifiable) -->
        <div class="mb-3">
            <label class="form-label">Référence</label>
            <input type="text" class="form-control" value="${item.ref}" disabled />
            <input type="hidden" name="ref" value="${item.ref}" />
        </div>

        <!-- Champ version caché -->
        <input type="hidden" name="version" value="${item.version}" />

        <div class="mb-3">
            <label for="name" class="form-label">Nom</label>
            <input type="text" class="form-control" id="name" name="name" value="${item.name}" required />
        </div>

        <div class="mb-3">
            <label for="price" class="form-label">Prix (€)</label>
            <input type="number" step="0.01" class="form-control" id="price" name="price" value="${item.price}" required />
        </div>

        <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <textarea class="form-control" id="description" name="description">${item.description}</textarea>
        </div>
        <input type="hidden" name="pathImg" value="${item.pathImg}">

         <c:if test="${not empty item.pathImg}">
                                <a href="${pageContext.request.contextPath}/images/${item.pathImg}" target="_blank" rel="noopener noreferrer">
                                    <img src="${pageContext.request.contextPath}/images/${item.pathImg}" alt="${item.name}" style="max-height: 60px;">
                                </a>
                            </c:if>

            <div class="mb-3">
                <label for="imageFile" class="form-label">Changer l'image</label>
                <input type="file" class="form-control" id="imageFile" name="imageFile" accept="image/*" />
                <div class="form-text">Laissez vide pour conserver l'image actuelle.</div>
            </div>

        <div class="mb-3">
            <label for="category" class="form-label">Catégorie</label>
            <select class="form-select" id="category" name="category" required>
                <option value="">-- Choisir une catégorie --</option>
                <c:forEach var="cat" items="${categories}">
                    <option value="${cat}" <c:if test="${cat == item.category}">selected</c:if>>${cat}</option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <label for="ingredientIds" class="form-label">Ingrédients</label>
            <select class="form-select" id="ingredientIds" name="ingredientIds" multiple size="6">
                <c:forEach var="ing" items="${ingredients}">
                    <option value="${ing.id}"
                      <c:if test="${item.ingredients != null && item.ingredients.contains(ing)}">selected</c:if>>
                        ${ing.name}
                    </option>
                </c:forEach>
            </select>
            <div class="form-text">Maintenez Ctrl (Cmd sur Mac) pour sélectionner plusieurs ingrédients</div>
        </div>

        <button type="submit" class="btn btn-primary">Enregistrer</button>
        <a href="${pageContext.request.contextPath}/mvcItems/findall" class="btn btn-secondary ms-2">Annuler</a>
    </form>
</div>

<script>
// Bootstrap validation (optionnel)
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
