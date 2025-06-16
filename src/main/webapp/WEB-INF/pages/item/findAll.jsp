<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des Items</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h2 class="mb-4 text-center">Liste des Items</h2>

    <table class="table table-striped table-bordered shadow">
        <thead class="table-dark">
        <tr>
            <th>Réf</th>
            <th>Nom</th>
            <th>Prix</th>
            <th>Description</th>
            <th>Image</th>
            <th>Catégorie</th>
            <th>Ingrédients</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${liste}">
            <tr>
                <td>${item.ref}</td>
                <td>${item.name}</td>
                <td>${item.price} €</td>
                <td>${item.description}</td>
               <td>
                   <c:if test="${not empty item.pathImg}">
                       <a href="${pageContext.request.contextPath}/images/${item.pathImg}" target="_blank" rel="noopener noreferrer">
                           <img src="${pageContext.request.contextPath}/images/${item.pathImg}" alt="${item.name}" style="max-height: 60px;">
                       </a>
                   </c:if>
               </td>
                <td>${item.category}</td>
                <td>
                    <c:forEach var="ingredient" items="${item.ingredients}">
                        <span class="badge bg-secondary">${ingredient.name}</span>
                    </c:forEach>
                </td>
                <td>
                    <!-- Bouton Modifier -->
                    <a href="${pageContext.request.contextPath}/mvcItems/update/${item.ref}" class="btn btn-primary btn-sm me-1">Modifier</a>

                    <!-- Bouton Supprimer -->
                    <form action="${pageContext.request.contextPath}/mvcItems/delete/${item.ref}" method="get" style="display:inline;" onsubmit="return confirm('Supprimer cet item ?');">
                        <button type="submit" class="btn btn-danger btn-sm">Supprimer</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- Bouton Ajouter -->
    <a href="${pageContext.request.contextPath}/mvcItems/create" class="btn btn-success mt-3">+ Ajouter un item</a>
</div>

</body>
</html>
