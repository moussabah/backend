<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Connexion</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body class="bg-light">
<div class="container mt-5" style="max-width: 400px;">
    <h2>Connexion</h2>
    <form method="post" action="<c:url value='/login' />">
        <div class="mb-3">
            <label for="username" class="form-label">Nom d'utilisateur</label>
            <input type="text" class="form-control" id="username" name="username" required autofocus />
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Mot de passe</label>
            <input type="password" class="form-control" id="password" name="password" required />
        </div>
        <c:if test="${param.error != null}">
            <div class="alert alert-danger">Nom d'utilisateur ou mot de passe incorrect.</div>
        </c:if>
        <button type="submit" class="btn btn-primary w-100">Se connecter</button>
    </form>
</div>
</body>
</html>
