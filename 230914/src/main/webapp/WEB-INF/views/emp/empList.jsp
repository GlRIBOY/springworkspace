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
	<button type="button">선택삭제</button>
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
		
		//단건삭제
		$('tr button').on('click', empInfoDel);

		function empInfoDel(event){
			let trTag = event.currentTarget.closest('tr');
			let empId = $(trTag).children().eq(1).text();

			$.ajax('empDelete?employeeId='+empId)
			.done(result => {
				console.log(result);
				let deletedId = result.list[0];
				$('tbody > tr > td:nth-of-type(2)').each(function(idx, tag){
					if($(tag).text() == deletedId){
						$(tag).parent().remove();
					}
				})
			})
			.fail(reject => console.log(reject));
		};
		
		//선택삭제
		$('button:eq(0)').on('click', empListDelete);

		function empListDelete(event){

			//선택한 사원번호를 가지는 배열
			let empIdLIst = getEmpList();

			//ajax
			$.ajax('empDelete', {
				type : 'post',
				contentType : 'application/json',
				data : JSON.stringify(empIdLIst)
			})
			.done(result => {
				if(result){
					location.href='empList';
				}
			})
			.fail(reject => console.log(reject));
		}		

		function getEmpList(){
			let checkTag = $('tbody input[type="checkbox"]:checked');

			let empList = [];
			checkTag.each(function(idx, inTag){
				let empId = $(inTag).parent().next().text();
				empList.push(empId);
			});
			return empList;
		}
	</script>
</body>
</html>