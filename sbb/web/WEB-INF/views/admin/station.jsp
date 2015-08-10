<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<tiles:insertDefinition name="adminTemplate">
  <tiles:putAttribute name="content">
    <div class="row">
      <div class="col-lg-12">
        <h3 class="page-header">Station details</h3>
      </div>
      <!-- /.col-lg-12 -->
    </div>

    <div>
      <p>
        <button id="saveStationButton" type="button" class="btn btn-outline btn-primary">Save</button>
        <a href="<c:url value="/admin/stations"/>" id="cancelButton" type="button" class="btn btn-outline btn-default">Cancel</a>
      </p>
    </div>

    <div >
      <div >
        <form:form id="station-data-form" method="post" servletRelativeAction="/admin/saveStation" modelAttribute="stationModel">
          <div class="form-group ">
            <label for="stationName" class="control-label">Station name:</label>
            <form:input type="text" path="stationName"
                        class="form-control input-common" id="stationName" name="stationName"/>
            <form:hidden path="id"/>
          </div>

          <div class="form-group ">


          </div>
        </form:form>
      </div>
    </div>
  <script>
    $( "#saveStationButton" ).click(function() {
      $( "#station-data-form" ).submit();
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
</tiles:putAttribute>
</tiles:insertDefinition>

