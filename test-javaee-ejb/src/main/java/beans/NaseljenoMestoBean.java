package beans;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.NaseljenoMesto;

public class NaseljenoMestoBean {

    @PersistenceContext
	EntityManager em;

    @SuppressWarnings("unchecked")
    public List<NaseljenoMesto> getMesto(){
		return em.createQuery("select m from NaseljenoMesto m").getResultList();
	}
}