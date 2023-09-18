<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix= "fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체 사원 조회</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

</head>
<body>
<p>${result }</p>
	<table>
		<thead>
			<tr>
				<th>Check</th>
				<th>employee_id</th>
				<th>first_name</th>
				<th>last_name</th>
				<th>email</th>
				<th>hire_date</th>
				<th>job_id</th>
				<th>salary</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${empList}" var="emp">
				<tr>
					<%-- <tr onclick="location.href='empInfo?employee_id=${emp.employeeId}'"> --%>
					<td><input type="checkbox"></td>
					<td>${emp.employeeId}</td>
					<td>${emp.firstName}</td>
					<td>${emp.lastName}</td>
					<td>${emp.email}</td>
					<td><fmt:formatDate value="${emp.hireDate}" pattern="yyyy-MM-dd"/></td>
					<td>${emp.jobId}</td>
					<td>${emp.salary}</td>
					<td><button type="button">Del</button></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<script>
		let message = `${result}`;
		
		if(message != '') alert(message);
	
		$('tbody > tr').on('click', function(e) {
			if (e.target.tagName != 'TD')
				return;//함수안에서 리턴은 종료를 뜻함
			//let empId = e.currentTarget.firstElementChild.nextElementSibling.textContent;
			let empId = $(e.currentTarget).find('td:nth-of-type(2)').text();
			//=let empId = $(e.currentTarget).find('td:eq(1)').text();
			location.href = 'empInfo?employeeId=' + empId;
		});
	</script>
</body>
</html>