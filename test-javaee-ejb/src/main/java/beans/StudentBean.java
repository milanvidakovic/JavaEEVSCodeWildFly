package beans;

import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import entities.Student;

@Singleton
@LocalBean
public class StudentBean {

	@PersistenceContext
	EntityManager em;

	public String test() {
		System.out.println("StudentBean.test()");
		return "OK";
	}

	@SuppressWarnings("unchecked")
	public List<Student> getStudenti() {
		return em.createQuery("select s from Student s").getResultList();
	}

	public Student insert(Student s) {
		System.out.println("save student: " + s);
		if (s.getId() == 0)
			throw new RuntimeException("Student nema dobar ID");
		try {
			em.persist(s);
			em.flush();
		} catch (RuntimeException ex) {
			StringBuilder sb = new StringBuilder();
			Throwable e = ex;
			while (e != null) {
				if (e instanceof ConstraintViolationException) {
					ConstraintViolationException cve = (ConstraintViolationException) e;
					Set<ConstraintViolation<?>> errors = cve.getConstraintViolations();
					for (ConstraintViolation<?> error : errors) {
						System.out.println(">>>>>" + error.getClass() + ": " + error.getMessage());
						if (!error.getMessage().contains("could not execute statement")) {
							sb.append(error.getMessage() + "\n");
						}
					}
				} else {
					System.out.println("<<<<<<<<<<<<" + e.getClass() + ": " + e.getMessage());
					if (!e.getMessage().contains("could not execute statement")) {
						sb.append(e.getMessage() + "\n");
					}
				}
				e = e.getCause();
			}
			throw new RuntimeException(sb.toString());
		}
		return s;
	}

	public Student update(Student s) {
		System.out.println("update student: " + s);
		try {
			s = em.merge(s);
			em.flush();
			return s;
		} catch (RuntimeException ex) {
			StringBuilder sb = new StringBuilder();
			Throwable e = ex;
			while (e != null) {
				if (e instanceof ConstraintViolationException) {
					ConstraintViolationException cve = (ConstraintViolationException) e;
					Set<ConstraintViolation<?>> errors = cve.getConstraintViolations();
					for (ConstraintViolation<?> error : errors) {
						sb.append(error.getMessage() + "\n");
					}
				} else {
					sb.append(e.getMessage() + "\n");
				}
				e = e.getCause();
			}
			throw new RuntimeException(sb.toString());
		}
	}

	public boolean delete(long id) {
		Student s = em.find(Student.class, id);
		em.remove(s);
		return true;
	}

}