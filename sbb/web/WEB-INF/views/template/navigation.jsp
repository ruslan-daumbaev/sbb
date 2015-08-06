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
    <a class="navbar-brand" href="${pageContext.request.contextPath}">SBB</a>
  </div>
  <ul class="nav navbar-top-links navbar-right">
    <li>
      <a href="${pageContext.request.contextPath}/admin">
        <div>Admin Panel</div>
      </a>
    </li>
  </ul>

  <div class="navbar-default sidebar" role="navigation">
    <div class="sidebar-nav navbar-collapse">
      <ul class="nav" id="side-menu">
        <li>
          <a href="${pageContext.request.contextPath}/trains"><i class="fa fa-search fa-fw"></i> Search train</a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/schedule"><i class="fa fa-table fa-fw"></i> Schedule</a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/tickets"><i class="fa fa-ticket fa-fw"></i> Buy ticket</a>
        </li>
      </ul>
    </div>
    <!-- /.sidebar-collapse -->
  </div>
</nav>
