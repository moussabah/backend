
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/mvcAccount/manage">Gérer mon compte</a></li>
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/mvcCustomers/findall">Gérer Customers</a></li>
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/mvcStats">Statistiques</a></li>
                        <li class="nav-item"><a class="nav-link text-danger" href="${pageContext.request.contextPath}/logout">Déconnexion</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/login">Connexion</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>

