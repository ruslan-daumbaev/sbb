<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>SBB Admin</title>

  <link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet">
  <link href="<c:url value="/css/metisMenu.min.css" />" rel="stylesheet">
  <link href="<c:url value="/css/sb-admin-2.css" />" rel="stylesheet">
  <link href="<c:url value="/css/font-awesome.min.css" />" rel="stylesheet">

  <script src="<c:url value="/js/sb-admin-2.js" />"></script>
  <script src="<c:url value="/js/bootstrap.min.js" />"></script>
  <script src="<c:url value="/js/metisMenu.min.js" />"></script>
  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
  <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->

</head>

<body>

<div class="container">
  <div class="row">
    <div class="col-md-4 col-md-offset-4">
      <div class="login-panel panel panel-default">
        <div class="panel-heading">
          <h3 class="panel-title">Please Sign In</h3>
        </div>
        <div class="panel-body">
          <spring:url var="loginUrl" value="j_spring_security_check" />
          <form role="form" action="<c:url value='${loginUrl}'/>" method="post">
            <fieldset>
              <div class="form-group">
                <input class="form-control" placeholder="Login" name="j_username" type="text" autofocus>
              </div>
              <div class="form-group">
                <input class="form-control" placeholder="Password" name="j_password" type="password" value="">
              </div>
              <!-- Change this to a button or input when using this as a form -->
              <input type="submit" class="btn btn-lg btn-success btn-block" value="Login"/>
            </fieldset>
          </form>
        <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
          <div class="label-warning">
               ${SPRING_SECURITY_LAST_EXCEPTION.message}
          </div>
        </c:if>
        </div>
      </div>
    </div>
  </div>
</div>

</body>

</html>
