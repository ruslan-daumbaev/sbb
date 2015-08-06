<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="forn" uri="http://www.springframework.org/tags/form" %>
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
  <link href="<c:url value="/css/dataTables.bootstrap.css" />" rel="stylesheet">
  <link href="<c:url value="/css/sb-admin-2.css" />" rel="stylesheet">
  <link href="<c:url value="/css/font-awesome.min.css" />" rel="stylesheet">
  <link href="<c:url value="/css/jquery.timepicker.min.css" />" rel="stylesheet">

  <script src="<c:url value="/js/jquery.min.js" />"></script>
  <script src="<c:url value="/js/sb-admin-2.js" />"></script>
  <script src="<c:url value="/js/bootstrap.min.js" />"></script>
  <script src="<c:url value="/js/metisMenu.min.js" />"></script>
  <script src="<c:url value="/js/jquery.dataTables.min.js" />"></script>
  <script src="<c:url value="/js/jquery.timepicker.min.js" />"></script>

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
            <a href="${pageContext.request.contextPath}/admin/stations">Stations</a>
          </li>
        </ul>
      </div>
      <!-- /.sidebar-collapse -->
    </div>
  </nav>

  <div id="page-wrapper">
    <div class="row">
      <div class="col-lg-12">
        <h3 class="page-header">Train details</h3>
      </div>
      <!-- /.col-lg-12 -->
    </div>

    <div>
      <p>
        <button id="addTrainButton" type="button" class="btn btn-outline btn-primary">Save</button>
        <a href="<c:url value="/admin/trains"/>" id="cancelButton" type="button" class="btn btn-outline btn-default">Cancel</a>
      </p>
    </div>

    <div >
      <div >
        <form:form id="train-data-form" method="post" action="saveTrain" modelAttribute="trainModel">
          <div class="form-group ">
            <label for="trainNumber" class="control-label">Train number:</label>
            <form:input type="text" path="trainNumber"
                        class="form-control input-common" id="trainNumber" name="trainNumber"/>
            <form:hidden path="id"/>
          </div>
          <div class="form-group ">
            <label for="placesAmount" class="control-label">Available places:</label>
            <forn:input type="number" path="placesAmount" class="form-control input-common" id="placesAmount"
                        name="placesAmount"/>
          </div>
          <div class="form-group ">
            <label for="stations-table" class="control-label">Stations:</label>
            <table class="table table-striped table-bordered table-hover" id="stations-table" name="">
              <thead>
              <tr>
                <th></th>
                <th>Station name</th>
                <th>Time</th>
              </tr>
              </thead>
              <tbody id="stations-table-body">
              <c:forEach items="${trainModel.stations}" var="station" varStatus="status">
                <tr>
                  <td><form:checkbox path="stations[${status.index}].isSelected"/></td>
                  <td>
                      ${station.stationName}
                    <form:hidden path="stations[${status.index}].id"/>
                  </td>
                  <td>
                    <form:input path="stations[${status.index}].trainTime"  class="time-input"/>
                  </td>
                </tr>
              </c:forEach>
              </tbody>
            </table>
          </div>
        </form:form>
      </div>
    </div>
  </div>
  <script>
    $( "#addTrainButton" ).click(function() {
      $( "#train-data-form" ).submit();
    });

    $(document).ready(function(){
      $('.time-input').timepicker({
        timeFormat: 'HH:mm',
        startTime: new Date(0,0,0,0,0,0),
        minTime: '00:00',
        maxHour: 23,
        maxMinutes: 59,
        interval: 10
      });
    });
  </script>

</div>

</body>

</html>

