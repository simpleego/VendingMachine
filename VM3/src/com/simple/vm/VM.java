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
		
		// ����Ҹ� ���� ���� ��ü 
		HttpSession session = request.getSession();
		int balance = 0;
		
		// ���� ����ҿ� ����� �ܾ� ���� �о�´�.
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
			
			if(coffee_.equals("��ũĿ��")) {
				outCoffee="��ũĿ��";
				balance -= MILK_PRICE;
			}else if(coffee_.equals("����Ŀ��")) {
				outCoffee="����Ŀ��";
				balance -= CREAM_PRICE;
			}else if(coffee_.equals("����Ŀ��")) {
				outCoffee="����Ŀ��";
				balance -= SUGAR_PRICE;
			}else {
				outCoffee="��Ŀ��";				
				balance -= BLACK_PRICE;
			}
		}
		
		
		if(return_ != null ) {
			// �ܾ� ���
			retrun_money = balance;
			
			// �ܾ� ����
			balance = 0;			
		}
		
		// coin ó�� �Լ�(�ܾ�ó��)
		if(coin_ != null) {			
			balance = getBalance(balance, coin_);
		}
		
		// ��ư Ȱ��ȭ/��Ȱ��ȭ
		activateButton(balance, session);
		
		session.setAttribute("retrun_money", retrun_money);
		session.setAttribute("outCoffee", outCoffee);
		session.setAttribute("balance", balance);
		
		System.out.println("�ܾ� : "+balance);
		response.sendRedirect("vm.jsp");
	}

	private void activateButton(int balance, HttpSession session) {
		
		String btnBlack="";// ��ư ��Ȱ��/Ȱ�� ����
		String btnCream="";// ��ư ��Ȱ��/Ȱ�� ����
		String btnSugar="";// ��ư ��Ȱ��/Ȱ�� ����
		String btnMilk="";// ��ư ��Ȱ��/Ȱ�� ����
		
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
