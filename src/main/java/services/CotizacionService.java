package services;

import db.entidades.Direccion;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Transactional
public class CotizacionService {

    @GET
    @Path("/prueba")
    public String prueba() {
        return "PRUEBA";
    }

    @GET
    @Path("/cotizaciones")
    public Response obtenerCotizaciones()
    {
        return Response.ok(new Direccion()).build();
    }
}
