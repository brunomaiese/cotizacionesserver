package administracion;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import db.daos.CasaCambiariaDao;
import db.daos.DireccionDao;
import db.entidades.Direccion;
import dtypes.RespuestaCotizaciones;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class CotizacionesBL {

    @EJB
    CasaCambiariaDao casaCambiariaDao;

    @EJB
    DireccionDao direccionDao;

    public RespuestaCotizaciones obtenerPuntosConCotizaciones(double longitud, double latitud) throws UnirestException {
        //List<Direccion> direcciones = direccionDao.findAll();

        List<Direccion> direcciones = new LinkedList<Direccion>();

        Direccion dirSiR =  direccionDao.findSingleResultByField("direccion","Misiones 1374");
        Direccion dirIndumex = direccionDao.findSingleResultByField("direccion","Rincon 473");
        Direccion dirGales =  direccionDao.findSingleResultByField("direccion","Rincon 483");

        direcciones.add(dirSiR);
        direcciones.add(dirGales);
        direcciones.add(dirIndumex);

        final String URL = "http://router.project-osrm.org/route/v1/driving/";
        final String PARAMETERS = "?overview=false";

        double distanciaSir = Double.MAX_VALUE;
        double distanciaIndumex = Double.MAX_VALUE;
        double distanciaGales = Double.MAX_VALUE;

        Direccion direccionSir = new Direccion();
        Direccion direccionIndumex = new Direccion();;
        Direccion direccionGales = new Direccion();;

        for (Direccion dir : direcciones) {

            String uri = URL + longitud + "," + latitud + ";" + dir.getLongitud() + "," + dir.getLatitud() + PARAMETERS;
            HttpResponse<String> response = Unirest.get(uri).header("cache-control", "no-cache").asString();
            JSONObject json = new JSONObject(response.getBody());
            JSONArray results = json.getJSONArray("routes");
            JSONObject array = results.getJSONObject(0);

            double distancia = array.getDouble("distance");

            String casaCambiaria = dir.getCasaCambiaria().getNombre();

            if (casaCambiaria.equals("Cambio SIR"))
            {
                if (distancia < distanciaSir)
                {
                    distanciaSir = distancia;
                    direccionSir = dir;
                }
            }
            else if (casaCambiaria.equals("Cambio Indumex"))
            {
                if (distancia < distanciaIndumex)
                {
                    distanciaIndumex = distancia;
                    direccionIndumex = dir;
                }
            }
            else
            {
                if (distancia < distanciaGales)
                {
                    distanciaGales = distancia;
                    direccionGales = dir;
                }
            }

        }

        RespuestaCotizaciones respuestaCotizaciones= new RespuestaCotizaciones();
      //  respuestaCotizaciones.setDireccionGales(direccionGales);
       // respuestaCotizaciones.setDireccionIndumex(direccionIndumex);
        //respuestaCotizaciones.setDireccionSir(direccionSir);
        respuestaCotizaciones.setDistanciaGales(distanciaGales);
        respuestaCotizaciones.setDistanciaIndumex(distanciaIndumex);
        respuestaCotizaciones.setDistanciaSir(distanciaSir);
/*

        RespuestaCotizaciones respuestaCotizaciones = new RespuestaCotizaciones();
        respuestaCotizaciones.setDistanciaGales(500);
        respuestaCotizaciones.setDistanciaIndumex(300);
        respuestaCotizaciones.setDistanciaSir(200);*/
        return respuestaCotizaciones;
    }
}
