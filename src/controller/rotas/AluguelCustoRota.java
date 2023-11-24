package controller.rotas;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import model.Bike;
import view.AluguelCustoView;

import java.io.IOException;

public class AluguelCustoRota {
    public static String rota  = "/aluguel/custo";

    public static HttpHandler tratadorRota = new TratadorRota();

    static class TratadorRota implements HttpHandler{
        public void handle(HttpExchange t) throws IOException {
            String rota = t.getRequestURI().getPath();
            String[] elementosRota = rota.split("/");
            int numBike = Integer.parseInt(elementosRota[3]);
            int numberOfDays = Integer.parseInt(elementosRota[5]);
            Bike bicicleta = Bike.findBikeByNumber(numBike);
            int numberOfBikes = Integer.parseInt(elementosRota[4]);
            int custo = bicicleta.calculateCost(numberOfDays) * numberOfBikes;
            AluguelCustoView.enviaCusto(custo, t);
        }
    }
}
