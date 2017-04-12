package br.gov.sp.fatec.model;

import org.springframework.beans.factory.annotation.Autowired;

import br.gov.sp.fatec.loja.Funcionario;
import br.gov.sp.fatec.service.LoginImpl;

public class Login {
		
	@Autowired
	private LoginImpl loginImpl;
	@Autowired
	private MenuFunc menuFunc;
	@Autowired
	private MenuAdmin menuAdmin;
	private Funcionario func;
	
	public void setMenuFunc(MenuFunc menuFunc){
		this.menuFunc = menuFunc;
	}
	
	public void setMenuAdmin(MenuAdmin menuAdmin){
		this.menuAdmin = menuAdmin;
	}
	
	public void setLoginImpl(LoginImpl loginImpl){
		this.loginImpl = loginImpl;
	}
	
	public void setFunc(Funcionario func){
		this.func = func;
	}
	
	public Funcionario getFunc(){
		return this.func;
	}
	
	public boolean getMenu(){
		
		if (func.getCargo().equals("ADMIN")){
			return this.menuAdmin.menuAdmin(this.func);
		}
		else if (func.getCargo().equals("FUNCIONARIO")){
			return this.menuFunc.menuFunc(this.func);
			
		}
		return false; 
	
	}
	
	public boolean login(long id, String senha){
			
		if (loginImpl.login(id, senha)){
			
				setFunc(loginImpl.getFunc(id));
				System.out.println("Login permitido");
				return true;
			}
			
			else{
				System.out.println("Login e senha incorreto!!!");
				return false;
			}		
	}
}
