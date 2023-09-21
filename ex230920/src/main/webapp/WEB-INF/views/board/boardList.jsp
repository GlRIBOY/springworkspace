<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix= "fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>번호</th><!-- bno -->
				<th>제목</th><!-- titile -->
				<th>작성자</th><!-- writer -->
				<th>작성일</th><!-- regdate : yyyy년MM월dd일 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${boardList }" var="b">
				<tr>
					<td>${b.bno}</td>
					<td>${b.title}</td>
					<td>${b.writer}</td>
					<td><fmt:formatDate value="${b.regdate}" pattern="yyyy년 MM월 dd일"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
<script>
 	$('tbody > tr').on('click', function(e){
		if(e.target.tagName != 'TD') return;
		
		let boardNo = $(e.currentTarget).find('td:nth-of-type(1)').text();
		location.href = 'boardInfo?bno=' + boardNo;
	}) 
	
/* 	document.querySelectorAll('tbody > tr')
			.forEach(function(tag){
				tag.addEventListener('click', function(e){
					let bno = e.currentTarget.firstElementChild.textContent;
					location.href = 'boardInfo?bno='+bno;
				})
			}) */
</script>
</html>