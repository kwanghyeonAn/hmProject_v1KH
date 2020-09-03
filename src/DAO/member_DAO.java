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

	public static member_DAO m_dao() { // �̱���
		return m = new member_DAO();
	}

	private member_DAO() {
	}

	// ==================DB����========================
	public Connection conn() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pwd);
			return conn;
		} catch (Exception e) {
			System.out.println("DB������ �����Ͽ����ϴ�.");
		}
		return null;
	}
	// ==================DB����========================

	public void insert(member_DTO m) { // ȸ������
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
					System.out.println("ȸ������ ����");
				} else {
					System.out.println("ȸ������ �Ϸ�");
				}
			} catch (Exception e) {
				System.out.println("ȸ������ ���ܹ߻�");
			} finally {
				try {
					if (conn != null)
						conn.close();
					if (ppst != null)
						ppst.close();
				} catch (Exception e2) {
					System.out.println("ȸ������ �ڿ���ȯ ���ܹ߻�");
				}
			}
		}
	}

	// =================================================================

	public ArrayList<member_DTO> selectAll() { // ��� ������ Ȯ�� �����ڿ�
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
					System.out.println("�˻��� �����������ϴ�.");
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
					System.out.println("�˻� �Ϸ�");
				}
			} catch (Exception e) {
				System.out.println("ȸ���������� ���ܹ߻�");
			} finally {
				try {
					if (conn != null)
						conn.close();
					if (st != null)
						st.close();
				} catch (Exception e2) {
					System.out.println("ȸ���������� �ڿ���ȯ ���ܹ߻�");
				}
			}
		}
		return mm;
	}
	// =================================================================

	public void PWDupdate(String update, member_DTO m) { // ���̵� ��й�ȣ �Է��� ��й�ȣ ����
		String sql = "update member set pwd = ? where id = ? and pwd = ?";
		PreparedStatement ppst = null;
		int a = 0; // ����̴��� ���� Ȯ�� ����
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setString(1, update);
				ppst.setString(2, m.getId());
				ppst.setString(3, m.getPwd());
				a = ppst.executeUpdate();
				if (a == 0) {
					System.out.println("���̵� �Ǵ� ��й�ȣ�� Ʋ���ϴ�.");
					System.out.println("Ȯ���Ͻð� �ٽ� �õ����ּ���.");
				} else {
					System.out.println("��й�ȣ ����Ϸ�");
				}
			} catch (Exception e) {
				System.out.println("��й�ȣ���� ���ܹ߻�");
			} finally {
				try {
					if (conn != null)
						conn.close();
					if (ppst != null)
						ppst.close();
				} catch (Exception e2) {
					System.out.println("��й�ȣ���� �ڿ���ȯ ���ܹ߻�");
				}
			}
		}
	}

	// =================================================================

	public member_DTO selectOne(member_DTO m) { // ���ο� ����Ȯ�� ���̵� ��й�ȣ��
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
					System.out.println("���̵� �Ǵ� ��й�ȣ�� Ʋ���ϴ�.");
					System.out.println("Ȯ���Ͻð� �ٽ� �õ����ּ���.");
				} else {
					if (rs.next()) {
						mm.setNo(rs.getInt("no"));
						mm.setId(rs.getString("id"));
						mm.setPwd(rs.getString("pwd"));
						mm.setName(rs.getString("name"));
						mm.setGender(rs.getString("gender"));
						mm.setAge(rs.getInt("age"));
						System.out.println("�˻��Ϸ�");
					}
				}
			} catch (Exception e) {
				System.out.println("����ȸ���������� ���ܹ߻�");
			} finally {
				try {
					if (conn != null)
						conn.close();
					if (ppst != null)
						ppst.close();
				} catch (Exception e2) {
					System.out.println("����ȸ���������� �ڿ���ȯ ���ܹ߻�");
				}
			}
		}
		return mm;
	}

	// =================================================================
	public void delete(member_DTO m) {  //�������� ���̵� �н����� �ʿ�
		String sql = "delete from member where id = ? and pwd = ?";

		PreparedStatement ppst = null;
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setString(1, m.getId());
				ppst.setString(2, m.getPwd());
				int a = ppst.executeUpdate();
				if (a == 0) {
					System.out.println("���̵� �Ǵ� ��й�ȣ�� Ʋ���ϴ�.");
					System.out.println("Ȯ���Ͻð� �ٽ� �õ����ּ���.");
				} else {
					System.out.println("�����Ϸ�Ǿ����ϴ�.");
				}
			} catch (Exception e) {
				System.out.println("���� ���ܹ߻�");
			} finally {
				try {
					if (conn != null)
						conn.close();
					if (ppst != null)
						ppst.close();
				} catch (Exception e2) {
					System.out.println("���� �ڿ���ȯ ���ܹ߻�");
				}
			}
		}

	}
	
	// =================================================================
	public String login(member_DTO m) {  //�α��α��
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
					f = "�α��� ����!!";
				}
			} catch (Exception e) {
				System.out.println("�α��� ���ܹ߻�");
			} finally {
				try {
					if(conn != null) conn.close();
					if(ppst != null) ppst.close();
				} catch (Exception e2) {
					System.out.println("�α��� �ڿ���ȯ ���ܹ߻�");
				}
			}
		}
		
		return f;
	}

}
