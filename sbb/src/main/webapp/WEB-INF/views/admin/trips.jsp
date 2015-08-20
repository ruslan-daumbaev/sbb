<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<tiles:insertDefinition name="adminTemplate">
  <tiles:putAttribute name="content">
    <div class="row">
      <div class="col-lg-12">
        <h3 class="page-header">Current trips</h3>
      </div>
      <!-- /.col-lg-12 -->
    </div>

    <table class="table table-striped table-bordered table-hover">
      <thead>
      <tr>
        <th>Train #</th>
        <th>Trip date</th>
        <th></th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${trips}" var="trip">
        <tr>
          <td>${trip.trainNumber}</td>
          <td>${trip.tripDate}</td>
          <td><a href="<c:url value="/admin/trip/"/>${trip.tripId}"  class="btn" id="trainId-${trip.tripId}">Show details</a></td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </tiles:putAttribute>
</tiles:insertDefinition>

