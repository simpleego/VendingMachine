<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/* int balance = 0;

	String balance_ = request.getParameter("balance");
	
	if(balance_ != null && !balance_.equals("")){
		balance = Integer.parseInt(balance_);
	} */	

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>믹스커피 자판기</title>
<style>
	* {
		border: 1px solid #f00;
		margin: 0px;
		padding: 0px;	
	}
	
	#content {
		width: 800px;
		margin: 10px auto;
	}
	
	.menu {
		padding: 30px;
	}
	
	input {
		padding: 10px;
	}
	
	.btn {
		text-align: center;
	}
	
	#out {
		height: 300px;
		text-align: center;	
	}
	
	#return {
		padding-top: 50px;
		text-align: right;	
		padding-right: 20px;
	}
</style>
</head>
<body>
	<div id="content">
		<h1>믹스 커피 자판기</h1>
		<form action="vm" method="post">
			<table>
				<tr>
					<td class="menu">밀크커피(400원)</td>
					<td class="menu">프림커피(300원)</td>
					<td class="menu">설탕커피(300원)</td>
					<td class="menu">블랙커피(200원)</td>
				</tr>
				<tr>
					<td class="btn"><input type="submit" ${empty btnMilk?'disabled':'' } name="coffee" value="밀크커피" /> </td>
					<td class="btn"><input type="submit" ${empty btnCream?'disabled':''} name="coffee" value="프림커피" /> </td>
					<td class="btn"><input type="submit" ${empty btnSugar?'disabled':'' } name="coffee" value="설탕커피" /> </td>
					<td class="btn"><input type="submit" ${empty btnBlack?'disabled':'' } name="coffee" value="블랙커피" /> </td>
				</tr>
				
				<tr>
					<td class="btn">잔액 : <input type="text" value="${empty balance?'0':balance}" /> </td>
					<td class="btn">지폐 : <input type="text" value="0" /> </td>
					<td class="btn"><input type="submit" name="return" value="반환" /> </td>
					<td class="btn">
						<input type="submit" name="coin" value="50원" /> 
						<input type="submit" name="coin" value="100원" /> 
						<input type="submit" name="coin" value="500원" /> 
					</td>
				</tr>
				<tr>
					<td colspan="4" id="out">
						제품 출구 : ${empty outCoffee?' ':outCoffee }
						<textarea cols=10 rows=4>
						
						</textarea>
					</td>					
				</tr>
				<tr>
					<td colspan="4" id="return">
						반환금액 : <input type="text" value="${empty retrun_money?'0':retrun_money } ">					
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>