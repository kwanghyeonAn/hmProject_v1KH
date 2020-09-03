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

	public void insert(item_DTO m) { // ����ũ �԰� �����ڿ�
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
					System.out.println("����ũ �԰� ����");
					System.out.println("�Է������� Ȯ���ϰ� �ٽ� �õ����ּ���.");
				} else {
					System.out.println("����ũ �԰�Ϸ�");
				}
			} catch (Exception e) {
				System.out.println("����ũ �԰� ���� �߻�");
			} finally {
				try {
					if (conn != null)
						conn.close();
					if (ppst != null)
						ppst.close();
				} catch (Exception e2) {
					System.out.println("����ũ �԰� �ڿ���ȯ ���� �߻�");
				}
			}
		}
	}

	// =================================================================

	public void cntplus(item_DTO i) { // ��ǰ��ȣ�� ���� ���� �����ڿ�
		String sql = "update item set itemcnt = itemcnt+? where itemno = ?";
		PreparedStatement ppst = null;
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setInt(1, i.getItemCnt());
				ppst.setInt(2, i.getItemNo());
				int a = ppst.executeUpdate();
				if (a == 0) {
					System.out.println("��ǰ�˻��ȵ�. ��ǰ��ȣ NO�� �ٽ� Ȯ�����ּ���");
				} else {
					System.out.println("�������� �Ϸ�.");
				}
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("�������� ���ܹ߻�");
			} finally {
				try {
					if (conn != null)
						conn.close();
					if (ppst != null)
						ppst.close();
				} catch (Exception e2) {
					System.out.println("�������� �ڿ���ȯ ���ܹ߻�");
				}
			}
		}
	}

	// =================================================================

	public void cntminus(item_DTO i) { //��ǰ��ȣ�� �������� �����ڿ�
		String sql = "update item set itemcnt = itemcnt-? where itemno = ?";
		PreparedStatement ppst = null;
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setInt(1, i.getItemCnt());
				ppst.setInt(2, i.getItemNo());
				int a = ppst.executeUpdate();
				if (a == 0) {
					System.out.println("��ǰ�˻��ȵ�. ��ǰ��ȣ NO�� �ٽ� Ȯ�����ּ���");
				} else {
					System.out.println("�������� �Ϸ�.");
				}
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("�������� ���ܹ߻�");
			} finally {
				try {
					if (conn != null)
						conn.close();
					if (ppst != null)
						ppst.close();
				} catch (Exception e2) {
					System.out.println("�������� �ڿ���ȯ ���ܹ߻�");
				}
			}
		}
	}

	// =================================================================

	public void delete(int no) { //��ǰ��ȣ�� �ش���ǰ ���� �����ڿ�
		String sql = "delete from item where itemno = ?";

		PreparedStatement ppst = null;
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setInt(1, no);
				int a = ppst.executeUpdate();
				if (a == 0) {
					System.out.println("��ǰ��ȣ�� ���������ʽ��ϴ�.");
					System.out.println("Ȯ���Ͻð� �ٽ� �õ����ּ���.");
				} else {
					System.out.println("��ǰ�����Ϸ�Ǿ����ϴ�.");
				}
			} catch (Exception e) {
				System.out.println("��ǰ���� ���ܹ߻�");
			} finally {
				try {
					if (conn != null)
						conn.close();
					if (ppst != null)
						ppst.close();
				} catch (Exception e2) {
					System.out.println("��ǰ���� �ڿ���ȯ ���ܹ߻�");
				}
			}
		}

	}
	// =================================================================
	public ArrayList<item_DTO> selectAll() { //�����ǰ ���� 
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
					System.out.println("��ϵ� ��ǰ�� �����ϴ�.");
					System.out.println("��ǰ�� ������ּ���.");
				}else {
					while (rs.next()) {
						i.setItemNo(rs.getInt("itemno"));
						i.setItemName(rs.getString("itemname"));
						i.setItemPrice(rs.getInt("itemprice"));
						i.setItemCnt(rs.getInt("itemcnt"));
						list.add(i);
					}
					System.out.println("���� �б� ����");
				}
			} catch (Exception e) {
				System.out.println("��ǰ��Ϻ��� ���ܹ߻�");
			} finally {
				try {
					if (conn != null) conn.close();
					if (st != null) st.close();
				} catch (Exception e2) {
					System.out.println("��ǰ��Ϻ��� �ڿ���ȯ ���ܹ߻�");
				}
			}
		}
		return list;
	}

}
