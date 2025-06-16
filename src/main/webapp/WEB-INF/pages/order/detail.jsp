<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Détail de la Commande</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body class="bg-light">
<div class="container mt-5">
    <h2>Détail de la commande #${order.id}</h2>
    <p><strong>Statut :</strong> ${order.status}</p>
    <p><strong>Prix total :</strong> ${order.totalPrice} €</p>

    <h4 class="mt-4">Lignes de commande</h4>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Article</th>
                <th>Quantité</th>
                <th>Prix ligne</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="line" items="${order.order_lines}">
                <tr>
                    <td>${line.item.name}</td>
                    <td>${line.quantity}</td>
                    <td>${line.line_price} €</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <a href="${pageContext.request.contextPath}/mvcCustomers/findall" class="btn btn-secondary">Retour</a>
</div>
</body>
</html>
