package Interface;

public interface user_INF {

	// ====================��������=======================

	public void membership(); // ȸ������
	
	public int login();  // �α���

	public void pwdupdate(); // ��й�ȣ����

	public void delete(); // ��������

	public void infoselect(); // ����������ȸ

	// ====================��ٱ��ϰ���=====================

	public void buylistselect(); // �� ��ٱ��� Ȯ��
	
	public void buyitemdelete(); // �� ��ٱ��� ������ ����

	public void buyitem(); // �� ��ٱ��� �����۴��

	public void buyitempudate(); // �� ��ٱ��� ������ ��������

	// ====================���Ÿ�ϰ���=====================

	public void itemlist(); // �� ���Ÿ����ȸ
	
}
