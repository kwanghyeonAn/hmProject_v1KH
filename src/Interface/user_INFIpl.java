package Interface;

import java.util.ArrayList;
import java.util.Scanner;

import DAO.buylist_DAO;
import DAO.item_DAO;
import DAO.member_DAO;
import DTO.buylist_DTO;
import DTO.item_DTO;
import DTO.member_DTO;

public class user_INFIpl implements user_INF {
	private Scanner in = new Scanner(System.in);
	private member_DAO m_dao = member_DAO.m_dao();
	private item_DAO i_dao = item_DAO.i_DAO();
	private buylist_DAO b_dao = buylist_DAO.buy();

	public static user_INFIpl u_INF = null;

	public static user_INFIpl u() {
		u_INF = new user_INFIpl();
		return u_INF;
	}

	private user_INFIpl() {
	}

	@Override
	public void membership() { // ��ȸ������
		member_DTO m = new member_DTO();
		System.out.println("===============================");
		System.out.println("ȸ������ �Դϴ�.");
		System.out.println("ID , ��й�ȣ�� ����,���ڷθ� �Է����ּ���.");
		System.out.println("ID , ��й�ȣ�� 10�ڸ������� �����մϴ�.");
		while (true) {
			System.out.println("ID�� �Է����ּ���.");
			m.setId(in.nextLine());
			if (m.getId().length() <= 10) {
				break;
			} else {
				System.out.println("ID 10���ڸ� �ʰ��Ͽ����ϴ�.");
				System.out.println("�ٽ� �Է����ּ���.");
				continue;
			}
		}
		while (true) {
			System.out.println("��й�ȣ�� �Է����ּ���.");
			m.setPwd(in.nextLine());
			if (m.getId().length() <= 10) {
				break;
			} else {
				System.out.println("��й�ȣ 10���ڸ� �ʰ��Ͽ����ϴ�.");
				System.out.println("�ٽ� �Է����ּ���.");
				continue;
			}
		}
		System.out.println("�̸��� �Է����ּ���.");
		m.setName(in.nextLine());
		System.out.println("������ �Է����ּ���. EX)���� , ����");
		m.setGender(in.nextLine());
		System.out.println("���̸� �Է����ּ���. ���ڷθ� �Է����ּ���.");
		m.setAge(in.nextInt());
		in.nextLine();
		m_dao.insert(m);
		System.out.println("===============================");

	}

	@Override
	public void pwdupdate() { // �� ��й�ȣ����
		member_DTO m = new member_DTO();
		String update = null;
		System.out.println("===============================");
		System.out.println("��й�ȣ �����Դϴ�.");
		System.out.println("���̵�� ��й�ȣ�� �ٽ� Ȯ���մϴ�..");
		System.out.println("ID�� �Է����ּ���.");
		m.setId(in.nextLine());
		System.out.println("��й�ȣ�� �Է����ּ���.");
		m.setPwd(in.nextLine());
		while (true) {
			System.out.println("������ ��й�ȣ�� �Է����ּ���.");
			System.out.println("��й�ȣ�� 10�ڸ������� �����մϴ�.");
			update = in.nextLine();
			if (update.length() <= 10) {
				break;
			} else {
				System.out.println("��й�ȣ 10���ڸ� �ʰ��Ͽ����ϴ�.");
				System.out.println("�ٽ� �Է����ּ���.");
				continue;
			}
		}
		System.out.println("===============================");
		m_dao.PWDupdate(update, m);

	}

	@Override
	public void delete() { // �� id����
		member_DTO m = new member_DTO();
		String k;
		System.out.println("===============================");
		System.out.println("���������Դϴ�.");
		System.out.println("������ �������ּ���.");
		System.out.println("���� �����Ͻðڽ��ϱ�?");
		while (true) {
			System.out.println("���� : y , ��� : n");
			k = in.nextLine();
			if (k.equals("y")) {
				System.out.println("������ ID�� �Է����ּ���.");
				m.setId(in.nextLine());
				System.out.println("������ ��й�ȣ�� �Է����ּ���.");
				m.setPwd(in.nextLine());
				System.out.println("===============================");
				m_dao.delete(m);
				break;
			} else if (k.equals("n")) {
				System.out.println("��� �Ǿ����ϴ�.");
				System.out.println("===============================");
				break;
			} else {
				System.out.println("  'y'  �Ǵ� 'n' ���θ� �Է����ּ���.");
				System.out.println("===============================");
			}
		}
	}

	@Override
	public void infoselect() { // �� ����Ȯ��
		member_DTO m = new member_DTO();
		System.out.println("===============================");
		System.out.println("Ȯ���ϰ��� �ϴ� ID , ��й�ȣ�� �ѹ��� �Է����ּ���.");
		System.out.println("ID�� �Է����ּ���.");
		m.setId(in.nextLine());
		System.out.println("��й�ȣ�� �Է����ּ���.");
		m.setPwd(in.nextLine());
		m = m_dao.selectOne(m);
		if (m != null) {
			System.out.println("===============================");
			System.out.println("NO: " + m.getNo());
			System.out.println("ID: " + m.getId());
			System.out.println("PWD: " + m.getPwd());
			System.out.println("NAME: " + m.getName());
			System.out.println("GENDER: " + m.getGender());
			System.out.println("AGE: " + m.getAge());
			System.out.println("===============================");
		}
	}

