<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tiles:insertDefinition name="defaultTemplate">
  <tiles:putAttribute name="content">

      <div class="row">
        <div class="col-lg-12">
          <h3 class="page-header">Schedule</h3>
        </div>
      </div>
    <div>
      <p>
        <select name="stations-cb" id="stations-select" class="form-control input-common">
          <option value="0">Select station</option>
          <c:forEach items="${stationModels}" var="station">
            <option value="${station.id}">
                ${station.stationName}
            </option>
          </c:forEach>
        </select>
      </p>
    </div>

    <div >
      <div >
        <div class="form-group hidden" id="trains-panel">
          <label for="trains-table" class="control-label">Trains:</label>
          <table id="trains-table" class="table table-striped table-bordered table-hover">
            <thead>
            <tr>
              <th>Train #</th>
              <th>Time</th>
              <th></th>
            </tr>
            </thead>
            <tbody id="stations-table-body">

            </tbody>
          </table>
        </div>
      </div>
    </div>
    <script>
      $('#trains-panel').hide();
      $(document).ready(function(){

        $('#stations-select').change(function() {

          if ($(this).val() === '0') {
            $('#trains-panel').hide();
          }
          else{
            var stationId = $(this).val();

            $.ajax({
              url: '<c:url value="/scheduleJson"/>',
              type: 'GET',
              data: {'stationId': stationId },
              error: function() {
                //$('#info').html('<p>An error has occurred</p>');
              },
              dataType: 'json',
              success: function(data) {
                var schedules = data.schedules;
                var content = '';
                for (var i = 0; i< schedules.length; i++) {
                  content += '<tr>';
                  content += '<td>'+schedules[i].trainNumber+'</td>';
                  content += '<td>'+schedules[i].trainTime+'</td>';
                  content += '<td><a href="buyTicket?trainId='+schedules[i].trainId+'&stationId='+ stationId +'">Buy ticket</a></td>';
                  content += '</tr>';
                }
                $('#stations-table-body').empty().append(content);
                $('#trains-panel').removeClass('hidden');
                $('#trains-panel').show();
              }
            });


          }
        });
      });
    </script>
  </tiles:putAttribute>
</tiles:insertDefinition>

