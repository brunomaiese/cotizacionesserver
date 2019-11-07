package administracion;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import db.daos.CasaCambiariaDao;
import db.daos.DireccionDao;
import db.entidades.CasaCambiaria;
import db.entidades.Direccion;
import org.json.JSONArray;
import org.json.JSONObject;
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

    @EJB
    DireccionDao direccionDao;



    private class Punto {
        private Double latitud;

        private Double longitud;

        public Punto() {
        }

        public Punto(Double latitud, Double longitud) {
            this.latitud = latitud;
            this.longitud = longitud;
        }

        public Double getLatitud() {
            return latitud;
        }

        public void setLatitud(double latitud) {
            this.latitud = latitud;
        }

        public Double getLongitud() {
            return longitud;
        }

        public void setLongitud(Double longitud) {
            this.longitud = longitud;
        }
    }


    @PostConstruct
    public void startupTasks() {
        //cargarDatos();

    }

    private void cargarDatos() {
        System.out.println("INICIALIZANDO DATOS");

        CasaCambiaria cambioSir = new CasaCambiaria("Cambio SIR", 35.70, 37.70, 38.40, 42.3, 0.50, 0.95, 8.00, 9.90);
        CasaCambiaria cambioIndumex = new CasaCambiaria("Cambio Indumex", 36.15, 38.15, 39.90, 42.90, 0.55, 0.85, 8.50, 9.50);
        CasaCambiaria cambioGales = new CasaCambiaria("Cambio Gales", 36.15, 38.15, 39.65, 42.65, 0.40, 0.90, 8.60, 9.60);

        casaCambiariaDao.create(cambioSir);
        casaCambiariaDao.create(cambioIndumex);
        casaCambiariaDao.create(cambioGales);

        Direccion direccionSir1 = new Direccion("Misiones 1374", -34.90741097393889, -56.20602288816894, cambioSir);

        Direccion direccionSir2 = new Direccion("Avda. Arocena 1649", -34.88770860762756,-56.0579997397793,  cambioSir);

        Direccion direccionSir3 = new Direccion("Avda. Rivera 2348",-34.90224288885414, -56.164551442554746,  cambioSir);

        Direccion direccionSir4 = new Direccion("Avda. Agraciada 4176", -34.85675384729447,-56.21937807757043,  cambioSir);

        Direccion direccionSir5 = new Direccion("Colonia 760", -34.90559382050233,-56.19957689799749,  cambioSir);

        Direccion direccionSir6 = new Direccion("Palmar 2311", -34.9008991337404,-56.16500070003673,  cambioSir);

        Direccion direccionSir7 = new Direccion("Avda. Brasil 2954",-34.91157829345914, -56.151615734456016,  cambioSir);

        Direccion direccionIndumex1 = new Direccion("Rincon 473",-34.906774339155675, -56.20582739262713,  cambioIndumex);

        Direccion direccionIndumex2 = new Direccion("Luis A.Herrera 1290",  -34.9040970431828,-56.13725499405635, cambioIndumex);

        Direccion direccionIndumex3 = new Direccion("Luis A.Herrera 3365",-34.86881111427584, -56.16891195967197,  cambioIndumex);

        Direccion direccionIndumex4 = new Direccion("Avda Italia 5775", -34.8824435133862,-56.082756232738184,  cambioIndumex);

        Direccion direccionIndumex5 = new Direccion("Acevedo Díaz 1785", -34.89413394139804,-56.16754604551614,  cambioIndumex);

        Direccion direccionIndumex6 = new Direccion("Acevedo Díaz 1785",-34.89430948218002, -56.1652019867311,  cambioIndumex);

        Direccion direccionGales1 = new Direccion("Luis A. Herrera 1248", -34.904309450383465,-56.13709712672926,  cambioGales);

        Direccion direccionGales2 = new Direccion("Av. Americas 6000", -34.797236999999974,-56.14696499999994,  cambioGales);

        Direccion direccionGales3 = new Direccion("Juan Carlos Patrón 1804",-34.88579506605964, -56.17935352385334,  cambioGales);

        Direccion direccionGales4 = new Direccion("Av Arocena 1625",-34.888256389970714, -56.05771902882636,  cambioGales);

        Direccion direccionGales5 = new Direccion("18 de julio 1046", -34.906203880434134,-56.19383961871135,  cambioGales);

        Direccion direccionGales6 = new Direccion("Rincon 483", -34.90665589381983,-56.20557163231187,  cambioGales);

        Direccion direccionGales7 = new Direccion("18 de julio 1297", -34.905559126916216,-56.18821487228704,  cambioGales);

        Direccion direccionGales8 = new Direccion("Luis A. de Herrera 1290",-34.9040970431828, -56.13725499405635,  cambioGales);

        Direccion direccionGales9 = new Direccion("Jose Maria Guerra 3540",-34.84416799999885, -56.13973000000002,  cambioGales);

        direccionDao.create(direccionSir1);
        direccionDao.create(direccionSir2);
        direccionDao.create(direccionSir3);
        direccionDao.create(direccionSir4);
        direccionDao.create(direccionSir5);
        direccionDao.create(direccionSir6);
        direccionDao.create(direccionSir7);

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

    @Schedule(hour = "1", minute = "*", persistent = false, info = "Obtencion de cambios cada 15 minutos")
    public void obtenerCotizacionesSIR() throws IOException {
        try {
            System.out.println("OBTENIENDO COTIZACIONES");
            // load page using HTML Unit and fire scripts
            //C:\Users\brunom.ANC\Desktop\chromedriver_win32
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\diegoi\\Desktop\\chromedriver_win32\\chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            driver.get("https://www.cambiosir.com.uy");

            Document doc = Jsoup.parse(driver.getPageSource());

            Element iframe = doc.select("iframe").first();
            String iframeSrc = iframe.attr("src");
            if (iframeSrc != null) {

                CasaCambiaria cambioSir = casaCambiariaDao.findSingleResultByField("nombre", "Cambio SIR");

                WebDriver driver2 = new ChromeDriver();

                driver2.get(iframeSrc);
                Document iframeContentDoc = Jsoup.parse(driver2.getPageSource());
                Element table = iframeContentDoc.select("#theTable").first();
                List<Element> rows = table.select("tr");

                for (Element e : rows) {
                    //TODO recorrer y guardar las cotizaciones
                    List<Element> valores =  e.select("td");

                    if (valores.size() == 3) {
                        Element monedaHTML = valores.get(0);
                        Element compraHTML = valores.get(1);
                        Element ventaHTML = valores.get(2);

                        String moneda = monedaHTML.text();
                        String compra = compraHTML.text();
                        String venta = ventaHTML.text();

                        if (moneda.equals("DÓLAR"))
                        {
                            cambioSir.setDolarCompra(Double.parseDouble(compra));
                            cambioSir.setDolarVenta(Double.parseDouble(venta));
                        }
                        else if ( moneda.equals("ARGENTINO"))
                        {
                            cambioSir.setArgentinoCompra(Double.parseDouble(compra));
                            cambioSir.setArgentinoVenta(Double.parseDouble(venta));
                        }
                        else if (moneda.equals("REAL"))
                        {
                            cambioSir.setRealCompra(Double.parseDouble(compra));
                            cambioSir.setRealVenta(Double.parseDouble(venta));
                        }
                        else if (moneda.equals("EURO"))
                        {
                            cambioSir.setEuroCompra(Double.parseDouble(compra));
                            cambioSir.setEuroVenta(Double.parseDouble(venta));
                        }
                    }
                }

                casaCambiariaDao.merge(cambioSir);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Schedule(hour = "1", minute = "*", persistent = false, info = "Obtencion de cambios cada 15 minutos")
    public void obtenerCotizacionesIndumex() throws IOException {
        try {
            System.out.println("OBTENIENDO COTIZACIONES");
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\diegoi\\Desktop\\chromedriver_win32\\chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            driver.get("https://www.indumex.com/");

            Document doc = Jsoup.parse(driver.getPageSource());
            String compraDolar = doc.select("#compraDolar").text();
            String ventaDolar = doc.select("#ventaDolar").text();
            String compraArg = doc.select("#compraArg").text();
            String ventaArg = doc.select("#ventaArg").text();
            String compraReal = doc.select("#compraReal").text();
            String ventaReal = doc.select("#ventaReal").text();
            String compraEuro = doc.select("#compraEuro").text();
            String ventaEuro = doc.select("#ventaEuro").text();

            CasaCambiaria cambioIndumex = casaCambiariaDao.findSingleResultByField("nombre", "Cambio Indumex");

            cambioIndumex.setDolarCompra(Double.parseDouble(compraDolar));
            cambioIndumex.setDolarVenta(Double.parseDouble(ventaDolar));

            cambioIndumex.setArgentinoCompra(Double.parseDouble(compraArg));
            cambioIndumex.setArgentinoVenta(Double.parseDouble(ventaArg));

            cambioIndumex.setRealCompra(Double.parseDouble(compraReal));
            cambioIndumex.setRealVenta(Double.parseDouble(ventaReal));

            cambioIndumex.setEuroCompra(Double.parseDouble(compraEuro));
            cambioIndumex.setEuroVenta(Double.parseDouble(ventaEuro));

            casaCambiariaDao.merge(cambioIndumex);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Schedule(hour = "1", minute = "*", persistent = false, info = "Obtencion de cambios cada 15 minutos")
    public void obtenerCotizacionesGales() throws IOException {
        try {
            System.out.println("OBTENIENDO COTIZACIONES");
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\diegoi\\Desktop\\chromedriver_win32\\chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            driver.get("http://www.gales.com.uy/home/");

            Document doc = Jsoup.parse(driver.getPageSource());
            List<Element> valores = doc.select("table").select("tr").select("td");

            CasaCambiaria cambioGales = casaCambiariaDao.findSingleResultByField("nombre", "Cambio Gales");

            String compraDolar = valores.get(1).text().replace(',', '.');
            String ventaDolar = valores.get(2).text().replace(',', '.');
            String compraArg =  valores.get(4).text().replace(',', '.');
            String ventaArg =  valores.get(5).text().replace(',', '.');
            String compraReal =  valores.get(7).text().replace(',', '.');
            String ventaReal =  valores.get(8).text().replace(',', '.');
            String compraEuro =  valores.get(10).text().replace(',', '.');;
            String ventaEuro =  valores.get(11).text().replace(',', '.');;

            cambioGales.setDolarCompra(Double.parseDouble(compraDolar));
            cambioGales.setDolarVenta(Double.parseDouble(ventaDolar));

            cambioGales.setArgentinoCompra(Double.parseDouble(compraArg));
            cambioGales.setArgentinoVenta(Double.parseDouble(ventaArg));

            cambioGales.setRealCompra(Double.parseDouble(compraReal));
            cambioGales.setRealVenta(Double.parseDouble(ventaReal));

            cambioGales.setEuroCompra(Double.parseDouble(compraEuro));
            cambioGales.setEuroVenta(Double.parseDouble(ventaEuro));

            casaCambiariaDao.merge(cambioGales);
            //TODO recorrer y guardar las cotizaciones
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
