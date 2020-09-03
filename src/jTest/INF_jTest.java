package jTest;

import org.junit.Before;
import org.junit.Test;

import Interface.user_INFIpl;

public class INF_jTest {
	
	user_INFIpl u_inf = null;
	
	@Before
	public void u() {
		u_inf = user_INFIpl.u();
	}
	
	@Test
	public void membership() {
		u_inf.membership();
	}
	
	
	
}
