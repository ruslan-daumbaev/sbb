<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tiles:insertDefinition name="defaultTemplate">
  <tiles:putAttribute name="content">


    <div class="container-fluid">
      <div class="row">
        <div class="col-lg-12">
          <h3 class="page-header">Trains search</h3>
        </div>
      </div>
    </div>

    <div>
      <label for="from-time" class="control-label">From station:</label>
      <p>
        <select name="stations-cb" id="station-first-select" class="form-control input-common">
          <option value="0">select station</option>
          <c:forEach items="${stationModels}" var="station">
            <option value="${station.id}">
                ${station.stationName}
            </option>
          </c:forEach>
        </select>
      </p>
    </div>

    <div>
      <label for="from-time" class="control-label">To station:</label>
      <p>
        <select name="stations-cb" id="station-second-select" class="form-control input-common">
          <option value="0">select station</option>
          <c:forEach items="${stationModels}" var="station">
            <option value="${station.id}">
                ${station.stationName}
            </option>
          </c:forEach>
        </select>
      </p>
    </div>

    <div>
      <label for="from-time" class="control-label">From time:</label>
      <p>
        <input id="from-time"  class="time-input form-control input-common"/>

      </p>

    </div>
    <div>
      <label for="to-time" class="control-label">To time:</label>
      <p>
        <input id="to-time"  class="time-input form-control input-common"/>

      </p>

    </div>

    <script>
      $('#trains-panel').hide();
      $(document).ready(function(){
        $('.time-input').timepicker({
          timeFormat: 'HH:mm',
          startTime: new Date(0,0,0,0,0,0),
          minTime: '00:00',
          maxHour: 23,
          maxMinutes: 59,
          interval: 10
        });

        $('#station-first-select').change(function() {
          var secondVal = $('#station-second-select').val();

          if ($(this).val() === '0' || secondVal === '0') {
            $('#trains-panel').hide();
          }
          else{
            var stationFirstId = $(this).val();
            findTrains(stationFirstId, secondVal);
          }
        });

        $('#station-second-select').change(function() {
          var firstVal = $('#station-first-select').val();

          if ($(this).val() === '0' || firstVal === '0') {
            $('#trains-panel').hide();
          }
          else{
            var stationSecondId = $(this).val();
            findTrains(firstVal, stationSecondId);
          }
        });

      });

      function findTrains(stationFirstId, stationSecondId){
        var fromTime = $('#from-time').val();
        var toTime = $('#to-time').val();
        $.ajax({
          url: '<c:url value="/findTrainsJson"/>',
          type: 'GET',
          data: {'stationFirstId': stationFirstId, 'stationSecondId': stationSecondId},
          error: function() {
            //$('#info').html('<p>An error has occurred</p>');
          },
          dataType: 'json',
          success: function(data) {
            var schedules = data;
            var content = '';
            for (var i = 0; i< schedules.length; i++) {
              content += '<tr>';
              content += '<td>'+schedules[i].trainNumber+'</td>';
              content += '<td>'+schedules[i].trainTime+'</td>';
              content += '<td><a href="buyTicket/' +schedules[i].id+'">Buy ticket</a></td>';
              content += '</tr>';
            }
            $('#stations-table-body').empty().append(content);
            $('#trains-panel').removeClass('hidden');
            $('#trains-panel').show();
          }
        });
      }
    </script>

  </tiles:putAttribute>
</tiles:insertDefinition>

