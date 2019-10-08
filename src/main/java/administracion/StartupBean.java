package administracion;

import com.fasterxml.jackson.databind.util.JSONPObject;
import db.daos.CasaCambiariaDao;
import db.daos.DireccionDao;
import db.entidades.CasaCambiaria;
import db.entidades.Direccion;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.json.Json;
import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Startup
@Singleton
public class StartupBean {

    @EJB
    CasaCambiariaDao casaCambiariaDao;

    @EJB
    DireccionDao direccionDao;

    private class Punto
    {
        private Double latitud;

        private Double longitud;

        public Punto(){}

        public Punto(Double latitud, Double longitud)
        {
            this.latitud = latitud;
            this.longitud = longitud;
        }

        public Double getLatitud() {
            return latitud;
        }

        public void setLatitud(double latitud) {
            this.latitud = latitud;
        }

        public Double getLongitud() { return longitud; }

        public void setLongitud(Double longitud) {
            this.longitud = longitud;
        }
    }


    @PostConstruct
    public void startupTasks() {

        System.out.println("INICIALIZANDO DATOS");

        CasaCambiaria cambioSir = new CasaCambiaria("Cambio SIR", 35.70, 37.70, 38.40, 42.3, 0.50, 0.95, 8.00, 9.90, "https://www.cambiosir.com.uy/", "#niidea");
        CasaCambiaria cambioIndumex = new CasaCambiaria("Cambio Indumex", 36.15, 38.15, 39.90, 42.90, 0.55, 0.85, 8.50, 9.50, "https://www.indumex.com/", "#niidea");
        CasaCambiaria cambioGales = new CasaCambiaria("Cambio Gales", 36.15, 38.15, 39.65, 42.65, 0.40, 0.90, 8.60, 9.60, "http://www.gales.com.uy/home/", "#niidea");

        casaCambiariaDao.create(cambioSir);
        casaCambiariaDao.create(cambioIndumex);
        casaCambiariaDao.create(cambioGales);

        Punto puntoSir1 = ObtenerCoordenadas("\'Misiones 1374\'");
        Direccion direccionSir1 = new Direccion("Misiones 1374", puntoSir1.latitud, puntoSir1.longitud, cambioSir);

        Punto puntoSir2 = ObtenerCoordenadas("\'Avda. Arocena 1649\'");
        Direccion direccionSir2 = new Direccion("Avda. Arocena 1649", puntoSir2.latitud, puntoSir2.longitud, cambioSir);

        Punto puntoSir3 = ObtenerCoordenadas("\'AV GRAL RIVERA 2348\'");
        Direccion direccionSir3 = new Direccion("Avda. Rivera 2348", puntoSir3.latitud, puntoSir3.longitud, cambioSir);

        Punto puntoSir4 = ObtenerCoordenadas("\'Avda. Agraciada 4176\'");
        Direccion direccionSir4 = new Direccion("Avda. Agraciada 4176", puntoSir4.latitud, puntoSir4.longitud, cambioSir);

        Punto puntoSir5 = ObtenerCoordenadas("\'Colonia 760\'");
        Direccion direccionSir5 = new Direccion("Colonia 760", puntoSir5.latitud, puntoSir5.longitud, cambioSir);

        Punto puntoSir6 = ObtenerCoordenadas("\'Palmar 2311\'");
        Direccion direccionSir6 = new Direccion("Palmar 2311", puntoSir6.latitud, puntoSir6.longitud, cambioSir);

        Punto puntoIndumex1 = ObtenerCoordenadas("\'Rincon 473\'");
        Direccion direccionIndumex1 = new Direccion("Rincon 473", puntoIndumex1.latitud, puntoIndumex1.longitud, cambioIndumex);

        Punto puntoIndumex2 = ObtenerCoordenadas("\'Luis A.Herrera 1290\'");
        Direccion direccionIndumex2 = new Direccion("Luis A.Herrera 1290", puntoIndumex2.latitud, puntoIndumex2.longitud, cambioIndumex);

        Punto puntoIndumex3 = ObtenerCoordenadas("\'Luis A.Herrera 3365\'");
        Direccion direccionIndumex3 = new Direccion("Luis A.Herrera 3365", puntoIndumex3.latitud, puntoIndumex3.longitud, cambioIndumex);

        Punto puntoIndumex4 = ObtenerCoordenadas("\'Avda Italia 5775'");
        Direccion direccionIndumex4 = new Direccion("Avda Italia 5775", puntoIndumex4.latitud, puntoIndumex4.longitud, cambioIndumex);

        Punto puntoIndumex5 = ObtenerCoordenadas("\'Bulevar General Artigas+1825\'");
        Direccion direccionIndumex5 = new Direccion("Bulevar General Artigas 1825", puntoIndumex5.latitud, puntoIndumex5.longitud, cambioIndumex);

        direccionDao.create(direccionSir1);
        direccionDao.create(direccionSir2);
        direccionDao.create(direccionSir3);
        direccionDao.create(direccionSir4);
        direccionDao.create(direccionSir5);
        direccionDao.create(direccionSir6);
        direccionDao.create(direccionIndumex1);
        direccionDao.create(direccionIndumex2);
        direccionDao.create(direccionIndumex3);
        direccionDao.create(direccionIndumex4);
        direccionDao.create(direccionIndumex5);



    }

    public Punto ObtenerCoordenadas(String direccion)
    {
        String url = "http://geo.correo.com.uy/servicios/BusquedaDireccion";
        Punto punto = null;
        try {
            URL obj = new URL(url);
            Map<String, Object> parametros = new LinkedHashMap<String, Object>();

            parametros.put("Departamento", "Montevideo");
            parametros.put("Localidad", "Montevideo");
            parametros.put("direccion", direccion);

            StringBuilder postData = new StringBuilder();

            for (Map.Entry<String, Object> parametro : parametros.entrySet())
            {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(parametro.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(parametro.getValue()), "UTF-8"));
            }

            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            con.setDoOutput(true);
            con.getOutputStream().write(postDataBytes);
            int responseCode = con.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String json = response.toString();
            json = json.substring(1, json.length() - 1);
            JSONObject puntoJSon = new JSONObject(json);

            Double latitud = puntoJSon.getDouble("puntoX");
            Double longitud = puntoJSon.getDouble("puntoY");

            punto = new Punto(latitud, longitud);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            return punto;
        }

    }

    @Schedule(hour = "*", minute = "0,15,30,45", persistent = false, info = "Obtencion de cambios cada 15 minutos")
    public void obtenerCotizaciones() throws IOException {
        System.out.println("OBTENIENDO COTIZACIONES");
        //OBTIENE LA SECCION In the news de wikipedia
        Document doc = Jsoup.connect("http://en.wikipedia.org/").get();
        Elements newsHeadlines = doc.select("#mp-itn b a");
        for (Element headline : newsHeadlines) {
            System.out.println(headline.toString());
        }
    }
}
