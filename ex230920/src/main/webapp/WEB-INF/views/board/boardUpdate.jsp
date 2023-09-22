<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
</head>
<body>
	<form>
		<table>
			<tr>
				<th>번호</th>
				<td><input type="text" name="bno" value="${boardInfo.bno}"
					readonly></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" value="${boardInfo.title}"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer"
					value="${boardInfo.writer}"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="contents">${boardInfo.contents}</textarea></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td><input type="text" name="image" value="${boardInfo.image}"></td>
			</tr>
			<tr>
				<th>수정날짜</th>
				<td><input type="date" name="updatedate"
					value="<fmt:formatDate value="${boardInfo.updatedate}" pattern="yyyy-MM-dd"/>"></td>
			</tr>
		</table>
		<button type="button" id="saveBtn">저장</button>
		<button type="button"
			onclick="location.href='boardInfo?bno=${boardInfo.bno}'">취소</button>
	</form>
	<script>
		$('#saveBtn').on('click', updateAjax);
		
		function updateAjax(event){
			let updateDate = getBoardInfo();
			
			$.ajax('boardUpdate', {
				type : 'post',
				contentType : 'application/json',
				data : JSON.stringify(updateDate)
			})
			.done(result =>{
				if(result['result']){
					alert('정상저그로 수정됬슴둥.');
				}else{
					alert('데이터 제대로 쓰소.');
				}
			})
			.fail(reject => console.log(reject));
		}
		
		function getBoardInfo(){
			let formData = $('form').serializeArray();//폼안에 입력태그에 대해서 배열타입으로 가져온다
			
			let formObj = {};
			$.each(formData, function(idx, obj){
				//각 입력태그를 하나의 필드로 변환한다.
				formObj[obj.name] = obj.value;
			});
			return formObj;
		}
		
	</script>
</body>
</html>