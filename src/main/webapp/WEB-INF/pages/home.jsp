<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Accueil</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="row">
        <!-- CAROUSEL à gauche -->
        <div class="col-md-8">
            <div id="itemCarousel" class="carousel slide" data-bs-ride="carousel">
                <div class="carousel-inner">
                    <c:forEach var="item" items="${items}" varStatus="status">
                        <div class="carousel-item ${status.first ? 'active' : ''}">
                            <img src="${pageContext.request.contextPath}/images/${item.pathImg}" class="d-block w-100" alt="${item.name}" style="max-height:400px; object-fit:cover;">
                            <div class="carousel-caption d-none d-md-block bg-dark bg-opacity-50 rounded">
                                <h5>${item.name}</h5>
                                <p>${item.description}</p>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#itemCarousel" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Précédent</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#itemCarousel" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Suivant</span>
                </button>
            </div>
        </div>

        <!-- Zone connexion à droite -->
        <div class="col-md-4 d-flex flex-column justify-content-center align-items-center">
            <c:choose>
                <c:when test="${not empty sessionScope.user}">
                    <h4>Bienvenue, ${sessionScope.user.name}</h4>
                    <a href="${pageContext.request.contextPath}/mvcItems/findall" class="btn btn-primary mb-3 w-100">Voir la carte</a>
                    <a href="${pageContext.request.contextPath}/mvcAccount/manage" class="btn btn-outline-secondary mb-2 w-100">Gérer mon compte</a>
                    <a href="${pageContext.request.contextPath}/mvcCustomers/findall" class="btn btn-outline-secondary mb-2 w-100">Gérer Customers</a>
                    <a href="${pageContext.request.contextPath}/mvcStats" class="btn btn-outline-secondary mb-2 w-100">Statistiques</a>
                    <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger w-100">Déconnexion</a>
                </c:when>
                <c:otherwise>
                    <h4>Connectez-vous</h4>
                    <a href="${pageContext.request.contextPath}/login" class="btn btn-success w-100">Connexion</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
