package administracion;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.IOException;

@Startup
@Singleton
public class StartupBean {

    @PostConstruct
    public void startupTasks() {
        System.out.println("INICIALIZANDO DATOS");
    }

    @Schedule(hour = "*", minute = "0,15,30,45", persistent = false, info = "Notificaciones EveryDay 16 y 22 hs")
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
