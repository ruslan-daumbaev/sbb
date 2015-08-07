<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<tiles:insertDefinition name="adminTemplate">
  <tiles:putAttribute name="content">
    <div class="row">
      <div class="col-lg-12">
        <h3 class="page-header">Stations</h3>
      </div>
      <!-- /.col-lg-12 -->
    </div>

    <div>
      <p>
        <a href="${pageContext.request.contextPath}/admin/addStation" id="addStationButton" type="button"
           class="btn btn-outline btn-default">Add station</a>
      </p>
    </div>

    <table id="stations-table" class="table table-striped table-bordered table-hover">
      <thead>
      <tr>
        <th>Station name</th>
        <th></th>
        <th></th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${stations}" var="station">
        <tr>
          <td>${station.stationName}</td>
          <td>
            <a href="<c:url value="/admin/station"/>?stationId=${station.id}"  class="btn editStationLink" id="stationId-${station.id}">Edit</a>
          </td>
            <td>
            <a href="<c:url value="/admin/stationSchedule"/>?stationId=${station.id}"  class="btn editStationLink" id="stationIdSchedule-${station.id}">Schedule</a>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>

      <script>
          $(document).ready(function(){
              $('#stations-table').DataTable({
                  "paging": false,
                  "info": false,
                  "columnDefs": [
                      {
                          "targets": [ 1 ],
                          "visible": true,
                          "searchable": false,
                          "orderable": false
                      },
                      {
                          "targets": [ 2 ],
                          "visible": true,
                          "searchable": false,
                          "orderable": false
                      }
                  ]
              } );
          })
      </script>

</tiles:putAttribute>
</tiles:insertDefinition>

