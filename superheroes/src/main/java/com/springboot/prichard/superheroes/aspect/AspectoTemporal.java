package com.springboot.prichard.superheroes.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.springboot.prichard.superheroes.SuperheroesApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class AspectoTemporal {

	private static final Logger LOGGER = LoggerFactory.getLogger(SuperheroesApplication.class);

	@Around("execution(* com.springboot.prichard.superheroes.service.*.*(..))")

	public Object calculoTiempo(ProceedingJoinPoint joinPoint) throws Throwable {

		long inicio = System.currentTimeMillis();
		// Obtener el registrador de destino

		Object resultado = joinPoint.proceed();

		long fin = System.currentTimeMillis();

		long tiempoProcesamiento = fin - inicio;

		String tiempo = String.valueOf(tiempoProcesamiento);

		System.out.println("Tiempo de procesamiento:" + joinPoint.getTarget().getClass() + "."
				+ joinPoint.getSignature().getName() + ":" + tiempo);
		LOGGER.info("{}: {}: : end... cost time: {} ms", joinPoint.getTarget().getClass(), joinPoint.getSignature(),
				tiempo);

		return resultado;

	}

}
