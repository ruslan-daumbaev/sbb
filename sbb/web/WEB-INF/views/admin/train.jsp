<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<tiles:insertDefinition name="adminTemplate">
  <tiles:putAttribute name="content">

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
        <form:form id="train-data-form" method="post" servletRelativeAction="/admin/saveTrain" modelAttribute="trainModel">
          <div class="form-group ">
            <label for="trainNumber" class="control-label">Train number:</label>
            <form:input type="text" path="trainNumber"
                        class="form-control input-common" id="trainNumber" name="trainNumber"/>
            <form:hidden path="id"/>
            <span><form:errors path="${trainNumber}"/></span>
          </div>
          <div class="form-group ">
            <label for="placesAmount" class="control-label">Available places:</label>
            <form:input type="number" path="placesAmount" class="form-control input-common" id="placesAmount"
                        name="placesAmount"/>
            <span><form:errors path="${placesAmount}"/></span>
          </div>
          <div class="form-group ">
            <label for="stations-table" class="control-label">Stations:</label>
            <table class="table table-striped table-bordered table-hover" id="stations-table">
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
                  <td><form:checkbox path="stations[${status.index}].isSelected" id="stationCb-${station.id}" class="station-checkbox"/></td>
                  <td>
                      ${station.stationName}
                    <form:hidden path="stations[${status.index}].id"/>
                  </td>
                  <td>
                    <form:input path="stations[${status.index}].trainTime" disabled="${!station.isSelected}" id="stationDp-${station.id}"  class="time-input"/>
                  </td>
                </tr>
              </c:forEach>
              </tbody>
            </table>
          </div>
        </form:form>
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

              $('.station-checkbox').click(function(){
                  var id = this.id;
                  var datePickerId = "#stationDp-" + id.split('-')[1];
                  $(datePickerId).prop('disabled', !this.checked);
              });
          });
      </script>
  </tiles:putAttribute>
</tiles:insertDefinition>

