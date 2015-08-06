<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<tiles:insertDefinition name="adminTemplate">
  <tiles:putAttribute name="content">
    <div class="row">
      <div class="col-lg-12">
        <h3 class="page-header">Control panel</h3>
      </div>
      <!-- /.col-lg-12 -->
    </div>

    <table class="table">
      <thead>
      <tr>
        <th>Train #</th>
        <th>Places</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${trains}" var="train">
        <tr>
          <td>${train.trainNumber}</td>
          <td>${train.placesAmount}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </tiles:putAttribute>
</tiles:insertDefinition>

