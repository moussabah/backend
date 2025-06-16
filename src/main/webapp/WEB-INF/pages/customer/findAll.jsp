<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Liste des clients</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body class="container mt-5">

<div class="d-flex justify-content-between align-items-center mb-4">
    <h2>Clients</h2>
    <a class="btn btn-success" href="${pageContext.request.contextPath}/mvcCustomers/create">Ajouter un client</a>
</div>

<c:forEach var="customer" items="${liste}">
    <div class="card mb-4">
        <div class="card-header d-flex justify-content-between align-items-center">
            <strong>${customer.firstname} ${customer.lastname}</strong>
            <div>
                <a class="btn btn-sm btn-info me-1" href="${pageContext.request.contextPath}/mvcCustomers/detail/${customer.id}">Infos</a>
                <a class="btn btn-sm btn-primary me-1" href="${pageContext.request.contextPath}/mvcCustomers/update/${customer.id}">Modifier</a>
                <a class="btn btn-sm btn-danger" href="${pageContext.request.contextPath}/mvcCustomers/delete/${customer.id}" onclick="return confirm('Confirmer la suppression ?')">Supprimer</a>
            </div>
        </div>
        <div class="card-body">
            <p><strong>Téléphone :</strong> ${customer.phone}</p>

            <!-- Favoris -->
            <c:if test="${not empty customer.items}">
                <p><strong>Favoris :</strong>
                    <c:forEach var="item" items="${customer.items}">
                        <span class="badge bg-secondary me-1">${item.name}</span>
                    </c:forEach>
                </p>
            </c:if>

            <!-- Commandes -->
            <c:if test="${not empty customer.orders}">
                <p><strong>Commandes :</strong></p>
                <ul>
                    <c:forEach var="order" items="${customer.orders}">
                        <li>
                            <a href="${pageContext.request.contextPath}/mvcOrders/detail/${order.id}">
                                Commande #${order.id} - ${order.status} - ${order.totalPrice} €
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </c:if>

            <!-- Réservations -->
            <c:if test="${not empty customer.reservations}">
                <p><strong>Réservations :</strong></p>
                <ul>
                    <c:forEach var="res" items="${customer.reservations}">
                        <li>
                            <a href="${pageContext.request.contextPath}/mvcReservations/detail/${res.id}">
                                ${res.date} - ${res.slot} - ${res.nbPersons} personnes
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </c:if>
        </div>
    </div>
</c:forEach>

</body>
</html>
