<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>SBB</title>

  <link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet">
  <link href="<c:url value="/css/metisMenu.min.css" />" rel="stylesheet">
  <link href="<c:url value="/css/sb-admin-2.css" />" rel="stylesheet">
  <link href="<c:url value="/css/font-awesome.min.css" />" rel="stylesheet">

  <script src="<c:url value="/js/jquery.min.js" />"></script>
  <script src="<c:url value="/js/sb-admin-2.js" />"></script>
  <script src="<c:url value="/js/bootstrap.min.js" />"></script>
  <script src="<c:url value="/js/metisMenu.min.js" />"></script>

</head>

<body>

<div id="wrapper">

  <!-- Navigation -->
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
            <a href="${logoutUrl}">Logout</a>
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
            <a>Stations</a>
          </li>
        </ul>
      </div>
      <!-- /.sidebar-collapse -->
    </div>
  </nav>

  <div id="page-wrapper">
    <div class="row">
      <div class="col-lg-12">
        <h3 class="page-header">Stations</h3>
      </div>
      <!-- /.col-lg-12 -->
    </div>

    <div>
      <p>
        <button type="button" class="btn btn-outline btn-default">Add station</button>
        <button type="button" class="btn btn-outline btn-primary">Refresh</button>
      </p>
    </div>

    <table class="table">
      <thead>
      <tr>
        <th>Station name</th>
        <th>Create date</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${stations}" var="station">
        <tr>
          <td>${station.stationName}</td>
          <td>${station.insDate}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
  <div class="modal fade" id="addStationWindow" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title" id="exampleModalLabel">Station details</h4>
        </div>
        <div class="modal-body">
          <form>
            <div class="form-group">
              <label for="train-number" class="control-label">Station name:</label>
              <input type="text" class="form-control" id="train-number">
              <input type="hidden" id="train-id">
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button id="saveTrainButton" type="button" class="btn btn-primary" min="1">Save</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>

</div>

</body>

</html>

