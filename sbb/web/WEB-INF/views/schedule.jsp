<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tiles:insertDefinition name="defaultTemplate">
  <tiles:putAttribute name="modalDialog">
    <div id="dialog-form" title="Ticket details" class="dialog-window">
      <form id="ticket-data-form">
        <div class="form-group ">
          <label for="trainNumber" class="control-label">Train number:</label>
          <label id="trainNumber-lbl" class="control-label"></label>
          <input type="hidden" id="trainId" name="trainId"/>
          <input type="hidden" id="scheduleId" name="scheduleId"/>
          <input type="hidden" id="trainNumber" name="trainNumber"/>
        </div>
        <div class="form-group ">
          <label for="tripDate" class="control-label">Trip date:</label>
          <input id="tripDate" type="text" readonly="readonly" class="form-control input-common" name="tripDate"  required="required"/>
        </div>
        <div class="form-group ">
          <label for="trainTime-lbl" class="control-label">Train time:</label>
          <label id="trainTime-lbl" class="control-label" ></label>
          <input type="hidden" id="trainTime"/>
        </div>
        <div class="form-group ">
          <label for="firstName" class="control-label">First name:</label>
          <input type="text" autofocus=""
                 class="form-control input-common" id="firstName" name="firstName"  required="required"/>
        </div>
        <div class="form-group ">
          <label for="lastName" class="control-label">Last name:</label>
          <input type="text"
                      class="form-control input-common" id="lastName" name="lastName"   required="required"/>
        </div>
        <div class="form-group ">
          <label for="birthDateString" class="control-label">Birth date:</label>
          <input type="text"
                      class="form-control input-common" id="birthDateString" required="required"/>
        </div>
        <label id="confirm-errors" class="control-label validation-error"></label>
      </form>
    </div>

    <div id="dialog-message" title="Ticket reserved" class="dialog-window">
      <p >
        <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
       <span id="messageText"></span>
      </p>
    </div>
  </tiles:putAttribute>
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



      dialog = $( "#dialog-form" ).dialog({
        autoOpen: false,
        height: 580,
        width: 440,
        modal: true,
        resizable: false,
        buttons: {
          "Confirm": confirmTicket,
          Cancel: function() {
            dialog.dialog( "close" );
          }
        }
      });

      confirmDialog = $( "#dialog-message" ).dialog({
        modal: true,
        autoOpen: false,
        resizable: false,
        buttons: {
          Ok: function() {
            $( this ).dialog( "close" );
          }
        }
      });

      function confirmTicket() {
        var valid = false;
        if($("#ticket-data-form" ).valid()){
        var trainId = $('#trainId').val();
        var scheduleId = $('#scheduleId').val();
        var firstName = $('#firstName').val();
        var lastName = $('#lastName').val();
        var birthDateString = $('#birthDateString').val();
        var tripDate = $('#tripDate').val();

        $.ajax({
          url: '${pageContext.request.contextPath}/confirmTicket',
          type: 'POST',
          global: false,
          data: {
            "trainId": trainId,
            "scheduleId": scheduleId,
            "firstName": firstName,
            "lastName": lastName,
            "birthDateString": birthDateString,
            "tripDate": tripDate
          },
          dataType: 'json',
          success: function(data) {
            if(data.isOk){
              dialog.dialog("close");
              showDialog('Your ticket has been reserved successfully. Thank you for using SBB service portal!', 'Ticket reserved');
              valid = true;
            }
            else{
              showDialog(data.errorMessage, 'Confirmation error');
              $('#comfirm-errors').empty().append(data.errorMessage);
            }
          },
          error: function(e){
            $('#comfirm-errors').empty().append(e.message);
          }
        });
        }
        return valid;
      }


      function showDialog(messageText, title){
        $('#messageText').empty().append(messageText);
        $('#dialog-message').attr('title', title);
        confirmDialog.dialog("open");
      }

      $("#wait").css("display", "none");

      $(function() {
        $( "#birthDateString" ).datepicker({
          dateFormat: 'dd/mm/yy',
          maxDate: new Date()
        });
      });

      $(function() {
        $( "#tripDate" ).datepicker({
          dateFormat: 'dd/mm/yy',
          minDate: new Date()
        });
      });

      $(document).ajaxStart(function(){
        $("#wait").css("display", "block");
        $('#stations-select').prop('disabled', true);
      });

      $(document).ajaxComplete(function(){
        $("#wait").css("display", "none");
        $('#stations-select').prop('disabled', false);
      });

      $('#trains-panel').hide();
      $(document).ready(function(){
        $('#stations-select').change(stationChanged);

        $('#ticket-data-form').validate({
          rules: {
            firstName: {
              required: true
            },
            lastName: {
              required: true
            },
            birthDateString: {
              required: true
            }
          },
          submitHandler: function(form) {
            return false;
          }
        });

      });

      function stationChanged() {
        $('#trains-panel').hide();
        if ($(this).val() === '0') {
          $('#trains-panel').hide();
        }
        else{
          var stationId = $(this).val();

          $.ajax({
            url: '${pageContext.request.contextPath}/scheduleJson',
            type: 'GET',
            data: {'stationId': stationId },
            dataType: 'json',
            success: function(data) {
              var schedules = data.schedules;
              var content = '';
              for (var i = 0; i< schedules.length; i++) {
                content += '<tr>';
                content += '<td>'+schedules[i].trainNumber+'</td>';
                content += '<td>'+schedules[i].trainTime+'</td>';
                content += '<td><button class="buy-ticket-btn btn btn-default" id="buyticket-' + schedules[i].id+'">Buy ticket</button></td>';
                content += '</tr>';
              }
              $('#stations-table-body').empty().append(content);
              $('#trains-panel').removeClass('hidden');
              $('#trains-panel').show();
              $( ".buy-ticket-btn" ).click(function() {
                var scheduleId = this.id.split('-')[1];
                loadTicketData(scheduleId);
              });
            }
          });
        }
      }

      function loadTicketData(scheduleId){
        $.ajax({
          url: '${pageContext.request.contextPath}/getTicketData/'+scheduleId,
          type: 'GET',
          global: false,
          dataType: 'json',
          success: function(data) {
            $('#trainId').val(data.trainId);
            $('#scheduleId').val(data.scheduleId);
            $('#trainNumber-lbl').html(data.trainNumber);
            $('#trainTime-lbl').html(data.trainTime);
            $('#tripDate').val(data.tripDate);
            dialog.dialog("open");
          }
        });
      }

    </script>
  </tiles:putAttribute>

</tiles:insertDefinition>

