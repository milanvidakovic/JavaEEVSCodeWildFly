package services;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import services.errors.MyJPAException;
import beans.StudentBean;
import entities.Student;

@Path("/student")
public class StudentService {

	@Inject
	StudentBean studentBean;

	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {
		return studentBean.test();
	}

	@GET
	@Path("/getall")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getStudenti() {
		return studentBean.getStudenti();
	}

	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Student insert(Student s) {
		try {
			return studentBean.insert(s);
		} catch (RuntimeException ex) {
			throw new MyJPAException(ex.getMessage().replace("java.lang.RuntimeException: ", ""));
		}
	}

	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Student update(Student s) {
		try {
			return studentBean.update(s);
		} catch (Exception ex) {
			throw new MyJPAException(ex.getMessage().replace("java.lang.RuntimeException: ", ""));
		}
	}

	@DELETE
	@Path("/delete/{id}")
	public boolean delete(@PathParam("id") long id) {
		return studentBean.delete(id);
	}

}