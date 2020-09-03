package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.buylist_DTO;

public class buylist_DAO {
	private Connection conn = null;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String id = "system";
	private String pwd = "1111";

	public static buylist_DAO b = null;
	private ResultSet rs = null;

	public static buylist_DAO buy() {
		b = new buylist_DAO();
		return b;
	}

	private buylist_DAO() {
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

	public void insert(buylist_DTO b) { // 고객이 자신의 회번번호와 제품번호 구매수량을 입력하여 장바구니에 담는다.
		String sql = "insert into buylist values (sqc_buylist.nextval,?,?,?)";

		PreparedStatement ppst = null;

		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setInt(1, b.getNo());
				ppst.setInt(2, b.getItemNo());
				ppst.setInt(3, b.getCnt());
				int a = ppst.executeUpdate();
				if (a == 0) {
					System.out.println("장바구니 담기 실패 ");
				} else {
					System.out.println("장바구니 담기 완료");
				}
			} catch (Exception e) {
				System.out.println("장바구니 예외발생");
			} finally {
				try {
					if (conn != null)
						conn.close();
					if (ppst != null)
						ppst.close();
				} catch (Exception e2) {
					System.out.println("장바구니 자원반환 예외발생");
				}
			}
		}
	}

	// =================================================================
	public void cntplus(buylist_DTO m) { // 자신의 장바구니 구매목록에 수량을 증가한다.
		String sql = "update buylist set cnt = cnt+? where no = ?";
		PreparedStatement ppst = null;
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setInt(1, m.getCnt());
				ppst.setInt(2, m.getNo());
				int a = ppst.executeUpdate();
				if (a == 0) {
					System.out.println("장바구니 번호가 존재하지 않습니다.");
					System.out.println("확인하고 다시 시도해주세요.");
				} else {
					System.out.println("수량증가 완료.");
				}
			} catch (Exception e) {
				System.out.println("장바구니 수량증가 예외발생");
			} finally {
				try {
					if (conn != null)
						conn.close();
					if (ppst != null)
						ppst.close();
				} catch (Exception e2) {
					System.out.println("장바구니 수량증가 자원반환 예외발생");
				}
			}
		}
	}

	// =================================================================
	public void cntminus(buylist_DTO m) { // 자신의 장바구니 구매목록에 수량을 감소한다.
		String sql = "update buylist set cnt = cnt-? where no = ?";
		PreparedStatement ppst = null;
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setInt(1, m.getCnt());
				ppst.setInt(2, m.getNo());
				int a = ppst.executeUpdate();
				if (a == 0) {
					System.out.println("장바구니 번호가 존재하지 않습니다.");
					System.out.println("확인하고 다시 시도해주세요.");
				} else {
					System.out.println("수량감소 완료.");
				}
			} catch (Exception e) {
				System.out.println("장바구니 수량감소 예외발생");
			} finally {
				try {
					if (conn != null)
						conn.close();
					if (ppst != null)
						ppst.close();
				} catch (Exception e2) {
					System.out.println("장바구니 수량감소 자원반환 예외발생");
				}
			}
		}
	}

	// =================================================================

	public ArrayList<buylist_DTO> selectAll() { // 관리자용 모든장바구니 정보를 확인.
		String sql = "select * from buylist";
		Statement st = null;
		ArrayList<buylist_DTO> list = new ArrayList<>();
		buylist_DTO b = new buylist_DTO();
		if (conn() != null) {
			try {
				st = conn.createStatement();
				int a = st.executeUpdate(sql);
				rs = st.executeQuery(sql);
				if (a == 0) {
					System.out.println("장바구니 목록이 없습니다.");
				} else {
					while (rs.next()) {
						b.setBuyNo(rs.getInt("buyno"));
						b.setNo(rs.getInt("no"));
						b.setItemNo(rs.getInt("itemno"));
						b.setCnt(rs.getInt("cnt"));
						list.add(b);
					}
					System.out.println("장바구니 목록 읽기 성공");
				}
			} catch (Exception e) {
				System.out.println("장바구니 목록 읽기 예외발생");
			} finally {
				try {
					if (conn != null)
						conn.close();
					if (st != null)
						st.close();
				} catch (Exception e2) {
					System.out.println("장바구니 목록 읽기 자원반환 예외발생");
				}
			}
		}
		return list;
	}
	// =================================================================

	public ArrayList<buylist_DTO> selectOne(int no) { // 고객 자신의 장바구니 목록을 확인할떄
		String sql = "select * from buylist where no = ?";
		buylist_DTO b = new buylist_DTO();
		PreparedStatement ppst = null;
		ArrayList<buylist_DTO> list = new ArrayList<>();

		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setInt(1, no);
				int a = ppst.executeUpdate();
				rs = ppst.executeQuery();
				if (a == 0) {
					System.out.println("고객번호가 존재하지않습니다");
					System.out.println("확인하고 다시 시도해주세요.");
				} else {
					while (rs.next()) {
						b.setBuyNo(rs.getInt("buyno"));
						b.setNo(rs.getInt("no"));
						b.setItemNo(rs.getInt("itemno"));
						b.setCnt(rs.getInt("cnt"));
						list.add(b);
					}
					System.out.println("검색을 완료하였습니다.");
				}
			} catch (Exception e) {
				System.out.println("고객의 장바구니 조회 예외발생");
			} finally {
				try {
					if (conn != null)
						conn.close();
					if (ppst != null)
						ppst.close();
				} catch (Exception e2) {
					System.out.println("고객의 장바구니 조회 자원반환 예외발생");
				}
			}
		}
		return list;
	}
	// =================================================================

	public void delete(int no) {
		String sql = "delete from buylist where buyno = ?";
		PreparedStatement ppst = null;
		int a = 0;

		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setInt(1, no);
				a = ppst.executeUpdate();
				if (a == 0) {
					System.out.println("장바구니 삭제 실패");
					System.out.println("BUYNO 을 확인해주세요.");
				}else {
					System.out.println("장바구니 삭제 성공");
				}

			} catch (Exception e) {
				System.out.println("장바구니 삭제 예외발생");
			} finally {
				try {
					if (conn != null) conn.close();
					if (ppst != null) ppst.close();
				} catch (Exception e2) {
					System.out.println("장바구니 삭제 자원반환 예외발생");
				}
			}
		}
	}

}
