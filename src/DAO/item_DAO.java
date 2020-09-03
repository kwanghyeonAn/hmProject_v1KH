package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.item_DTO;

public class item_DAO {

	private Connection conn = null;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String id = "system";
	private String pwd = "1111";

	private ResultSet rs = null;
	public static item_DAO i = null;

	public static item_DAO i_DAO() {
		i = new item_DAO();
		return i;
	}

	private item_DAO() {
	}

	// ==================DB연걸========================
	public Connection conn() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pwd);
			return conn;
		} catch (Exception e) {
			System.out.println("DB연결이 실패하였습니다.");
		}
		return null;
	}
	// ==================DB연걸========================

	public void insert(item_DTO m) { // 마스크 입고 관리자용
		String sql = "insert into item values (sqc_item.nextval,?,?,?)";
		PreparedStatement ppst = null;
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setString(1, m.getItemName());
				ppst.setInt(2, m.getItemPrice());
				ppst.setInt(3, m.getItemCnt());
				int a = ppst.executeUpdate();
				if( a == 0 ) {
					System.out.println("마스크 입고 실패");
					System.out.println("입력정보를 확인하고 다시 시도해주세요.");
				} else {
					System.out.println("마스크 입고완료");
				}
			} catch (Exception e) {
				System.out.println("마스크 입고 예외 발생");
			} finally {
				try {
					if (conn != null)
						conn.close();
					if (ppst != null)
						ppst.close();
				} catch (Exception e2) {
					System.out.println("마스크 입고 자원반환 예외 발생");
				}
			}
		}
	}

	// =================================================================

	public void cntplus(item_DTO i) { // 제품번호로 수량 증가 관리자용
		String sql = "update item set itemcnt = itemcnt+? where itemno = ?";
		PreparedStatement ppst = null;
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setInt(1, i.getItemCnt());
				ppst.setInt(2, i.getItemNo());
				int a = ppst.executeUpdate();
				if (a == 0) {
					System.out.println("제품검색안됨. 제품번호 NO를 다시 확인해주세요");
				} else {
					System.out.println("수량증가 완료.");
				}
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("수량증가 예외발생");
			} finally {
				try {
					if (conn != null)
						conn.close();
					if (ppst != null)
						ppst.close();
				} catch (Exception e2) {
					System.out.println("수량증가 자원반환 예외발생");
				}
			}
		}
	}

	// =================================================================

	public void cntminus(item_DTO i) { //제품번호로 수량감소 관리자용
		String sql = "update item set itemcnt = itemcnt-? where itemno = ?";
		PreparedStatement ppst = null;
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setInt(1, i.getItemCnt());
				ppst.setInt(2, i.getItemNo());
				int a = ppst.executeUpdate();
				if (a == 0) {
					System.out.println("제품검색안됨. 제품번호 NO를 다시 확인해주세요");
				} else {
					System.out.println("수량감소 완료.");
				}
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("수량감소 예외발생");
			} finally {
				try {
					if (conn != null)
						conn.close();
					if (ppst != null)
						ppst.close();
				} catch (Exception e2) {
					System.out.println("수량감소 자원반환 예외발생");
				}
			}
		}
	}

	// =================================================================

	public void delete(int no) { //제품번호로 해당제품 삭제 관리자용
		String sql = "delete from item where itemno = ?";

		PreparedStatement ppst = null;
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setInt(1, no);
				int a = ppst.executeUpdate();
				if (a == 0) {
					System.out.println("제품번호가 존재하지않습니다.");
					System.out.println("확인하시고 다시 시도해주세요.");
				} else {
					System.out.println("제품삭제완료되었습니다.");
				}
			} catch (Exception e) {
				System.out.println("제품삭제 예외발생");
			} finally {
				try {
					if (conn != null)
						conn.close();
					if (ppst != null)
						ppst.close();
				} catch (Exception e2) {
					System.out.println("제품삭제 자원반환 예외발생");
				}
			}
		}

	}
	// =================================================================
	public ArrayList<item_DTO> selectAll() { //모든제품 보기 
		String sql = "select * from item";
		Statement st = null;
		ArrayList<item_DTO> list = new ArrayList<>();
		item_DTO i = new item_DTO();
		if(conn() != null) {
			try {
				st = conn.createStatement();
				int a = st.executeUpdate(sql);
				rs = st.executeQuery(sql);
				
				if (a == 0) {
					System.out.println("등록된 제품이 없습니다.");
					System.out.println("제품을 등록해주세요.");
				}else {
					while (rs.next()) {
						i.setItemNo(rs.getInt("itemno"));
						i.setItemName(rs.getString("itemname"));
						i.setItemPrice(rs.getInt("itemprice"));
						i.setItemCnt(rs.getInt("itemcnt"));
						list.add(i);
					}
					System.out.println("정보 읽기 성공");
				}
			} catch (Exception e) {
				System.out.println("제품목록보기 예외발생");
			} finally {
				try {
					if (conn != null) conn.close();
					if (st != null) st.close();
				} catch (Exception e2) {
					System.out.println("제품목록보기 자원반환 예외발생");
				}
			}
		}
		return list;
	}

}
