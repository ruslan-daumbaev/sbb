<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
  <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
      <span class="sr-only">Toggle navigation</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="${pageContext.request.contextPath}/admin">SBB Control Panel</a>
  </div>
  <ul class="nav navbar-top-links navbar-right">

    <li>
      <sec:authorize access="isAuthenticated()">

    <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown" href="#">
        <div>
          <sec:authentication property="principal.username" />
        </div>
      </a>
      <ul class="dropdown-menu dropdown-user">
        <li><a href="#">User Profile</a>
        </li>
        <li class="divider"></li>
        <li>
          <spring:url var="logoutUrl" value="j_spring_security_logout" />
          <a href="${pageContext.request.contextPath}/${logoutUrl}">Logout</a>
        </li>
      </ul>
      <!-- /.dropdown-user -->
    </li>
    </sec:authorize>
    </li>
    <!-- /.dropdown -->
  </ul>
  <div class="navbar-default sidebar" role="navigation">
    <div class="sidebar-nav navbar-collapse">
      <ul class="nav" id="side-menu">
        <li>
          <a href="${pageContext.request.contextPath}/admin/trains">Trains</a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/admin/stations">Stations</a>
        </li>
      </ul>
    </div>
    <!-- /.sidebar-collapse -->
  </div>
</nav>