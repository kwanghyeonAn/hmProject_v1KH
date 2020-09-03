package jTest;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import DAO.member_DAO;
import DTO.member_DTO;

public class member_jTest {

	member_DAO m = null;

	@Before
	public void m_dao() {
		m = member_DAO.m_dao();
	}

	@Test
	public void insert() {  //�����
		member_DTO member = new member_DTO();

		member.setId("akh");
		member.setPwd("1234");
		member.setName("����");
		member.setGender("����");
		member.setAge(30);

		m.insert(member);

	}

	@Test
	public void selectAll() { //��� ������ Ȯ�� �����ڿ�
		ArrayList<member_DTO> member = new ArrayList<>();
		member = m.selectAll();

		for (member_DTO i : member) {
			System.out.println("===============================");
			System.out.println("NO: " + i.getNo());
			System.out.println("ID: " + i.getId());
			System.out.println("PWD: " + i.getPwd());
			System.out.println("NAME: " + i.getName());
			System.out.println("GENDER: " + i.getGender());
			System.out.println("AGE: " + i.getAge());
			System.out.println("===============================");
		}
	}

	@Test
	public void pwdupdate() { //���̵��й�ȣ�Է��� ��й�ȣ����
		member_DTO member = new member_DTO();
		member.setId("akh");
		member.setPwd("1234");
		String pwd = "1111";
		m.PWDupdate(pwd, member);
	}
	
	@Test
	public void selectOne() { //���̵��й�ȣ�Է��� �ڽ��� �������Ȯ��
		member_DTO mm = new member_DTO();
		mm.setId("akh");
		mm.setPwd("1234");
		mm = m.selectOne(mm);
		System.out.println("===============================");
		System.out.println("NO: " + mm.getNo());
		System.out.println("ID: " + mm.getId());
		System.out.println("PWD: " +mm.getPwd());
		System.out.println("NAME: " +mm.getName());
		System.out.println("GENDER: " +mm.getGender());
		System.out.println("AGE: " +mm.getAge());
		System.out.println("===============================");
	}
	
	
	@Test
	public void delete() { // ���̵��й�ȣ�Է��� �ڽ��� ��������
		member_DTO mm = new member_DTO();
		mm.setId("akh");
		mm.setPwd("1234");
		m.delete(mm);
	}
	
	@Test
	public void login() {
		member_DTO mm = new member_DTO();
		String f = null;
		mm.setId("akh");
		mm.setPwd("1234");
		f = m.login(mm);
		System.out.println(f);
		
		
		
	}

}
