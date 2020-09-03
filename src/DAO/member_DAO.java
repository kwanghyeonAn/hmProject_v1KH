package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.member_DTO;

public class member_DAO {

	private Connection conn = null;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String id = "system";
	private String pwd = "1111";

	private ResultSet rs = null;
	public static member_DAO m = null;

	public static member_DAO m_dao() { // 싱글톤
		return m = new member_DAO();
	}

	private member_DAO() {
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

	public void insert(member_DTO m) { // 회원가입
		String sql = "insert into member values (sqc_member.nextval,?,?,?,?,?)";
		PreparedStatement ppst = null;
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setString(1, m.getId());
				ppst.setString(2, m.getPwd());
				ppst.setString(3, m.getName());
				ppst.setString(4, m.getGender());
				ppst.setInt(5, m.getAge());
				int a = ppst.executeUpdate();
				if (a == 0) {
					System.out.println("회원가입 실패");
				} else {
					System.out.println("회원가입 완료");
				}
			} catch (Exception e) {
				System.out.println("회원가입 예외발생");
			} finally {
				try {
					if (conn != null)
						conn.close();
					if (ppst != null)
						ppst.close();
				} catch (Exception e2) {
					System.out.println("회원가입 자원반환 예외발생");
				}
			}
		}
	}

	// =================================================================

	public ArrayList<member_DTO> selectAll() { // 모든 고객정보 확인 관리자용
		String sql = "select * from member order by no desc";
		Statement st = null;
		ArrayList<member_DTO> mm = new ArrayList<>();
		member_DTO m = new member_DTO();
		if (conn() != null) {
			try {
				st = conn.createStatement();
				int a = st.executeUpdate(sql);
				rs = st.executeQuery(sql);
				
				if (a == 0) {
					System.out.println("검색된 정보가없습니다.");
				} else {
					while (rs.next()) {
						m.setNo(rs.getInt("no"));
						m.setId(rs.getString("id"));
						m.setPwd(rs.getString("pwd"));
						m.setName(rs.getString("name"));
						m.setGender(rs.getString("gender"));
						m.setAge(rs.getInt("age"));
						mm.add(m);
					}
					System.out.println("검색 완료");
				}
			} catch (Exception e) {
				System.out.println("회원정보보기 예외발생");
			} finally {
				try {
					if (conn != null)
						conn.close();
					if (st != null)
						st.close();
				} catch (Exception e2) {
					System.out.println("회원정보보기 자원반환 예외발생");
				}
			}
		}
		return mm;
	}
	// =================================================================

	public void PWDupdate(String update, member_DTO m) { // 아이디 비밀번호 입력후 비밀번호 변경
		String sql = "update member set pwd = ? where id = ? and pwd = ?";
		PreparedStatement ppst = null;
		int a = 0; // 실행됫는지 여부 확인 변수
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setString(1, update);
				ppst.setString(2, m.getId());
				ppst.setString(3, m.getPwd());
				a = ppst.executeUpdate();
				if (a == 0) {
					System.out.println("아이디 또는 비밀번호가 틀립니다.");
					System.out.println("확인하시고 다시 시도해주세요.");
				} else {
					System.out.println("비밀번호 변경완료");
				}
			} catch (Exception e) {
				System.out.println("비밀번호변경 예외발생");
			} finally {
				try {
					if (conn != null)
						conn.close();
					if (ppst != null)
						ppst.close();
				} catch (Exception e2) {
					System.out.println("비밀번호변경 자원반환 예외발생");
				}
			}
		}
	}

	// =================================================================

	public member_DTO selectOne(member_DTO m) { // 개인에 정보확인 아이디 비밀번호로
		String sql = "select * from member where id = ? and pwd = ?";
		PreparedStatement ppst = null;
		member_DTO mm = new member_DTO();
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setString(1, m.getId());
				ppst.setString(2, m.getPwd());
				int a = ppst.executeUpdate();
				rs = ppst.executeQuery(sql);
				if (a == 0) {
					System.out.println("아이디 또는 비밀번호가 틀립니다.");
					System.out.println("확인하시고 다시 시도해주세요.");
				} else {
					if (rs.next()) {
						mm.setNo(rs.getInt("no"));
						mm.setId(rs.getString("id"));
						mm.setPwd(rs.getString("pwd"));
						mm.setName(rs.getString("name"));
						mm.setGender(rs.getString("gender"));
						mm.setAge(rs.getInt("age"));
						System.out.println("검색완료");
					}
				}
			} catch (Exception e) {
				System.out.println("개인회원정보보기 예외발생");
			} finally {
				try {
					if (conn != null)
						conn.close();
					if (ppst != null)
						ppst.close();
				} catch (Exception e2) {
					System.out.println("개인회원정보보기 자원반환 예외발생");
				}
			}
		}
		return mm;
	}

	// =================================================================
	public void delete(member_DTO m) {  //계정삭제 아이디 패스워드 필요
		String sql = "delete from member where id = ? and pwd = ?";

		PreparedStatement ppst = null;
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setString(1, m.getId());
				ppst.setString(2, m.getPwd());
				int a = ppst.executeUpdate();
				if (a == 0) {
					System.out.println("아이디 또는 비밀번호가 틀립니다.");
					System.out.println("확인하시고 다시 시도해주세요.");
				} else {
					System.out.println("삭제완료되었습니다.");
				}
			} catch (Exception e) {
				System.out.println("삭제 예외발생");
			} finally {
				try {
					if (conn != null)
						conn.close();
					if (ppst != null)
						ppst.close();
				} catch (Exception e2) {
					System.out.println("삭제 자원반환 예외발생");
				}
			}
		}

	}
	
	// =================================================================
	public String login(member_DTO m) {  //로그인기능
		String sql = "select * from member where id = ? and pwd = ?";
		PreparedStatement ppst = null;
		int aa;
		String f = null;
		if(conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setString(1, m.getId());
				ppst.setString(2, m.getPwd());
				aa = ppst.executeUpdate();
				if (aa == 0) {
					f = null;
				} else {
					f = "로그인 성공!!";
				}
			} catch (Exception e) {
				System.out.println("로그인 예외발생");
			} finally {
				try {
					if(conn != null) conn.close();
					if(ppst != null) ppst.close();
				} catch (Exception e2) {
					System.out.println("로그인 자원반환 예외발생");
				}
			}
		}
		
		return f;
	}

}
