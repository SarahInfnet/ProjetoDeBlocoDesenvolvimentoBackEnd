package controller.rotas;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import model.Bike;
import view.AluguelFinalizacaoView;

import java.io.IOException;

public class AluguelFinalizacaoRota {
    public static String rota = "/aluguel/finalizaAluguel";

    public static HttpHandler tratadorRota = new TratadorRota();

    static class TratadorRota implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            String rota = t.getRequestURI().getPath();
            String[] elementosRota = rota.split("/");
            int numBike = Integer.parseInt(elementosRota[3]);
            int numberOfDays = Integer.parseInt(elementosRota[5]);
            int numberOfBikes = Integer.parseInt(elementosRota[4]);
            Bike bicicleta = Bike.findBikeByNumber(numBike);
            String descricao = bicicleta.getDescription();
            int custo = bicicleta.calculateCost(numberOfDays) * numberOfBikes;
            AluguelFinalizacaoView.atualizaInterfaceUsuario(bicicleta, numberOfDays, numberOfBikes, custo, t);
        }
    }
}