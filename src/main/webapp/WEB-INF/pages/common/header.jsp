<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<nav>
  <sec:authorize access="hasRole('ADMIN')">
    <a href="${pageContext.request.contextPath}/employes/findall">Gérer les employés</a>
  </sec:authorize>

  <sec:authorize access="isAuthenticated()">
    <a href="${pageContext.request.contextPath}/mvcItems/findall">Gérer les items</a>
    <a href="${pageContext.request.contextPath}/account">Gérer mon compte</a>
    <a href="${pageContext.request.contextPath}/statistiques">Statistiques</a>
    <a href="${pageContext.request.contextPath}/logout">Déconnexion</a>
  </sec:authorize>

  <sec:authorize access="!isAuthenticated()">
    <a href="${pageContext.request.contextPath}/login">Connexion</a>
  </sec:authorize>
</nav>
