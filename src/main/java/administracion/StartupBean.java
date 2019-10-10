package administracion;

import db.daos.CasaCambiariaDao;
import db.entidades.CasaCambiaria;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.IOException;
import java.util.List;

@Startup
@Singleton
public class StartupBean {

    @EJB
    CasaCambiariaDao casaCambiariaDao;

    @PostConstruct
    public void startupTasks() {
        System.out.println("INICIALIZANDO DATOS");

        CasaCambiaria cambioSir = new CasaCambiaria("Cambio SIR", 35.70, 37.70, 38.40, 42.3, 0.50, 0.95, 8.00, 9.90, "https://www.cambiosir.com.uy/", "#niidea");
        CasaCambiaria cambioIndumex = new CasaCambiaria("Cambio Indumex", 36.15, 38.15, 39.90, 42.90, 0.55, 0.85, 8.50, 9.50, "https://www.indumex.com/", "#niidea");
        CasaCambiaria cambioGales = new CasaCambiaria("Cambio Gales", 36.15, 38.15, 39.65, 42.65, 0.40, 0.90, 8.60, 9.60, "http://www.gales.com.uy/home/", "#niidea");

        casaCambiariaDao.create(cambioSir);
        casaCambiariaDao.create(cambioIndumex);
        casaCambiariaDao.create(cambioGales);

    }

    @Schedule(hour = "22", minute = "*", persistent = false, info = "Obtencion de cambios cada 15 minutos")
    public void obtenerCotizacionesSIR() throws IOException {
        try {
            System.out.println("OBTENIENDO COTIZACIONES");
            // load page using HTML Unit and fire scripts
            //C:\Users\brunom.ANC\Desktop\chromedriver_win32
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\brunom.ANC\\Desktop\\chromedriver_win32\\chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            driver.get("https://www.cambiosir.com.uy");

            Document doc = Jsoup.parse(driver.getPageSource());

            Element iframe = doc.select("iframe").first();
            String iframeSrc = iframe.attr("src");
            if (iframeSrc != null) {
                WebDriver driver2 = new ChromeDriver();

                driver2.get(iframeSrc);
                Document iframeContentDoc = Jsoup.parse(driver2.getPageSource());
                Element table = iframeContentDoc.select("#theTable").first();
                List<Element> rows = table.select("tr");
                for(Element e :rows){
                    e.select("td");
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Schedule(hour = "*", minute = "*", persistent = false, info = "Obtencion de cambios cada 15 minutos")
    public void obtenerCotizacionesIndumex() throws IOException {
        try {
            System.out.println("OBTENIENDO COTIZACIONES");
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\brunom.ANC\\Desktop\\chromedriver_win32\\chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            driver.get("https://www.indumex.com/");

            Document doc = Jsoup.parse(driver.getPageSource());
            String compraDolar= doc.select("#compraDolar").text();
            String ventaDolar= doc.select("#ventaDolar").text();
            String compraArg=doc.select("#compraArg").text();
            String ventaArg=doc.select("#ventaArg").text();
            String compraReal=doc.select("#compraReal").text();
            String ventaReal=doc.select("#ventaReal").text();
            String compraEuro=doc.select("#compraEuro").text();
            String ventaEuro=doc.select("#ventaEuro").text();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
