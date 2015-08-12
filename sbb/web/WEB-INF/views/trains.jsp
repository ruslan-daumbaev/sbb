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

    <div class="time-seacrh-line">
      <label for="from-time" class="control-label train-search-label">Station from</label>
        <select name="stations-cb" id="station-first-select" class="form-control input-train-search" required>
          <option value="0">select station</option>
          <c:forEach items="${stationModels}" var="station">
            <option value="${station.id}">
                ${station.stationName}
            </option>
          </c:forEach>
        </select>

      <label for="from-time" class="control-label">to</label>
      <select name="stations-cb" id="station-second-select" class="form-control input-train-search" required>
        <option value="0">select station</option>
        <c:forEach items="${stationModels}" var="station">
          <option value="${station.id}">
              ${station.stationName}
          </option>
        </c:forEach>
      </select>
    </div>

    <div class="time-seacrh-line">
      <label for="from-time" class="control-label train-search-label">Time from</label>
      <input id="from-time"  class="form-control input-train-search train-search-time-input" required/>

      <label for="to-time" class="control-label">to</label>
      <input id="to-time"  class="form-control input-train-search train-search-time-input" required/>
    </div>
    <div class="time-seacrh-line">
      <button id="findTrainsButton" type="button" class="btn btn-outline btn-primary">Find trains</button>
    </div>

    <p/>

    <div>

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

      <div id="wait" class="wait-indicator">
        <label></label>
      </div>
    </div>

    <script>
      $('#trains-panel').hide();

      $("#wait").css("display", "none");
      $(document).ajaxStart(function(){
        $("#wait").css("display", "block");
        //$('#stations-select').prop('disabled', true);
      });

      $(document).ajaxComplete(function(){
        $("#wait").css("display", "none");
        //$('#stations-select').prop('disabled', false);
      });


      $('#findTrainsButton').click(function(){
        var fromTime = $('#from-time').val();
        var toTime = $('#to-time').val();
        var secondVal = $('#station-second-select').val();
        var firstVal = $('#station-first-select').val();
        findTrains(firstVal, secondVal, fromTime, toTime);

      })

      $(document).ready(function(){
        $('.train-search-time-input').timepicker({
          timeFormat: 'HH:mm',
          startTime: new Date(0,0,0,0,0,0),
          minTime: '00:00',
          maxHour: 23,
          maxMinutes: 59,
          interval: 10
        });
      });

      function findTrains(stationFirstId, stationSecondId, timeFrom, timeTo){
        $('#trains-panel').hide();
        $.ajax({
          url: '<c:url value="/findTrainsJson"/>',
          type: 'GET',
          data: {
            'stationFirstId': stationFirstId,
            'stationSecondId': stationSecondId,
            'timeFrom': timeFrom,
            'timeTo': timeTo
          },
          error: function() {
            //$('#info').html('<p>An error has occurred</p>');
          },
          dataType: 'json',
          success: function(data) {
            var schedules = data;
            if(schedules.length > 0){
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
          }
        });
      }
    </script>

  </tiles:putAttribute>
</tiles:insertDefinition>

