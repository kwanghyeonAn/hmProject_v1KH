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
	public void membership() { // 고객회원가입
		member_DTO m = new member_DTO();
		System.out.println("===============================");
		System.out.println("회원가입 입니다.");
		System.out.println("ID , 비밀번호는 영어,숫자로만 입력해주세요.");
		System.out.println("ID , 비밀번호는 10자리까지만 가능합니다.");
		while (true) {
			System.out.println("ID를 입력해주세요.");
			m.setId(in.nextLine());
			if (m.getId().length() <= 10) {
				break;
			} else {
				System.out.println("ID 10글자를 초과하였습니다.");
				System.out.println("다시 입력해주세요.");
				continue;
			}
		}
		while (true) {
			System.out.println("비밀번호를 입력해주세요.");
			m.setPwd(in.nextLine());
			if (m.getId().length() <= 10) {
				break;
			} else {
				System.out.println("비밀번호 10글자를 초과하였습니다.");
				System.out.println("다시 입력해주세요.");
				continue;
			}
		}
		System.out.println("이름을 입력해주세요.");
		m.setName(in.nextLine());
		System.out.println("성별을 입력해주세요. EX)남자 , 여자");
		m.setGender(in.nextLine());
		System.out.println("나이를 입력해주세요. 숫자로만 입력해주세요.");
		m.setAge(in.nextInt());
		in.nextLine();
		m_dao.insert(m);
		System.out.println("===============================");

	}

	@Override
	public void pwdupdate() { // 고객 비밀번호변경
		member_DTO m = new member_DTO();
		String update = null;
		System.out.println("===============================");
		System.out.println("비밀번호 변경입니다.");
		System.out.println("아이디와 비밀번호를 다시 확인합니다..");
		System.out.println("ID를 입력해주세요.");
		m.setId(in.nextLine());
		System.out.println("비밀번호를 입력해주세요.");
		m.setPwd(in.nextLine());
		while (true) {
			System.out.println("변경할 비밀번호를 입력해주세요.");
			System.out.println("비밀번호는 10자리까지만 가능합니다.");
			update = in.nextLine();
			if (update.length() <= 10) {
				break;
			} else {
				System.out.println("비밀번호 10글자를 초과하였습니다.");
				System.out.println("다시 입력해주세요.");
				continue;
			}
		}
		System.out.println("===============================");
		m_dao.PWDupdate(update, m);

	}

	@Override
	public void delete() { // 고객 id삭제
		member_DTO m = new member_DTO();
		String k;
		System.out.println("===============================");
		System.out.println("계정삭제입니다.");
		System.out.println("신중히 선택해주세요.");
		System.out.println("정말 삭제하시겠습니까?");
		while (true) {
			System.out.println("삭제 : y , 취소 : n");
			k = in.nextLine();
			if (k.equals("y")) {
				System.out.println("삭제할 ID를 입력해주세요.");
				m.setId(in.nextLine());
				System.out.println("삭제할 비밀번호를 입력해주세요.");
				m.setPwd(in.nextLine());
				System.out.println("===============================");
				m_dao.delete(m);
				break;
			} else if (k.equals("n")) {
				System.out.println("취소 되었습니다.");
				System.out.println("===============================");
				break;
			} else {
				System.out.println("  'y'  또는 'n' 으로만 입력해주세요.");
				System.out.println("===============================");
			}
		}
	}

	@Override
	public void infoselect() { // 고객 정보확인
		member_DTO m = new member_DTO();
		System.out.println("===============================");
		System.out.println("확인하고자 하는 ID , 비밀번호를 한번더 입력해주세요.");
		System.out.println("ID를 입력해주세요.");
		m.setId(in.nextLine());
		System.out.println("비밀번호를 입력해주세요.");
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
	public void buylistselect() { // 고객 장바구니 확인
		buylist_DTO b = new buylist_DTO();
		ArrayList<buylist_DTO> list = new ArrayList<>();
		int no;
		System.out.println("===============================");
		System.out.println("회원님의 아이디와 비밀번호입력후 정보가 출력되면");
		System.out.println("회원님의 NO를 확인후 입력해주세요.");
		infoselect();
		System.out.println("NO를 입력해주세요.");
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
	public void buyitemdelete() {  //고객 장바구니 물품 삭제
		int no;
		System.out.println("===============================");
		System.out.println("먼저 삭제하고자하는 장바구니 BUYNO를 확인해주세요.");
		System.out.println("회원님의 장바구니 목록확인후");
		System.out.println("삭제할 BUYNO 을 입력해주세요.");
		buylistselect();
		System.out.println("BUYNO 을 입력해주세요.");
		no = in.nextInt();
		in.nextLine();
		System.out.println("===============================");
		b_dao.delete(no);
		
		
	}

	@Override
	public void buyitem() {  //고객이원하는 아이템 장바구니에 담기
		buylist_DTO b = new buylist_DTO();
		item_DTO i = new item_DTO();
		System.out.println("고객님에 NO , ITEMNO , 수량을 입력해주세요.");
		System.out.println("고객님에 NO를 모르시면 1번을 알고계시면 아무번호나 입력해주세요.");
		int k = in.nextInt();
		in.nextLine();
		if (k == 1) {
			infoselect();
		}
		System.out.println("===============================");
		System.out.println("물품 목록확인을 원하시면 1번을 필요하지않으시면 아무번호나 입력해주세요.");
		k = in.nextInt();
		in.nextLine();
		if(k == 1) {
			itemlist();
		}
		System.out.println("===============================");
		System.out.println("고객님에 회원번호 NO를 입력해주세요.");
		b.setNo(in.nextInt());
		in.nextLine();
		System.out.println("구매하고자하는 ITEMNO를 입력해주세요.");
		b.setItemNo(in.nextInt());
		in.nextLine();
		System.out.println("구매 수량을 입력해주세요");
		b.setCnt(in.nextInt());
		in.nextLine();
		i.setItemNo(b.getItemNo());
		i.setItemCnt(b.getCnt());
		b_dao.insert(b);
		i_dao.cntminus(i);
		
	}

	@Override
	public void buyitempudate() { //고객장바구니에있는 아이템 수량조절
		buylist_DTO b = new buylist_DTO();
		buylistselect();
		System.out.println("===============================");
		System.out.println("구매 수량증가는 1번을 감소는 2번을 입력해주세요.");
		int k = in.nextInt();
		in.nextLine();
		if (k == 1) {
			System.out.println("===============================");
			System.out.println("고객님에 NO를 입력해주세요.");
			b.setNo(in.nextInt());
			in.nextLine();
			System.out.println("수량증가 시킬 갯수를 입력해주세요.");
			b.setCnt(in.nextInt());
			in.nextLine();
			b_dao.cntplus(b);
		} else if (k == 2) {
			System.out.println("===============================");
			System.out.println("고객님에 NO를 입력해주세요.");
			b.setNo(in.nextInt());
			in.nextLine();
			System.out.println("수량감소 시킬 갯수를 입력해주세요.");
			b.setCnt(in.nextInt());
			in.nextLine();
			b_dao.cntminus(b);
		} else {
			System.out.println("잘못입력하셨습니다. 1 또는 2번을 입력해주세요.");
		}
	}

	@Override
	public void itemlist() { // 구매가능목록보기
		ArrayList<item_DTO> list = i_dao.selectAll();
		for (int i = 0; i < list.size(); i++) {
			System.out.println("ITEMNO : "+list.get(i).getItemNo());
			System.out.println("ITEMNAME : "+list.get(i).getItemName());
			System.out.println("ITEMPRICE : "+list.get(i).getItemPrice());
			System.out.println("ITEMCNT : "+list.get(i).getItemCnt());
		}

	}

	@Override
	public int login() { // 로그인
		while (true) {
			member_DTO m = new member_DTO();
			String f = null;
			int k = 0;
			System.out.println("===============================");
			System.out.println("로그인입니다.");
			System.out.println("ID를 입력해주세요.");
			m.setId(in.nextLine());
			System.out.println("PWD를 입력해주세요");
			m.setPwd(in.nextLine());
			f = m_dao.login(m);
			if (f == null) {
				System.out.println("===============================");
				System.out.println("로그인 실패");
				System.out.println("ID와 비밀번호를 확인해주세요.");
				System.out.println("메뉴로 돌아가기는 0 번을 ");
				System.out.println("로그인 다시 시도는 0번을 !제외한! 아무숫자를입력해주세요.");
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
