package br.com.caelum.livraria.tx;

import java.io.Serializable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@SuppressWarnings("serial")
@Log
@Interceptor
public class TempoDeExecucaoInterceptor implements Serializable {

	@AroundInvoke
	public Object executaLog(InvocationContext contexto) throws Exception {

		long inicio = System.currentTimeMillis();

		String metodo = contexto.getMethod().getName();

		Object proceder = contexto.proceed();

		long fim = System.currentTimeMillis();

		long resultado = fim - inicio;

		System.out.println("M�todo executado: " + metodo + " Tempo execu��o: " + resultado);

		return proceder;

	}

}
