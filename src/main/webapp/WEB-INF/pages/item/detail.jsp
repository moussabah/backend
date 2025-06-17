<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Détail de l'Article</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body class="bg-light">
<div class="container mt-5">
    <h2>Détail de l'article</h2>
    <p><strong>Nom :</strong> ${item.name}</p>
    <p><strong>Description :</strong> ${item.description}</p>
    <p><strong>Prix :</strong> ${item.price} €</p>
    <p><strong>Type :</strong> ${item.category}</p>

    <!-- Affichage de la photo si elle existe -->
    <c:if test="${not empty item.pathImg}">
        <div class="mb-3">
            <img src="${pageContext.request.contextPath}/images/${item.pathImg}" alt="Photo de ${item.name}" class="img-thumbnail" style="max-width: 300px;" />
        </div>
    </c:if>

    <!-- Liste des ingrédients -->
    <c:if test="${not empty item.ingredients}">
        <div class="mb-3">
            <strong>Ingrédients :</strong>
            <ul>
                <c:forEach var="ingredient" items="${item.ingredients}">
                    <li>${ingredient.name}</li>
                </c:forEach>
            </ul>
        </div>
    </c:if>

    <a href="${pageContext.request.contextPath}/mvcCustomers/findall" class="btn btn-secondary">Retour</a>
</div>
</body>
</html>
