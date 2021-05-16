<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커피 자판기</title>
<style>

	* {
		border: 1px solid #f00;
		margin: 0px;
		padding: 0px;
	}
	
	form {
		width: 600px;
		margin: 10px auto;		
	}
	
	table {
		width: 600px;
		padding: 5px;
	}
	
	td {
		text-align: center;	
	}
	
	#coins input {
		width: 50px;
	}

</style>
</head>
<body>
	<form action="vm" method="post">
		<table>
			<tr></tr>
			<tr>
				<td>밀크커피(300원)</td>
				<td>프림커피(300원)</td>
				<td>블랙커피(200원)</td>
			</tr>
			<tr>
				<td><input type="submit" value="밀크커피"></td>
				<td><input type="submit" value="프림커피"></td>
				<td><input type="submit" value="블랙커피"></td>
			</tr>
			<tr></tr>
			<tr>
				<td id="coins" colspan="2">
					<input type="submit" value="500">
					<input type="submit" value="100">
					<input type="submit" value="50">
					<input type="submit" value="10">
				</td>	
				<td>
					잔액 : <input style="width: 100px" type="text" value="0">원</input>
				</td>		
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
		
		</table>
	
	</form>

</body>
</html>