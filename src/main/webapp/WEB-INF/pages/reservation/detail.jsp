<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Détail de la Réservation</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body class="bg-light">
<div class="container mt-5">
    <h2>Détail de la réservation #${reservation.id}</h2>
    <p><strong>Date :</strong> ${reservation.date}</p>
    <p><strong>Créneau :</strong> ${reservation.slot}</p>
    <p><strong>Nombre de personnes :</strong> ${reservation.nbPersons}</p>

    <a href="${pageContext.request.contextPath}/mvcCustomers/findall" class="btn btn-secondary">Retour</a>
</div>
</body>
</html>
