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
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 동전 저장을 위한 세션 객체 
		HttpSession session = request.getSession();
		int balance = 0;
		
		// 세션 저장소에 저장된 잔액 값을 읽어온다.
		if(session.getAttribute("balance") != null ) {
			balance = (int) session.getAttribute("balance");
		}
		
//		if(request.getAttribute("balance") != null ) {
//			balance = (int) request.getAttribute("balance");
//		}
		
		
		request.setCharacterEncoding("UTF-8");
		
		String coin_ = request.getParameter("coin");
		
		// coin 처리 함수
		balance = getBalance(balance, coin_);
		
		
		session.setAttribute("balance", balance);
		
		// request객체 내부의 저장소에 데이터를 저장
		// request.setAttribute("balance", balance);
		
		// 데이터와 함께 페이지 요청하기
//		RequestDispatcher dispatcher 
//		   = request.getRequestDispatcher("vm.jsp");
//		dispatcher.forward(request, response);
		
		System.out.println("잔액 : "+balance);
		response.sendRedirect("vm.jsp");
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
