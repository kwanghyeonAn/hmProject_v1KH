package jTest;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import DAO.item_DAO;
import DTO.item_DTO;

public class item_jTest {

	item_DAO i = null;
	
	
	@Before
	public void i() {
		i = item_DAO.i_DAO();
	}
	
	
	@Test
	public void insert() {  //마스크 입고  관리자용 테스트
		item_DTO m = new item_DTO();
		m.setItemName("KF94");
		m.setItemPrice(2000);
		m.setItemCnt(100);
		i.insert(m);
	}
	
	
	@Test
	public void cntplus() { //마스크 수량증가  관리자용 테스트
		item_DTO m = new item_DTO();
		m.setItemCnt(100);
		m.setItemNo(1);
		i.cntplus(m);
	}
	
	@Test
	public void cntminus() { //마스크 수량감소  관리자용 테스트
		item_DTO m = new item_DTO();
		m.setItemCnt(100);
		m.setItemNo(1);
		i.cntminus(m);
	}
	
	
	@Test
	public void delete() { //마스크 삭제  관리자용 테스트
		int aa = 1;
		i.delete(aa);
	}
	
	
	@Test
	public void selectAll() {   // 전체 제품 조회 테스트
		ArrayList<item_DTO> list = i.selectAll();
		for(item_DTO i : list) {
			System.out.println("=======================");
			System.out.println("NO: " + i.getItemNo());
			System.out.println("NAME: " + i.getItemName());
			System.out.println("PRICE: " + i.getItemPrice());
			System.out.println("CNT: " + i.getItemCnt());
			System.out.println("=======================");
		}
	}
}
