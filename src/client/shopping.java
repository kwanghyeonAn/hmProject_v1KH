package client;

import java.util.Scanner;

import Interface.user_INFIpl;

public class shopping {
		private Scanner in = new Scanner(System.in);
		private user_INFIpl u_inf = user_INFIpl.u();
	
	public shopping() {
		int k = 0;
		log();
		
		while(true) {
			menu2();
			System.out.println("메뉴를 선택해주세요.");
			k = in.nextInt();
			in.nextLine();
			
			if (k == 1) {
				u_inf.pwdupdate();
			} else if (k == 2) {
				u_inf.delete();
			} else if (k == 3) {
				u_inf.infoselect();
			} else if (k == 4) {
				u_inf.buylistselect();
			} else if (k == 5) {
				u_inf.buyitem();
			} else if (k == 6) {
				u_inf.buyitemdelete();
			} else if (k == 7) {
				u_inf.buyitempudate();
			} else if (k == 8) {
				u_inf.itemlist();
			} else if (k == 9) {
				break;
			}
		}
	}
	
	public void log() {
		while(true) {
			int k = 0;
			int f = 0;
			menu1();
			System.out.println("메뉴를 선택해주세요.");
			k = in.nextInt();
			in.nextLine();
			if (k == 1) {
				f = u_inf.login();
				if (f == 1) {
					break;
				}
			} else if (k == 2) {
				u_inf.membership();
				break;
			}
		}
	}
	
	public void menu1() {
		System.out.println("==============");
		System.out.println("1.로그인");
		System.out.println("2.회원가입");
		System.out.println("==============");
	}
	
	public void menu2() {
		System.out.println("==============");
		System.out.println("1.비밀번호 변경");
		System.out.println("2.계정삭제");
		System.out.println("3.계정정보조회");
		System.out.println("4.장바구니 확인");
		System.out.println("5.쇼핑하기");
		System.out.println("6.장바구니 삭제");
		System.out.println("7.장바구니 수량변경");
		System.out.println("8.아이쇼핑하기");
		System.out.println("9. 종료");
		System.out.println("==============");
	}
	
}
