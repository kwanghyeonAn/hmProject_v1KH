package Interface;

public interface user_INF {

	// ====================계정관련=======================

	public void membership(); // 회원가입
	
	public int login();  // 로그인

	public void pwdupdate(); // 비밀번호변경

	public void delete(); // 계정삭제

	public void infoselect(); // 계정정보조회

	// ====================장바구니관련=====================

	public void buylistselect(); // 고객 장바구니 확인
	
	public void buyitemdelete(); // 고객 장바구니 아이템 삭제

	public void buyitem(); // 고객 장바구니 아이템담기

	public void buyitempudate(); // 고객 장바구니 아이템 수량변경

	// ====================구매목록관련=====================

	public void itemlist(); // 고객 구매목록조회
	
}
