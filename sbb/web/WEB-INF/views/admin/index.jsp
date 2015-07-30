<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
      <a class="navbar-brand" href="${pageContext.request.contextPath}">SBB Control Panel</a>
    </div>
    <ul class="nav navbar-top-links navbar-right">
      <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
          <i class="fa fa-envelope fa-fw"></i>  <i class="fa fa-caret-down"></i>
        </a>
        <!-- /.dropdown-messages -->
      </li>
      <!-- /.dropdown -->
    </ul>
    <div class="navbar-default sidebar" role="navigation">
      <div class="sidebar-nav navbar-collapse">
        <ul class="nav" id="side-menu">
          <li>
            <a href="${pageContext.request.contextPath}/trains"><i class="fa fa-dashboard fa-fw"></i>Trains</a>
          </li>
          <li>
            <a href="${pageContext.request.contextPath}/schedule"><i class="fa fa-dashboard fa-fw"></i>Stations</a>
          </li>
        </ul>
      </div>
      <!-- /.sidebar-collapse -->
    </div>
  </nav>

  <div id="page-wrapper">
    <div class="row">
      <div class="col-lg-12">
        <h3 class="page-header">Control panel</h3>
      </div>
      <!-- /.col-lg-12 -->
    </div>

    <table class="table">
      <thead>
      <tr>
        <th>Train #</th>
        <th>Places</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${trains}" var="train">
        <tr>
          <td>${train.trainNumber}</td>
          <td>${train.placesAmount}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>


</div>

</body>

</html>

