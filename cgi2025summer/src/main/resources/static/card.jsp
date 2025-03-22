<% String destination = request.getParameter("destination"); String flightId =
request.getParameter("flightId"); String company =
request.getParameter("company"); String timestamp =
request.getParameter("timestamp"); %>
<div class="card">
	<div class="card-timestamp"><%= timestamp %></div>
	<div class="card-info">
		<h3><%= destination %></h3>
		<div class="flight-info">
			<p><%= flightId %></p>
			<p><%= company %></p>
		</div>
	</div>
</div>
