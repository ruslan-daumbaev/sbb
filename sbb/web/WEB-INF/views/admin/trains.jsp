<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<tiles:insertDefinition name="adminTemplate">
  <tiles:putAttribute name="content">
    <div class="row">
      <div class="col-lg-12">
        <h3 class="page-header">Trains</h3>
      </div>
      <!-- /.col-lg-12 -->
    </div>

    <div>
      <p>
        <a href="${pageContext.request.contextPath}/admin/addTrain" id="addTrainButton" type="button"
           class="btn btn-outline btn-default">Add train</a>
      </p>
    </div>

    <table class="table table-striped table-bordered table-hover" id="trainsTable">
      <thead>
      <tr>
        <th>Train #</th>
        <th>Places</th>
        <th></th>
      </tr>
      </thead>
      <tbody id="trainsTableBody">
      <c:forEach items="${trains}" var="train">
        <tr>
          <td >${train.trainNumber}</td>
          <td>${train.placesAmount}</td>
          <td><a href="<c:url value="/admin/train"/>?trainId=${train.id}"  class="btn editTrainLink" id="trainId-${train.id}">Edit</a></td>
        </tr>
      </c:forEach>
      </tbody>
    </table>

      <script>
          $(document).ready(function(){
              $('#trainsTable').DataTable({
                  "paging": false,
                  "info": false,
                  "columnDefs": [
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
