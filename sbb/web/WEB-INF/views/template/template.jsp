<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
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
  <link href="<c:url value="/css/jquery-ui.min.css" />" rel="stylesheet">
  <link href="<c:url value="/css/jquery.timepicker.min.css" />" rel="stylesheet">

  <script src="<c:url value="/js/jquery.min.js" />"></script>
  <script src="<c:url value="/js/bootstrap.min.js" />"></script>
  <script src="<c:url value="/js/metisMenu.min.js" />"></script>
  <script src="<c:url value="/js/sb-admin-2.js" />"></script>
  <script src="<c:url value="/js/jquery-ui.min.js" />"></script>
  <script src="<c:url value="/js/jquery.validate.min.js" />"></script>

  <script src="<c:url value="/js/jquery.timepicker.min.js" />"></script>

</head>

<body>

<tiles:insertAttribute name="modalDialog" />
<div>

  <div>
    <tiles:insertAttribute name="header" />
  </div>


  <div id="page-wrapper">
    <tiles:insertAttribute name="content" />
  </div>

</div>

</body>

</html>

