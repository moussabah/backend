<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Créer un Item</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body class="bg-light">

<div class="container mt-5">
    <h2 class="mb-4">Créer un nouvel Item</h2>

    <form id="createItemForm" action="${pageContext.request.contextPath}/mvcItems/create" method="post" enctype="multipart/form-data" class="needs-validation" novalidate>
        <div class="mb-3">
            <label for="ref" class="form-label">Référence</label>
            <input type="text" class="form-control" id="ref" name="ref" required />
        </div>

        <div class="mb-3">
            <label for="name" class="form-label">Nom</label>
            <input type="text" class="form-control" id="name" name="name" required />
        </div>

        <div class="mb-3">
            <label for="price" class="form-label">Prix (€)</label>
            <input type="number" step="0.01" class="form-control" id="price" name="price" required />
        </div>

        <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <textarea class="form-control" id="description" name="description"></textarea>
        </div>

        <!-- Champ fichier image -->
        <div class="mb-3">
            <label for="imageFile" class="form-label">Image (choisissez un fichier)</label>
            <input type="file" class="form-control" id="imageFile" name="imageFile" accept="image/*" />
        </div>

        <!-- Tu peux supprimer ce champ si tu veux, car il sera remplacé par l'upload
        <div class="mb-3">
            <label for="pathImg" class="form-label">Chemin Image</label>
            <input type="text" class="form-control" id="pathImg" name="pathImg" />
        </div>
        -->

        <div class="mb-3">
            <label for="category" class="form-label">Catégorie</label>
            <select class="form-select" id="category" name="category" required>
                <option value="" disabled selected>Choisissez une catégorie</option>
                <c:forEach var="cat" items="${categories}">
                    <option value="${cat}">${cat}</option>
                </c:forEach>
            </select>
        </div>

        <!-- Sélection des ingrédients -->
        <div class="mb-3">
            <label for="ingredientIds" class="form-label">Ingrédients</label>
            <select multiple class="form-select" id="ingredientIds" name="ingredientIds">
                <c:forEach var="ingredient" items="${ingredients}">
                    <option value="${ingredient.id}">${ingredient.name}</option>
                </c:forEach>
            </select>
            <div class="d-flex align-items-center mt-2">
                <button type="button" class="btn btn-outline-primary me-2"
                    id="addIngredientBtn">
                    +
                </button>
                <small>Ajouter un ingrédient</small>
            </div>
            <div class="form-text">Utilisez Ctrl (ou Cmd) pour sélectionner plusieurs ingrédients.</div>
        </div>

        <button type="submit" class="btn btn-success">Créer</button>
        <a href="${pageContext.request.contextPath}/mvcItems/findall" class="btn btn-secondary ms-2">Annuler</a>
    </form>
</div>

<script>
(() => {
  'use strict'

  const form = document.getElementById('createItemForm');
  const storageKey = 'createItemFormData';

  // Charger les données sauvegardées au chargement de la page
  window.addEventListener('load', () => {
    const savedData = sessionStorage.getItem(storageKey);
    if (savedData) {
      const data = JSON.parse(savedData);
      if (data.ref) document.getElementById('ref').value = data.ref;
      if (data.name) document.getElementById('name').value = data.name;
      if (data.price) document.getElementById('price').value = data.price;
      if (data.description) document.getElementById('description').value = data.description;
      if (data.category) document.getElementById('category').value = data.category;
      if (data.ingredientIds && Array.isArray(data.ingredientIds)) {
        const select = document.getElementById('ingredientIds');
        for (const option of select.options) {
          option.selected = data.ingredientIds.includes(option.value);
        }
      }
    }
  });

  // Avant d'aller créer un nouvel ingrédient, sauvegarder le formulaire
  document.getElementById('addIngredientBtn').addEventListener('click', () => {
    const ingredientSelect = document.getElementById('ingredientIds');
    const selectedIngredientIds = Array.from(ingredientSelect.selectedOptions).map(opt => opt.value);

    const formData = {
      ref: document.getElementById('ref').value,
      name: document.getElementById('name').value,
      price: document.getElementById('price').value,
      description: document.getElementById('description').value,
      category: document.getElementById('category').value,
      ingredientIds: selectedIngredientIds
    };
    sessionStorage.setItem(storageKey, JSON.stringify(formData));

    // Rediriger vers la création d'ingrédient, en ajoutant le paramètre returnTo
    window.location.href = '${pageContext.request.contextPath}/mvcIngredients/create?returnTo=/mvcItems/create';
  });

  // Validation Bootstrap
  Array.from(document.querySelectorAll('.needs-validation')).forEach(form => {
    form.addEventListener('submit', event => {
      if (!form.checkValidity()) {
        event.preventDefault()
        event.stopPropagation()
      } else {
        // Nettoyer la sessionStorage si le form est validé
        sessionStorage.removeItem(storageKey);
      }
      form.classList.add('was-validated')
    }, false)
  });

})();
</script>

</body>
</html>
