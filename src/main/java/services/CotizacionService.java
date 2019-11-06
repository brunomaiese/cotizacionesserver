package services;

import administracion.CotizacionesBL;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import db.entidades.Direccion;
import administracion.StartupBean;
import dtypes.RespuestaCotizaciones;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ejb.EJB;
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

    @EJB
    CotizacionesBL cotizacionesBL;

    @GET
    @Path("/prueba")
    public String prueba() {
        return "PRUEBA";
    }

    @GET
    @Path("/cotizaciones")
    public RespuestaCotizaciones obtenerCotizaciones() throws UnirestException {

        double latitud= -34.9185225;
        double longitud = -56.1806222;
        RespuestaCotizaciones puntos = cotizacionesBL.obtenerPuntosConCotizaciones(longitud, latitud);
        return puntos;
    }


}
