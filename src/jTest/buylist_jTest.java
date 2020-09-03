package jTest;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import DAO.buylist_DAO;
import DTO.buylist_DTO;

public class buylist_jTest {

	buylist_DAO b = null;

	@Before
	public void bb() {
		b = buylist_DAO.buy();
	}

	@Test
	public void insert() { // ��ٱ��� �����۴�� �׽�Ʈ
		buylist_DTO bt = new buylist_DTO();

		bt.setNo(3);
		bt.setItemNo(3);
		bt.setCnt(1);
		b.insert(bt);
	}

	@Test
	public void cntplus() { // ��ٱ��� ������ �������� �׽�Ʈ
		buylist_DTO bt = new buylist_DTO();
		bt.setCnt(2);
		bt.setNo(3);
		b.cntplus(bt);
	}

	@Test
	public void cntminus() { // ��ٱ��� ������ �������� �׽�Ʈ
		buylist_DTO bt = new buylist_DTO();
		bt.setCnt(2);
		bt.setNo(3);
		b.cntminus(bt);
	}

	@Test
	public void selectAll() { // �����ڿ� ��ٱ��� ������ ������ȸ �׽�Ʈ
		ArrayList<buylist_DTO> list = b.selectAll();

		for (buylist_DTO i : list) {
			System.out.println("==========================");
			System.out.println("BUYNO : " + i.getBuyNo());
			System.out.println("NO : " + i.getNo());
			System.out.println("ITEMNO : " + i.getItemNo());
			System.out.println("CNT : " + i.getCnt());
			System.out.println("==========================");
		}
	}

	@Test
	public void selectOne() { // ��ٱ��� ������ ��ȸ �׽�Ʈ
		int no = 3;
		ArrayList<buylist_DTO> bt = b.selectOne(no);
		for (int i = 0; i < bt.size(); i++) {
			System.out.println("BUYNO : " +bt.get(i).getBuyNo());
			System.out.println("NO : " + bt.get(i).getNo());
			System.out.println("ITEMNO : " + bt.get(i).getItemNo());
			System.out.println("CNT : " + bt.get(i).getCnt());
		}

	}

	@Test
	public void delete() {
		int no = 3;
		b.delete(no);
	}

}
