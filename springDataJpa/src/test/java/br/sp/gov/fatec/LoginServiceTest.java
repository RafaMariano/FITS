package br.sp.gov.fatec;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.loja.Funcionario;
import br.gov.sp.fatec.service.LoginImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/testContext.xml" })
@Rollback
@Transactional
public class LoginServiceTest {

	private static long id = 4;
	private static String senha = "111";
	@Autowired
	private LoginImpl loginImpl;
	
	
	public void setLoginImpl(LoginImpl loginImpl){
		this.loginImpl = loginImpl;
	}
	
	@Test
	public void loginTrue(){
		assertTrue(this.loginImpl.login(id, senha));
	}
	
	@Test
	public void loginFalse(){
		assertTrue(this.loginImpl.login(id, "222") == false);
	}
	
	@Test
	public void getFuncionario(){
		Funcionario funcDb = this.loginImpl.getFunc(id);
		
		assertTrue(funcDb.getNome().equals("Samuel"));
		assertTrue(funcDb.getSenha().equals("111"));
		assertTrue(funcDb.getCargo().equals("FUNCIONARIO"));
	}
}
