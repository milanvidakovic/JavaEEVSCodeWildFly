package services;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.NaseljenoMestoBean;
import entities.NaseljenoMesto;

@Path("/mesto")
public class NaseljenoMestoService {
    @Inject
    NaseljenoMestoBean naseljenoMestoBean;

	@GET
	@Path("/getall")
	@Produces(MediaType.APPLICATION_JSON)
	public List<NaseljenoMesto> getMesto(){
		return naseljenoMestoBean.getMesto();
	}

}