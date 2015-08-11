<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<tiles:insertDefinition name="adminTemplate">
  <tiles:putAttribute name="content">
    <div class="row">
      <div class="col-lg-12">
        <h3 class="page-header">Trip details</h3>
      </div>
      <!-- /.col-lg-12 -->
    </div>

    <div>
      <p>
        <a href="<c:url value="/admin/trips"/>" id="cancelButton" type="button" class="btn btn-outline btn-default">Back</a>
      </p>
    </div>


    <div >
      <div >
         <div class="form-group ">
            <label class="control-label">Train number:</label>
            <label class="control-label input-common" id="trainNumber">${tripDetailsModel.trainNumber}</label>
          </div>
          <div class="form-group ">
            <label class="control-label">Places reserved:</label>
            <label class="control-label input-common">${tripDetailsModel.reservedPlacesAmount} of ${tripDetailsModel.placesAmount}</label>
          </div>
          <div class="form-group ">
              <label class="control-label">Trip date:</label>
              <label class="control-label input-common" id="tripDate">${tripDetailsModel.tripDate}</label>
          </div>
          <div class="form-group ">
            <label for="passengers-table" class="control-label">Passengers:</label>
            <table class="table table-striped table-bordered table-hover" id="passengers-table">
              <thead>
              <tr>
                <th>First name</th>
                <th>Last name</th>
                <th>Birth date</th>
                <th>Registration date</th>
              </tr>
              </thead>
              <tbody id="stations-table-body">
              <c:forEach items="${tripDetailsModel.passengers}" var="passenger" varStatus="status">
                <tr>
                    <td>${passenger.firstName}</td>
                    <td>${passenger.lastName}</td>
                    <td>${passenger.birthDateString}</td>
                    <td>${passenger.registrationDate}</td>
                </tr>
              </c:forEach>
              </tbody>
            </table>
          </div>
      </div>
    </div>
      <script>
          $(document).ready(function(){
              $('#passengers-table').DataTable({
                  "paging": false,
                  "info": false
              } );
          })
      </script>
  </tiles:putAttribute>
</tiles:insertDefinition>

