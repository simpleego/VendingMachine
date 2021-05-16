package com.simple.vm;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.simple.vm.coffee.Coffee;

@WebServlet("/vm")
public class VM extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final int MILK  = 300;
	private static final int CREAM = 300;
	private static final int BLACK = 200;
	private static final int SUGAR = 200;
	private static final String OUTMESSAGE = "Out.png";
	
	private Coffee coffee;		// 커피 재료를 관리하는 객체
	private Money  money;		// 자판기 금액관리하는 객체
	private Sales  sales;		// 판매를 관리하는 객체
	

	public void init(ServletConfig config) throws ServletException {
		//System.out.println("서블릿 초기화 메서드 호출됨  단 한번 만 호출됨...");	
		coffee = new Coffee(1000, 1000, 1000, 1000, 100);
		money = new Money(20, 20, 50, 100, 0, 0, 0);
		sales = new Sales();
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
		request.setCharacterEncoding("UTF-8");

		int balance = 0;		
		
		// 세션 저장소에 저장된 잔액 값을 읽어옴
		if( session.getAttribute("balance") != null) {
			balance = (int) session.getAttribute("balance");			
		}
		
		// 금액입력 처리 함수
		balance = inMoney(request, balance);		
		
		// 커피 판매 함수		
		balance = coffeeOut(request, balance, session);		
		
		// 반환버튼 처리 함수
		balance = returnMoney(request, balance, session);
		
		// 버튼 상태 처리 함수
		buttonStatus(balance, session);
		
		// 잔액 및 반환금액 저장소에 설정		
		session.setAttribute("balance", balance);
		
		response.sendRedirect("index.jsp");			
	}

	// 버튼 상태 처리 함수
	private void buttonStatus(int balance, HttpSession session) {
		
		// 제품버튼 활성화/비활성화 처리 -- 잔액을 기준으로 처리
		String btnMilk="";
		String btnCream="";
		String btnBlack="";
		String btnSugar="";
		
		if(balance >= 300) {
			btnMilk = "enable";
			btnCream = "enable";
			btnBlack = "enable";
			btnSugar = "enable";
		}else if (balance >= 200) {
			btnBlack = "enable";
			btnSugar = "enable";
		}
		
		// 커피 버튼 저장소에 설정
		session.setAttribute("btnMilk", btnMilk);
		session.setAttribute("btnCream", btnCream);
		session.setAttribute("btnSugar", btnSugar);
		session.setAttribute("btnBlack", btnBlack);		
	}

	//
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
	// 돈 입력 처리 함수
	private int inMoney(HttpServletRequest request, int balance) {
		
		// 동전입력 값 읽어오기
		String coin500 = request.getParameter("coin500");
		String coin100 = request.getParameter("coin100");
		String coin50 = request.getParameter("coin50");
		String coin10 = request.getParameter("coin10");		
		
		// 동전입력 처리
		if(coin500 != null) {
			balance += 500;
			money.setCoin500(money.getCoin500()+1);
		}
		if(coin100 != null) {
			balance += 100;
			money.setCoin100(money.getCoin100()+1);
		}
		if(coin50 != null) {
			balance += 50;
			money.setCoin50(money.getCoin50()+1);
		}
		if(coin10 != null) {
			balance += 10;
			money.setCoin10(money.getCoin10()+1);
		}
		
		// 지폐입력 값 읽어오기
		String cash_ = request.getParameter("cash");
		if(cash_ != null) {
			int cash = Integer.parseInt(cash_);
			if(cash == 10000) {
				money.setCash10000(money.getCash10000()+1);
			}else if(cash == 5000) {
				money.setCash5000(money.getCash5000()+1);
			}else {
				money.setCash1000(money.getCash1000()+1);
			}
			balance += cash;
		}
		
		// 직접입력 값 읽어오기 
		/*
		 * String inmoney_ = request.getParameter("inmoney"); if(inmoney_ != null &&
		 * !inmoney_.equals("")) { int inmoney = Integer.parseInt(inmoney_); balance +=
		 * inmoney; }
		 */	

		System.out.println(money);
		
		return balance;
	}
	
	// 커피 출력 함수
	private int coffeeOut(HttpServletRequest request, int balance, HttpSession session) {
		
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy년 MM월dd일 HH시mm분ss초");
		
		Date date = new Date();
		
		String date1 = format1.format(date);
		String date2 = format2.format(date);
		
		System.out.println("날짜 시간 : "+ date1);
		System.out.println("날짜 시간 : "+ date2);
		
		// 제품버튼 입력 처리
		String milk  = request.getParameter("milk");
		String cream = request.getParameter("cream");
		String black = request.getParameter("black");
		String sugar = request.getParameter("sugar");
		
		String output="";
		String coffeeMessage="";		
		// 커피 재료 체크
		boolean milkOk  = true;
		boolean creamOk = true;
		boolean blackOk = true;
		boolean sugarOk = true;
		
		milkOk  = checkMilkCoffee();
		creamOk = checkCreamCoffee();
		blackOk = checkBlackCoffee();
		sugarOk = CheckSugarCoffee();
		
		// 밀크커피 재료가 부족한 상태
		if(!milkOk) {
			coffeeMessage = "밀크커피 재료가 부족합니다!!";
		}
		// 크림커피 재료가 부족한 상태
		if(!creamOk) {
			coffeeMessage = "프림커피 재료가 부족합니다!!";
		}
		// 블랙커피 재료가 부족한 상태
		if(!blackOk) {
			coffeeMessage = "블랙커피 재료가 부족합니다!!";
		}
		
		// 밀크커피 :  물(50), 커피(100), 크림(100), 설탕(200), 컵(1)			
		if(milkOk && milk != null) {
			balance -= MILK;
			output = "milk"+OUTMESSAGE;
			coffee.setWater(coffee.getWater()-50);
			coffee.setCoffee(coffee.getCoffee()-100);
			coffee.setCream(coffee.getCream()-100);
			coffee.setSugar(coffee.getSugar()-200);
			coffee.setCup(coffee.getCup()-1);
			sales.setpCode("M");
			sales.setDate(date);
			sales.setPrice(MILK);
		}
		
		// 프림커피 :  물(50), 커피(100), 크림(100), 컵(1)			
		if(creamOk && cream != null) {
			balance -= CREAM;
			output = "cream"+OUTMESSAGE;
			coffee.setWater(coffee.getWater()-50);
			coffee.setCoffee(coffee.getCoffee()-100);
			coffee.setCream(coffee.getCream()-100);
			coffee.setCup(coffee.getCup()-1);
			sales.setpCode("C");
			sales.setDate(date);
			sales.setPrice(CREAM);
		}
		
		//설탕커피 :  물(50), 커피(100), 설탕(200), 컵(1)	
		if(sugar != null&& sugarOk ) {
			balance -= SUGAR;
			output = "sugar"+OUTMESSAGE;
			coffee.setWater(coffee.getWater()-50);
			coffee.setCoffee(coffee.getCoffee()-100);
			coffee.setSugar(coffee.getSugar()-200);
			coffee.setCup(coffee.getCup()-1);
			sales.setpCode("S");
			sales.setDate(date);
			sales.setPrice(SUGAR);
		}
		
		// 프림커피 :  물(50), 커피(100), 컵(1)
		if(blackOk && black != null) {
			balance -= BLACK;
			output = "black"+OUTMESSAGE;
			// 재료 소비를 처리 :  물(50), 커피(100), 컵(1)			
			coffee.setWater(coffee.getWater()-50);
			coffee.setCoffee(coffee.getCoffee()-100);
			coffee.setCup(coffee.getCup()-1);
			sales.setpCode("B");
			sales.setDate(date);
			sales.setPrice(BLACK);
		}
		
		// 커피 출력문자열 저장소에 설정
		System.out.println("--->"+output);
		session.setAttribute("output", output);		
		session.setAttribute("coffeeMessage", coffeeMessage);	
		
		System.out.println(coffee);
		System.out.println(sales);
		return balance;
	}
	
	// 밀크커피 판매가능 여부 체크
	private boolean checkMilkCoffee() {
		
		if(coffee.getCream() < 100 || 
			coffee.getCoffee() < 100 ||
			coffee.getSugar() < 200 ||
			coffee.getWater() < 50 ||
			coffee.getCup() < 1 ) {
			return false;			
		}		
		return true;		
	}
	
	// 밀크커피 판매가능 여부 체크
	private boolean checkCreamCoffee() {
		
		if(coffee.getCream() < 100 || 
				coffee.getCoffee() < 100 ||
				coffee.getWater() < 50 ||
				coffee.getCup() < 1 ) {
			return false;			
		}		
		return true;		
	}
	
	// 설탕커피 판매가능 여부 체크
	private boolean CheckSugarCoffee() {
		
		if( coffee.getSugar() <200 || coffee.getWater() <50 || coffee.getCoffee() <100||coffee.getCup() <1 ) {
			return false;
		}else {
			return true;
		}
	}
	
	// 밀크커피 판매가능 여부 체크
	private boolean checkBlackCoffee() {
		
		if(coffee.getCoffee() < 100 ||				
			coffee.getWater() < 50 ||
			coffee.getCup() < 1 ) {
			return false;			
		}		
		return true;		
	}

	// 잔돈 반환 함수
	private int returnMoney(HttpServletRequest request, int balance, HttpSession session) {
		// 반환할 동전 개수 처리
		int return500Coin = 0;
		int return100Coin = 0;
		int return50Coin = 0;
		int return10Coin = 0;
		int returnValue = 0;
		String return_ = request.getParameter("return");
		
		if(return_ != null) {
			returnValue = balance;			
			balance = 0;
		}
		
		if(returnValue > 0) {
			return500Coin = returnValue / 500;
			returnValue %= 500;
			return100Coin = returnValue / 100;
			returnValue %= 100;
			return50Coin = returnValue / 50;
			returnValue %= 50;
			return10Coin = returnValue / 10;
			returnValue %= 10;
		}

		// 반환되는 동전 저장소에 설정
		session.setAttribute("return500Coin", return500Coin);
		session.setAttribute("return100Coin", return100Coin);
		session.setAttribute("return50Coin", return50Coin);
		session.setAttribute("return10Coin", return10Coin);
		session.setAttribute("returnValue", returnValue);
		return balance;
	}
	
	
}
