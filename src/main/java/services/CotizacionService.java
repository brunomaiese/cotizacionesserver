package services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import db.entidades.Direccion;
import administracion.StartupBean;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.mashape.unirest.http.Unirest;

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
    public Response obtenerCotizaciones(double longitud, double latitud) throws UnirestException {
        StartupBean administracion = new StartupBean();

        JSONObject puntos = administracion.obtenerPuntosConCotizaciones(longitud, latitud);

        return Response.ok(puntos, APPLICATION_JSON).build();
    }
}
