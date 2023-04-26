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
		
		// ���� ������ ���� ���� ��ü 
		HttpSession session = request.getSession();
		int balance = 0;
		
		// ���� ����ҿ� ����� �ܾ� ���� �о�´�.
		if(session.getAttribute("balance") != null ) {
			balance = (int) session.getAttribute("balance");
		}
		
//		if(request.getAttribute("balance") != null ) {
//			balance = (int) request.getAttribute("balance");
//		}
		
		
		request.setCharacterEncoding("UTF-8");
		
		String coin_ = request.getParameter("coin");
		
		String btnBlack="";// ��ư ��Ȱ��/Ȱ�� ����
		String btnCream="";// ��ư ��Ȱ��/Ȱ�� ����
		
		// coin ó�� �Լ�
		balance = getBalance(balance, coin_);
		
		if(balance >= BLACK_PRICE) {
			btnBlack="OK";
		}	
		
		if(balance >= CREAM_PRICE) {
			btnCream="OK";
		}		
		
		session.setAttribute("btnBlack", btnBlack);
		session.setAttribute("btnCream", btnCream);
		session.setAttribute("balance", balance);
		
		// request��ü ������ ����ҿ� �����͸� ����
		// request.setAttribute("balance", balance);
		
		// �����Ϳ� �Բ� ������ ��û�ϱ�
//		RequestDispatcher dispatcher 
//		   = request.getRequestDispatcher("vm.jsp");
//		dispatcher.forward(request, response);
		
		System.out.println("�ܾ� : "+balance);
		response.sendRedirect("vm.jsp");
	}

	private int getBalance(int balance, String coin_) {
		
		if(coin_.equals("50��") ) {
			balance += 50;
		}else if(coin_.equals("100��")) {
			balance += 100;			
		}else {
			balance += 500;
		}
		return balance;
	}

}
