<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>사원 정보 조회</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>

<body>
	<div>
		<form action="getEmp" method="get">
			<label>사원번호<input type="number" name="employeeId"></label>
			<button type="submit">검색</button>
		</form>
	</div>
	<div>
		<table>
			<tr>
				<th>사원번호</th>
				<td>${empInfo.employeeId}</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>${empInfo.firstName}</td>
			</tr>
			<tr>
				<th>업무</th>
				<td>${empInfo.jobId}</td>
			</tr>
		</table>
	</div>
	<hr>
	<form>
		<table>
			<tr>
				<th>성</th>
				<td><input type="text" name="lastName"></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" name="email"></td>
			</tr>
			<tr>
				<th>입사일</th>
				<td><input type="text" name="hireDate"></td>
			</tr>
			<tr>
				<th>업무</th>
				<td><input type="text" name="jobId"></td>
			</tr>
		</table>
		<button type="submit">등록</button>
	</form>
	<script>
		$('form:eq(1)').on('submit', function(e){
			e.preventDefault();
			//js방식
			//let formData = new FormData(document.getElementsByTagName('form')[1]);
			
			let formData = $('form:eq(1)').serializeArray(); // or formData=$('table input');
			/* 시리얼라이즈 와 시리얼라이즈어레이는 폼태그에만 동작함 */
			// serialize() : QueryString - key=valuew&key&vlaue&..
			// serializeArray() : [{name:'lastName', value:'King'}, ]
			//변환 ->{lastName:'King', }
			let formObj = {};
			$.each(formData, function(idx, obj){
				//하나의 객체를 하나의 필드로 전환
				formObj[obj.name] = obj.value;
			});
			console.log(formData, formObj);
			
			$.ajax('empInfoInsert', {
				type:'post',
				contentType:'application/json',//통신방법이 정해져있는거라 고정임.
				data: JSON.stringify(formObj)
			})
			.done(data=>{
				console.log(data);
			})
			.fail(reject => console.log(reject));
			
			//return false;
			
		});
		/*
			Event Object
			-메소드
			1) preventDefault() : 해당 이벤트에 기본으로 설정된 동작을 막음.form submit, <a>의 클릭이벤트
			2) stopPropagation() : 이벤트 버블링을 막음. 나 다음의 이벤트를 발생시키는걸 막는것.
									select이라도 클릭하면 클릭이벤트가 발생함
									캡쳐링 <=> 버블링(이벤트가 발생한 부분부터 부모로 쭉 타고 올라간다)
			
			
			-필드
			1) target : 이벤트가 발생한 태그.실제 사용자가 선택해서 발생한 이벤트(고정값)
			2) currentTarget : 즉, this. 현재 이벤트 핸들러가 동작한 태그
								이벤트가 발생한 곳으로부터 버블링으로 실행된 이벤트(변동값)
									
			form은 자신 안에 데이터를 다음페이지로 이동하며 통합시킨다.
			
									
		*/
		
	</script>
</body>

</html>