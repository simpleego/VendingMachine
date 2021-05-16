package com.simple.vm;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/vm")
public class VM extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final int MILK  = 300;
	private static final int CREAM = 300;
	private static final int BLACK = 200;

	public void init(ServletConfig config) throws ServletException {
		//System.out.println("서블릿 초기화 메서드 호출됨  단 한번 만 호출됨...");	
	}

	public void destroy() {
		//System.out.println("서블릿 소멸시 호출됨 서버가 종료되는 시점이 됩니다....");	
	}

	/*
	 * protected void service(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException {
	 * 
	 * System.out.println("Service 메서드 진입됨..."); String method =
	 * request.getMethod();
	 * 
	 * if(method.equals("POST")) { doPost(request, response); } else {
	 * doGet(request, response); } }
	 */
	 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("Get메서드 진입...");	
		
		HttpSession session = request.getSession();		
		int balance = 0;
		
		// 세션 저장소에 저장된 잔액 값을 읽어옴
		if( session.getAttribute("balance") != null) {
			balance = (int) session.getAttribute("balance");			
		}		
		
		// 돈 입력 처리 함수 호출
		// 잔액을 누적하여 되돌려 줌
		balance = inMoney(request, balance);
		
		// 제품버튼 입력 처리
		String milk = request.getParameter("milk");
		String cream = request.getParameter("cream");
		String black = request.getParameter("black");
		
		if(milk != null) {
			balance -= MILK;
		}
		
		
		// 반환버튼 처리
		String return_ = request.getParameter("return");
		int returnValue = 0;
		if(return_ != null) {
			returnValue = balance;			
			balance = 0;
		}
		
		// 제품버튼 활성화/비활성화 처리 -- 잔액을 기준으로 처리
		String btnMilk="disabled";
		String btnCream="disabled";
		String btnBlack="disabled";
		
		if(balance >= 300) {
			btnMilk = "";
			btnCream = "";
			btnBlack = "";
		}else if (balance >= 200) {
			btnBlack = "";
		}
		
		// 세션 저장소에 저장		
		session.setAttribute("balance", balance);
		session.setAttribute("returnValue", returnValue);
		session.setAttribute("btnMilk", btnMilk);
		session.setAttribute("btnCream", btnCream);
		session.setAttribute("btnBlack", btnBlack);
		
		
		//request.setAttribute("balance", 500);
		response.sendRedirect("index.jsp");		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("post 메서드 진입...");	
		doGet(request, response);
	}

	/*---------------------------------------------------------------
	 * 
	 * 사용자 정의 메서드 
	 * 
	 */
	
	private int inMoney(HttpServletRequest request, int balance) {
		
		// 동전입력 값 읽어오기
		String coin500 = request.getParameter("coin500");
		String coin100 = request.getParameter("coin100");
		String coin50 = request.getParameter("coin50");
		String coin10 = request.getParameter("coin10");
		
		// 직접입력 값 읽어오기 
		String inmoney_ = request.getParameter("inmoney");
		if(inmoney_ != null && !inmoney_.equals("")) {
			int inmoney = Integer.parseInt(inmoney_);
			balance += inmoney;
		}
		
		// 지폐입력 값 읽어오기
		String cash_ = request.getParameter("cash");
		if(cash_ != null) {
			int cash = Integer.parseInt(cash_);
			balance += cash;
		}
		
		// 동전입력 처리
		if(coin500 != null) {
			balance += 500;
		}
		if(coin100 != null) {
			balance += 100;
		}
		if(coin50 != null) {
			balance += 50;
		}
		if(coin10 != null) {
			balance += 10;
		}

		return balance;
	}
}
