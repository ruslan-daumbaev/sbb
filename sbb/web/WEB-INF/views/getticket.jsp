<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tiles:insertDefinition name="defaultTemplate">
  <tiles:putAttribute name="content">

    <div class="row">
      <div class="col-lg-12">
        <h3 class="page-header">Train details</h3>
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
        <form:form id="train-data-form" method="post" action="confirmTicket" modelAttribute="ticketModel">
          <div class="form-group ">
            <label for="trainNumber" class="control-label">Train number:</label>
            <form:label id="trainNumber" class="control-label" path="trainNumber"></form:label>
            <form:hidden path="trainId"/>
          </div>
          <div class="form-group ">
            <label for="stationName" class="control-label">From station:</label>
            <form:label id="stationName" class="control-label" path="stationName"></form:label>

          </div>
          <div class="form-group ">
            <label for="firstName" class="control-label">First name:</label>
            <form:input type="text" path="firstName"
                        class="form-control input-common" id="firstName" name="firstName"/>
          </div>
          <div class="form-group ">
            <label for="lastName" class="control-label">Last name:</label>
            <form:input type="text" path="lastName"
                        class="form-control input-common" id="lastName" name="lastName"/>
          </div>
          <div class="form-group ">
            <label for="birthDate" class="control-label">Birth date:</label>
            <form:input type="text" path="birthDate"
                        class="form-control input-common" id="birthDate" name="birthDate"/>
          </div>
        </form:form>
      </div>
    </div>

  </tiles:putAttribute>
</tiles:insertDefinition>

