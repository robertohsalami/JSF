package br.com.caelum.livraria.tx;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@SuppressWarnings("serial")
@Transacional
@Interceptor
public class GerenciadorDeTransacao implements Serializable{
	
	@Inject
	EntityManager manager;
	
	@AroundInvoke
	public Object executaTX(InvocationContext contexto) throws Exception {
		// abre transacao
		manager.getTransaction().begin();
		
		//chamar daos que precisam de uma transação
		Object resultado = contexto.proceed();
		
		// commita a transacao
		manager.getTransaction().commit();
		
		return resultado;

	}

}
