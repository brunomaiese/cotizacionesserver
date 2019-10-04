package administracion;

import com.fasterxml.jackson.databind.util.JSONPObject;
import db.daos.CasaCambiariaDao;
import db.daos.DireccionDao;
import db.entidades.CasaCambiaria;
import db.entidades.Direccion;
import javafx.util.Pair;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Startup
@Singleton
public class StartupBean {

    @EJB
    CasaCambiariaDao casaCambiariaDao;

    @EJB
    DireccionDao direccionDao;

    @PostConstruct
    public void startupTasks() {
        ObtenerCoordenadas("\'Buenos aires 451\'");
        System.out.println("INICIALIZANDO DATOS");

        CasaCambiaria cambioSir = new CasaCambiaria("Cambio SIR", 35.70, 37.70, 38.40, 42.3, 0.50, 0.95, 8.00, 9.90, "https://www.cambiosir.com.uy/", "#niidea");
        CasaCambiaria cambioIndumex = new CasaCambiaria("Cambio Indumex", 36.15, 38.15, 39.90, 42.90, 0.55, 0.85, 8.50, 9.50, "https://www.indumex.com/", "#niidea");
        CasaCambiaria cambioGales = new CasaCambiaria("Cambio Gales", 36.15, 38.15, 39.65, 42.65, 0.40, 0.90, 8.60, 9.60, "http://www.gales.com.uy/home/", "#niidea");

        casaCambiariaDao.create(cambioSir);
        casaCambiariaDao.create(cambioIndumex);
        casaCambiariaDao.create(cambioGales);



    }

    public Double ObtenerCoordenadas(String direccion)
    {
        String url = "http://geo.correo.com.uy/serviciosv2/BusquedaDireccion";
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
            JSONObject punto = new JSONObject(json);

            String a = punto.toString();


        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return 0.0;
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
