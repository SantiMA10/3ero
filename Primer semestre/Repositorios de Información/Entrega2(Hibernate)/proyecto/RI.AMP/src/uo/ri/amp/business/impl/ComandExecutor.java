package uo.ri.amp.business.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import uo.ri.amp.model.exception.BusinessException;
import uo.ri.amp.persistence.util.Jpa;

public class ComandExecutor {

	public Object execute(Comand comand) throws BusinessException {

		EntityManager em = Jpa.createEntityManager();
		EntityTransaction trx = em.getTransaction();

		try {
			trx.begin();
		} catch (PersistenceException e) {
			throw new BusinessException("Base de datos apagada.");
		}

		Object res = null;

		try {

			res = comand.execute();

			trx.commit();

		} catch (BusinessException | RuntimeException e) {

			if (trx.isActive())
				trx.rollback();
			throw e;

		} finally {

			if (em.isOpen())
				em.close();

		}

		return res;
	}

}
