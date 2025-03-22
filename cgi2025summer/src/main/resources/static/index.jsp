<%@ page language="java" contentType="text/html"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<meta
			name="viewport"
			content="width=device-width, initial-scale=1.0"
		/>
		<title>Index page</title>
		<link
			rel="stylesheet"
			href="./style.css"
		/>
	</head>
	<body>
		<div class="outer-container">
			<h1>Flight info</h1>
			<div class="search-container">
				<input
					type="text"
					name="filter"
					id="filter"
				/>
				<button type="submit">Submit</button>
			</div>
			<hr />
			<div class="flights-container">
				<div class="flights-container-date">21/03/2025</div>
				<% for(int i = 0; i < 5; i++) { %>
				<jsp:include page="card.jsp">
					<jsp:param
						name="destination"
						value="Tallinn"
					/>
					<jsp:param
						name="flightId"
						value="TL-001"
					/>
					<jsp:param
						name="company"
						value="Tallinn"
					/>
					<jsp:param
						name="timestamp"
						value="<%= LocalDate.now() %>"
					/>
				</jsp:include>
				<% } %>
			</div>
		</div>
	</body>
</html>
