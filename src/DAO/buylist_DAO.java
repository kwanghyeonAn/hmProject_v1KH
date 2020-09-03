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

	public void insert(buylist_DTO b) { // ���� �ڽ��� ȸ����ȣ�� ��ǰ��ȣ ���ż����� �Է��Ͽ� ��ٱ��Ͽ� ��´�.
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
					System.out.println("��ٱ��� ��� ���� ");
				} else {
					System.out.println("��ٱ��� ��� �Ϸ�");
				}
			} catch (Exception e) {
				System.out.println("��ٱ��� ���ܹ߻�");
			} finally {
				try {
					if (conn != null)
						conn.close();
					if (ppst != null)
						ppst.close();
				} catch (Exception e2) {
					System.out.println("��ٱ��� �ڿ���ȯ ���ܹ߻�");
				}
			}
		}
	}

	// =================================================================
	public void cntplus(buylist_DTO m) { // �ڽ��� ��ٱ��� ���Ÿ�Ͽ� ������ �����Ѵ�.
		String sql = "update buylist set cnt = cnt+? where no = ?";
		PreparedStatement ppst = null;
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setInt(1, m.getCnt());
				ppst.setInt(2, m.getNo());
				int a = ppst.executeUpdate();
				if (a == 0) {
					System.out.println("��ٱ��� ��ȣ�� �������� �ʽ��ϴ�.");
					System.out.println("Ȯ���ϰ� �ٽ� �õ����ּ���.");
				} else {
					System.out.println("�������� �Ϸ�.");
				}
			} catch (Exception e) {
				System.out.println("��ٱ��� �������� ���ܹ߻�");
			} finally {
				try {
					if (conn != null)
						conn.close();
					if (ppst != null)
						ppst.close();
				} catch (Exception e2) {
					System.out.println("��ٱ��� �������� �ڿ���ȯ ���ܹ߻�");
				}
			}
		}
	}

	// =================================================================
	public void cntminus(buylist_DTO m) { // �ڽ��� ��ٱ��� ���Ÿ�Ͽ� ������ �����Ѵ�.
		String sql = "update buylist set cnt = cnt-? where no = ?";
		PreparedStatement ppst = null;
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setInt(1, m.getCnt());
				ppst.setInt(2, m.getNo());
				int a = ppst.executeUpdate();
				if (a == 0) {
					System.out.println("��ٱ��� ��ȣ�� �������� �ʽ��ϴ�.");
					System.out.println("Ȯ���ϰ� �ٽ� �õ����ּ���.");
				} else {
					System.out.println("�������� �Ϸ�.");
				}
			} catch (Exception e) {
				System.out.println("��ٱ��� �������� ���ܹ߻�");
			} finally {
				try {
					if (conn != null)
						conn.close();
					if (ppst != null)
						ppst.close();
				} catch (Exception e2) {
					System.out.println("��ٱ��� �������� �ڿ���ȯ ���ܹ߻�");
				}
			}
		}
	}

	// =================================================================

	public ArrayList<buylist_DTO> selectAll() { // �����ڿ� �����ٱ��� ������ Ȯ��.
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
					System.out.println("��ٱ��� ����� �����ϴ�.");
				} else {
					while (rs.next()) {
						b.setBuyNo(rs.getInt("buyno"));
						b.setNo(rs.getInt("no"));
						b.setItemNo(rs.getInt("itemno"));
						b.setCnt(rs.getInt("cnt"));
						list.add(b);
					}
					System.out.println("��ٱ��� ��� �б� ����");
				}
			} catch (Exception e) {
				System.out.println("��ٱ��� ��� �б� ���ܹ߻�");
			} finally {
				try {
					if (conn != null)
						conn.close();
					if (st != null)
						st.close();
				} catch (Exception e2) {
					System.out.println("��ٱ��� ��� �б� �ڿ���ȯ ���ܹ߻�");
				}
			}
		}
		return list;
	}
	// =================================================================

	public ArrayList<buylist_DTO> selectOne(int no) { // �� �ڽ��� ��ٱ��� ����� Ȯ���ҋ�
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
					System.out.println("����ȣ�� ���������ʽ��ϴ�");
					System.out.println("Ȯ���ϰ� �ٽ� �õ����ּ���.");
				} else {
					while (rs.next()) {
						b.setBuyNo(rs.getInt("buyno"));
						b.setNo(rs.getInt("no"));
						b.setItemNo(rs.getInt("itemno"));
						b.setCnt(rs.getInt("cnt"));
						list.add(b);
					}
					System.out.println("�˻��� �Ϸ��Ͽ����ϴ�.");
				}
			} catch (Exception e) {
				System.out.println("���� ��ٱ��� ��ȸ ���ܹ߻�");
			} finally {
				try {
					if (conn != null)
						conn.close();
					if (ppst != null)
						ppst.close();
				} catch (Exception e2) {
					System.out.println("���� ��ٱ��� ��ȸ �ڿ���ȯ ���ܹ߻�");
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
					System.out.println("��ٱ��� ���� ����");
					System.out.println("BUYNO �� Ȯ�����ּ���.");
				}else {
					System.out.println("��ٱ��� ���� ����");
				}

			} catch (Exception e) {
				System.out.println("��ٱ��� ���� ���ܹ߻�");
			} finally {
				try {
					if (conn != null) conn.close();
					if (ppst != null) ppst.close();
				} catch (Exception e2) {
					System.out.println("��ٱ��� ���� �ڿ���ȯ ���ܹ߻�");
				}
			}
		}
	}

}