	@Override
	public void buylistselect() { // �� ��ٱ��� Ȯ��
		buylist_DTO b = new buylist_DTO();
		ArrayList<buylist_DTO> list = new ArrayList<>();
		int no;
		System.out.println("===============================");
		System.out.println("ȸ������ ���̵�� ��й�ȣ�Է��� ������ ��µǸ�");
		System.out.println("ȸ������ NO�� Ȯ���� �Է����ּ���.");
		infoselect();
		System.out.println("NO�� �Է����ּ���.");
		no = in.nextInt();
		in.nextLine();
		list = b_dao.selectOne(no);
		for (int i = 0; i < list.size(); i++) {
			System.out.println("===============================");
			System.out.println("BUYNO : " +list.get(i).getBuyNo());
			System.out.println("NO : " + list.get(i).getNo());
			System.out.println("ITEMNO : " + list.get(i).getItemNo());
			System.out.println("CNT : " + list.get(i).getCnt());
			System.out.println("===============================");
		}
	}

	@Override
	public void buyitemdelete() {  //�� ��ٱ��� ��ǰ ����
		int no;
		System.out.println("===============================");
		System.out.println("���� �����ϰ����ϴ� ��ٱ��� BUYNO�� Ȯ�����ּ���.");
		System.out.println("ȸ������ ��ٱ��� ���Ȯ����");
		System.out.println("������ BUYNO �� �Է����ּ���.");
		buylistselect();
		System.out.println("BUYNO �� �Է����ּ���.");
		no = in.nextInt();
		in.nextLine();
		System.out.println("===============================");
		b_dao.delete(no);
		
		
	}

	@Override
	public void buyitem() {  //���̿��ϴ� ������ ��ٱ��Ͽ� ���
		buylist_DTO b = new buylist_DTO();
		item_DTO i = new item_DTO();
		System.out.println("���Կ� NO , ITEMNO , ������ �Է����ּ���.");
		System.out.println("���Կ� NO�� �𸣽ø� 1���� �˰��ø� �ƹ���ȣ�� �Է����ּ���.");
		int k = in.nextInt();
		in.nextLine();
		if (k == 1) {
			infoselect();
		}
		System.out.println("===============================");
		System.out.println("��ǰ ���Ȯ���� ���Ͻø� 1���� �ʿ����������ø� �ƹ���ȣ�� �Է����ּ���.");
		k = in.nextInt();
		in.nextLine();
		if(k == 1) {
			itemlist();
		}
		System.out.println("===============================");
		System.out.println("���Կ� ȸ����ȣ NO�� �Է����ּ���.");
		b.setNo(in.nextInt());
		in.nextLine();
		System.out.println("�����ϰ����ϴ� ITEMNO�� �Է����ּ���.");
		b.setItemNo(in.nextInt());
		in.nextLine();
		System.out.println("���� ������ �Է����ּ���");
		b.setCnt(in.nextInt());
		in.nextLine();
		i.setItemNo(b.getItemNo());
		i.setItemCnt(b.getCnt());
		b_dao.insert(b);
		i_dao.cntminus(i);
		
	}

	@Override
	public void buyitempudate() { //����ٱ��Ͽ��ִ� ������ ��������
		buylist_DTO b = new buylist_DTO();
		buylistselect();
		System.out.println("===============================");
		System.out.println("���� ���������� 1���� ���Ҵ� 2���� �Է����ּ���.");
		int k = in.nextInt();
		in.nextLine();
		if (k == 1) {
			System.out.println("===============================");
			System.out.println("���Կ� NO�� �Է����ּ���.");
			b.setNo(in.nextInt());
			in.nextLine();
			System.out.println("�������� ��ų ������ �Է����ּ���.");
			b.setCnt(in.nextInt());
			in.nextLine();
			b_dao.cntplus(b);
		} else if (k == 2) {
			System.out.println("===============================");
			System.out.println("���Կ� NO�� �Է����ּ���.");
			b.setNo(in.nextInt());
			in.nextLine();
			System.out.println("�������� ��ų ������ �Է����ּ���.");
			b.setCnt(in.nextInt());
			in.nextLine();
			b_dao.cntminus(b);
		} else {
			System.out.println("�߸��Է��ϼ̽��ϴ�. 1 �Ǵ� 2���� �Է����ּ���.");
		}
	}

	@Override
	public void itemlist() { // ���Ű��ɸ�Ϻ���
		ArrayList<item_DTO> list = i_dao.selectAll();
		for (int i = 0; i < list.size(); i++) {
			System.out.println("ITEMNO : "+list.get(i).getItemNo());
			System.out.println("ITEMNAME : "+list.get(i).getItemName());
			System.out.println("ITEMPRICE : "+list.get(i).getItemPrice());
			System.out.println("ITEMCNT : "+list.get(i).getItemCnt());
		}

	}

	@Override
	public int login() { // �α���
		while (true) {
			member_DTO m = new member_DTO();
			String f = null;
			int k = 0;
			System.out.println("===============================");
			System.out.println("�α����Դϴ�.");
			System.out.println("ID�� �Է����ּ���.");
			m.setId(in.nextLine());
			System.out.println("PWD�� �Է����ּ���");
			m.setPwd(in.nextLine());
			f = m_dao.login(m);
			if (f == null) {
				System.out.println("===============================");
				System.out.println("�α��� ����");
				System.out.println("ID�� ��й�ȣ�� Ȯ�����ּ���.");
				System.out.println("�޴��� ���ư���� 0 ���� ");
				System.out.println("�α��� �ٽ� �õ��� 0���� !������! �ƹ����ڸ��Է����ּ���.");
				k = in.nextInt();
				in.nextLine();
				System.out.println("===============================");
				if(k == 0) {
					return 0;
				}
				
			} else {
				System.out.println("===============================");
				System.out.println(f);
				System.out.println("===============================");
				return 1;

			}
		}
	}

}
