<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	//String btnStatus="disabled";
	//pageContext.setAttribute("btnStatus", "disabled");

%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커피 자판기</title>
<style>

	* {
		border: 1px solid #aaa;
		margin: 0px;
		padding: 0px;
	}
	
	body {
		border: none;
	}
	
	form {
		width: 650px;
		margin: 10px auto;		
	}
	
	table {
		width: 650px;
		padding: 5px;
		border-collapse: collapse;
	}
	
	td {
		text-align: center;	
		padding: 10px;
	}
	
	#coins {		
		text-align: left;
	}
	
	#coins input {
		width: 80px;
	}
	
	#return  input {
		width: 50px;
		background: #ccc;
		text-align: right;
		padding-right: 5px;
	}
	
	#balance {
		width: 100px;
		background: #600;
		text-align: right;
		padding-right: 10px;	
		color: white;
		font-weight: bold;	
		height: 30px;
	}
	
	.line {
		background: #ddd;
	}

</style>
</head>
<body>
	<form action="vm" method="post">
		<table>
			<tr>
				<td class="line" colspan="3"></td>
			</tr>
			<tr>
				<td colspan="3"><h1>커피 & 음료 자동 판매기</h1></td>
			</tr>
			<tr>
				<td>밀크커피(300원)</td>
				<td>프림커피(300원)</td>
				<td>블랙커피(200원)</td>
			</tr>
			<tr>
				<td><input type="submit" ${btnMilk }  name="milk" value="밀크커피"></td>
				<td><input type="submit" ${btnCream } name="cream" value="프림커피"></td>
				<td><input type="submit" ${btnBlack } name="black" value="블랙커피"></td>
			</tr>
			<tr>
				<td class="line" colspan="3"></td>
			</tr>
			<tr>
				<td id="coins" colspan="2">
					
					동전투입 : <input type="submit" name="coin500" value="500">
					<input type="submit" name="coin100" value="100">
					<input type="submit" name="coin50" value="50">
					<input type="submit" name="coin10" value="10"></br>
					
					직접입력 : <input type="number" min="0" max="1000" step="10" 
					name="inmoney">원</input>
					<input type="submit" value="직접입력" /></br>
					
					지폐 :
					<select name="cash">
						<option value="0">지폐선택</option>
						<option value="10000">10,000</option>
						<option value="5000">5,000</option>
						<option value="1000">1,000</option>
					</select>
					<input type="submit" value="지폐입력" />					
				</td>	
				<td>
					잔액 : <input id="balance"  type="text" value="${balance}">원</input>
				</td>		
			</tr>
			<tr>
				<td class="line" colspan="3"></td>
			</tr>
			<tr>
				<td>
					<input type="submit" name="return" value="동전반환"/>
				</td>
				<td id="return" colspan="2">
					반환된 금액 : <input type="text" readonly value="${returnValue}"/></br>
					500원 : <input type="text" readonly value="1"/>개
					100원 : <input type="text" readonly value="1"/>개</br>
					50원 : <input type="text" readonly value="1"/>개
					10원 : <input type="text" readonly value="1"/>개</br>
				</td>
			</tr>
			<tr>
				<td class="line" colspan="3"></td>
			</tr>
			<tr>
				<td colspan="3">제품출구</br>
					<textarea rows="5">
						제품은 여기에서 출력됩니다.
						제품은 여기에서 출력됩니다.
						제품은 여기에서 출력됩니다.
						제품은 여기에서 출력됩니다.
						제품은 여기에서 출력됩니다.
						제품은 여기에서 출력됩니다.
						제품은 여기에서 출력됩니다.					
					</textarea>
				</td>
			</tr>
			<tr>
				<td class="line" colspan="3"></td>
			</tr>		
		</table>	
	</form>

</body>
</html>