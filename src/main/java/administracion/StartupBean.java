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

        CasaCambiaria cambioSir = new CasaCambiaria("Cambio SIR", 35.70, 37.70, 38.40, 42.3, 0.50, 0.95, 8.00, 9.90);
        CasaCambiaria cambioIndumex = new CasaCambiaria("Cambio Indumex", 36.15, 38.15, 39.90, 42.90, 0.55, 0.85, 8.50, 9.50);
        CasaCambiaria cambioGales = new CasaCambiaria("Cambio Gales", 36.15, 38.15, 39.65, 42.65, 0.40, 0.90, 8.60, 9.60);

        casaCambiariaDao.create(cambioSir);
        casaCambiariaDao.create(cambioIndumex);
        casaCambiariaDao.create(cambioGales);

        Direccion direccionSir1 = new Direccion("Misiones 1374", -56.20602288816894, -34.90741097393889, cambioSir);

        Direccion direccionSir2 = new Direccion("Avda. Arocena 1649", -56.0579997397793, -34.88770860762756, cambioSir);

        Direccion direccionSir3 = new Direccion("Avda. Rivera 2348", -56.164551442554746, -34.90224288885414, cambioSir);

        Direccion direccionSir4 = new Direccion("Avda. Agraciada 4176", -56.21937807757043, -34.85675384729447, cambioSir);

        Direccion direccionSir5 = new Direccion("Colonia 760", -56.19957689799749, -34.90559382050233, cambioSir);

        Direccion direccionSir6 = new Direccion("Palmar 2311", -56.16500070003673, -34.9008991337404, cambioSir);

        Direccion direccionSir7 = new Direccion("Avda. Brasil 2954", -56.151615734456016, -34.91157829345914, cambioSir);

        Direccion direccionIndumex1 = new Direccion("Rincon 473", -56.20582739262713, -34.906774339155675, cambioIndumex);

        Direccion direccionIndumex2 = new Direccion("Luis A.Herrera 1290", -56.13725499405635, -34.9040970431828, cambioIndumex);

        Direccion direccionIndumex3 = new Direccion("Luis A.Herrera 3365", -56.16891195967197, -34.86881111427584, cambioIndumex);

        Direccion direccionIndumex4 = new Direccion("Avda Italia 5775", -56.082756232738184, -34.8824435133862, cambioIndumex);

        Direccion direccionIndumex5 = new Direccion("Acevedo Díaz 1785", -56.16754604551614, -34.89413394139804, cambioIndumex);

        Direccion direccionIndumex6 = new Direccion("Acevedo Díaz 1785", -56.1652019867311, -34.89430948218002, cambioIndumex);

        Direccion direccionGales1= new Direccion("Luis A. Herrera 1248", -56.13709712672926, -34.904309450383465, cambioGales);

        Direccion direccionGales2 = new Direccion("Av. Americas 6000", -56.14696499999994, -34.797236999999974, cambioGales);

        Direccion direccionGales3 = new Direccion("Juan Carlos Patrón 1804", -56.17935352385334, -34.88579506605964, cambioGales);

        Direccion direccionGales4 = new Direccion("Av Arocena 1625", -56.05771902882636, -34.888256389970714, cambioGales);

        Direccion direccionGales5 = new Direccion("18 de julio 1046", -56.19383961871135, -34.906203880434134, cambioGales);

        Direccion direccionGales6 = new Direccion("Rincon 483", -56.20557163231187, -34.90665589381983, cambioGales);

        Direccion direccionGales7 = new Direccion("18 de julio 1297", -56.18821487228704, -34.905559126916216, cambioGales);

        Direccion direccionGales8 = new Direccion("Luis A. de Herrera 1290", -56.13725499405635, -34.9040970431828, cambioGales);

        Direccion direccionGales9 = new Direccion("Jose Maria Guerra 3540", -56.13973000000002, -34.84416799999885, cambioGales);

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
        direccionDao.create(direccionIndumex6);

        direccionDao.create(direccionGales1);
        direccionDao.create(direccionGales2);
        direccionDao.create(direccionGales3);
        direccionDao.create(direccionGales4);
        direccionDao.create(direccionGales5);
        direccionDao.create(direccionGales6);
        direccionDao.create(direccionGales7);
        direccionDao.create(direccionGales8);
        direccionDao.create(direccionGales9);

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
