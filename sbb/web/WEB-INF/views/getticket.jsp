<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tiles:insertDefinition name="defaultTemplate">
  <tiles:putAttribute name="content">

    <div class="row">
      <div class="col-lg-12">
        <h3 class="page-header">Ticket</h3>
      </div>
      <!-- /.col-lg-12 -->
    </div>

    <div>
      <p>
        <button id="buyTicketButton" type="button" class="btn btn-outline btn-primary">Confirm</button>
        <a href="<c:url value="/trains"/>" id="cancelButton" type="button" class="btn btn-outline btn-default">Cancel</a>
      </p>
    </div>

    <div >
      <div >
        <form:form id="ticket-data-form" method="post" servletRelativeAction="/confirmTicket" modelAttribute="ticketModel">
          <div class="form-group ">
            <label for="trainNumber" class="control-label">Train number:</label>
            <label id="trainNumber" class="control-label">${ticketModel.trainNumber}</label>
            <form:hidden path="trainId"/>
            <form:hidden path="scheduleId"/>
            <form:hidden path="trainNumber"/>
          </div>

          <div class="form-group ">
            <label for="stationName" class="control-label">From station:</label>
            <label id="stationName" class="control-label">${ticketModel.stationName}</label>
            <form:hidden path="stationName"/>

          </div>
          <div class="form-group ">
            <label for="stationName" class="control-label">Trip date:</label>
            <form:input id="tripDate" type="text" class="form-control input-common" path="tripDate" ></form:input>
            <form:hidden path="tripDate"/>
          </div>
          <div class="form-group ">
            <label for="stationName" class="control-label">Train time:</label>
            <label id="trainTime" class="control-label" >${ticketModel.trainTime}</label>
          </div>
          <div class="form-group ">
            <label for="firstName" class="control-label">First name:</label>
            <form:input type="text" path="firstName"
                        class="form-control input-common" id="firstName" name="firstName"  />
          </div>
          <div class="form-group ">
            <label for="lastName" class="control-label">Last name:</label>
            <form:input type="text" path="lastName"
                        class="form-control input-common" id="lastName" name="lastName"/>
          </div>
          <div class="form-group ">
            <label for="birthDate" class="control-label">Birth date:</label>
            <form:input type="text" path="birthDateString"
                        class="form-control input-common" id="birthDate"/>
          </div>
        </form:form>
      </div>
    </div>
    <script>
      $( "#buyTicketButton" ).click(function() {
        $( "#ticket-data-form" ).submit();
      })
      $(function() {
        $( "#birthDate" ).datepicker({ dateFormat: 'dd/mm/yy' });
      });

      $(function() {
        $( "#tripDate" ).datepicker({ dateFormat: 'dd/mm/yy' });
      });
    </script>

  </tiles:putAttribute>
</tiles:insertDefinition>

