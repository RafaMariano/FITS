package br.gov.sp.fatec;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.gov.sp.fatec.model.Login;


public class App {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			
		Login log = (Login) context.getBean("login");
		boolean whi = true;
		boolean logado = false;

			while (whi) {
				if (logado == false) { 
					Scanner sc1 = new Scanner(System.in);
									
					System.out.println("Login - Digite o ID do funcionario:");
					long id = sc1.nextLong();
						
					System.out.println("Digite a senha do funcionario");	
					String senha = sc1.next();
					
					if (log.login(id, senha)) {
						logado = true; 
						logado = log.getMenu();
					} 
			} else {
				logado = log.getMenu();
			} 

	}
	}
}
