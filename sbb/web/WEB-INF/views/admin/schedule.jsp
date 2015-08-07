<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<tiles:insertDefinition name="adminTemplate">
  <tiles:putAttribute name="content">
      <div class="row">
          <div class="col-lg-12">
              <h3 class="page-header">${stationModel.stationName} schedule</h3>
          </div>
          <!-- /.col-lg-12 -->
      </div>

      <div>
          <p>
              <a href="<c:url value="/admin/stations"/>" id="cancelButton" type="button" class="btn btn-outline btn-default">Back</a>
          </p>
      </div>

      <div >
          <div >
                  <div class="form-group ">
                      <label for="trains-table" class="control-label">Trains:</label>
                      <table id="trains-table" class="table table-striped table-bordered table-hover">
                          <thead>
                          <tr>
                              <th>Train #</th>
                              <th>Time</th>
                          </tr>
                          </thead>
                          <tbody id="stations-table-body">
                          <c:forEach items="${stationModel.schedules}" var="schedule" varStatus="status">
                              <tr>
                                  <td>
                                      ${schedule.trainNumber}
                                  </td>
                                  <td>
                                  ${schedule.trainTime}
                                  </td>
                              </tr>
                          </c:forEach>
                          </tbody>
                      </table>
                  </div>
          </div>
      </div>
  </tiles:putAttribute>
</tiles:insertDefinition>
