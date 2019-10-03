package administracion;

import db.daos.CasaCambiariaDao;
import db.entidades.CasaCambiaria;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import java.io.IOException;

@Startup
@Singleton
public class StartupBean {

    @PostConstruct
    public void startupTasks() {
        System.out.println("INICIALIZANDO DATOS");

        CasaCambiariaDao casaCambiariaDao = new CasaCambiariaDao();

        CasaCambiaria cambioSir = new CasaCambiaria("Cambio SIR", 35.70, 37.70, 38.40, 42.3, 0.50, 0.95, 8.00, 9.90, "https://www.cambiosir.com.uy/", "#niidea");
        CasaCambiaria cambioIndumex = new CasaCambiaria("Cambio Indumex", 36.15, 38.15, 39.90, 42.90, 0.55, 0.85, 8.50, 9.50, "https://www.indumex.com/", "#niidea");
        CasaCambiaria cambioGales = new CasaCambiaria("Cambio Gales", 36.15, 38.15, 39.65, 42.65, 0.40, 0.90, 8.60, 9.60, "http://www.gales.com.uy/home/", "#niidea");

        casaCambiariaDao.create(cambioSir);
        casaCambiariaDao.create(cambioIndumex);
        casaCambiariaDao.create(cambioGales);

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
