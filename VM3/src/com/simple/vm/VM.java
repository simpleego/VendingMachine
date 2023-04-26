package com.simple.vm;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/vm")
public class VM extends HttpServlet {
	private static final long serialVersionUID = 1L;
       final  int MILK_PRICE = 400;
       final  int CREAM_PRICE = 300;
       final  int SUGAR_PRICE = 300;
       final  int BLACK_PRICE = 200;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 저장소를 위한 세션 객체 
		HttpSession session = request.getSession();
		int balance = 0;
		
		// 세션 저장소에 저장된 잔액 값을 읽어온다.
		if(session.getAttribute("balance") != null ) {
			balance = (int) session.getAttribute("balance");
		}
		
		request.setCharacterEncoding("UTF-8");		
		
		String coin_ = request.getParameter("coin");
		String return_  = request.getParameter("return");
		String coffee_  = request.getParameter("coffee");
		String outCoffee="";
		
		int retrun_money=0;
		
		if(coffee_ != null) {
			
			if(coffee_.equals("밀크커피")) {
				outCoffee="밀크커피";
				balance -= MILK_PRICE;
			}else if(coffee_.equals("프림커피")) {
				outCoffee="프림커피";
				balance -= CREAM_PRICE;
			}else if(coffee_.equals("설탕커피")) {
				outCoffee="설탕커피";
				balance -= SUGAR_PRICE;
			}else {
				outCoffee="블랙커피";				
				balance -= BLACK_PRICE;
			}
		}
		
		
		if(return_ != null ) {
			// 잔액 출력
			retrun_money = balance;
			
			// 잔액 비우기
			balance = 0;			
		}
		
		// coin 처리 함수(잔액처리)
		if(coin_ != null) {			
			balance = getBalance(balance, coin_);
		}
		
		// 버튼 활성화/비활성화
		activateButton(balance, session);
		
		session.setAttribute("retrun_money", retrun_money);
		session.setAttribute("outCoffee", outCoffee);
		session.setAttribute("balance", balance);
		
		System.out.println("잔액 : "+balance);
		response.sendRedirect("vm.jsp");
	}

	private void activateButton(int balance, HttpSession session) {
		
		String btnBlack="";// 버튼 비활성/활성 설정
		String btnCream="";// 버튼 비활성/활성 설정
		String btnSugar="";// 버튼 비활성/활성 설정
		String btnMilk="";// 버튼 비활성/활성 설정
		
		if(balance >= BLACK_PRICE) {
			btnBlack="OK";
		}	
		
		if(balance >= CREAM_PRICE) {
			btnCream="OK";
		}
		
		if(balance >= SUGAR_PRICE) {
			btnSugar="OK";
		}		
		if(balance >= MILK_PRICE) {
			btnMilk="OK";
		}		
		
		session.setAttribute("btnBlack", btnBlack);
		session.setAttribute("btnCream", btnCream);
		session.setAttribute("btnSugar", btnSugar);
		session.setAttribute("btnMilk", btnMilk);
	}

	private int getBalance(int balance, String coin_) {
		
		if(coin_.equals("50원") ) {
			balance += 50;
		}else if(coin_.equals("100원")) {
			balance += 100;			
		}else {
			balance += 500;
		}
		return balance;
	}

}
