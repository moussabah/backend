<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Détails du client</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body class="container mt-5">

<h2>Détails du client</h2>

<div class="card mb-4">
    <div class="card-header">
        <strong>${customer.firstname} ${customer.lastname}</strong>
    </div>
    <div class="card-body">
        <p><strong>Téléphone :</strong> ${customer.phone}</p>

        <c:if test="${not empty customer.photo}">
            <p><strong>Photo :</strong><br/>
                <img src="${pageContext.request.contextPath}/images/customers/${customer.photo}" class="img-thumbnail" style="max-width: 200px;" />
            </p>
        </c:if>

        <c:if test="${not empty customer.addresses}">
            <p><strong>Adresses :</strong></p>
            <ul>
                <c:forEach var="addr" items="${customer.addresses}">
                    <li>${addr.streetNumber} ${addr.street}, ${addr.postalCode} ${addr.city}, ${addr.country}</li>
                </c:forEach>
            </ul>
        </c:if>

        <c:if test="${not empty customer.items}">
            <p><strong>Favoris :</strong></p>
            <ul>
                <c:forEach var="item" items="${customer.items}">
                    <li>
                        <a href="${pageContext.request.contextPath}/mvcItems/detail/${item.ref}">
                            ${item.name}
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </c:if>

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

<a class="btn btn-secondary" href="${pageContext.request.contextPath}/mvcCustomers/findall">Retour à la liste</a>

</body>
</html>
