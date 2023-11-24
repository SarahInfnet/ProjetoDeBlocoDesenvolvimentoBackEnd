package controller.rotas;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import model.Bike;
import model.Customer;
import model.Hire;
import model.Payment;
import view.PagamentoConsultaView;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

public class PagamentoConsultaRota {

    public static String rota = "/pagamento/consulta";

    public static HttpHandler tratadorRota = new TratadorRota();

    static class TratadorRota implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            String rota = t.getRequestURI().getPath();
            String[] rotaElementos = rota.split("/");
            String nome = rotaElementos[6];
            long tel = Long.parseLong(rotaElementos[7]);
            String cep = rotaElementos[8];
            Customer cliente = new Customer(nome, cep, tel);
            Payment pagamento = new Payment(cliente);
            int numDias = Integer.parseInt(rotaElementos[5]);
            int numBike = Integer.parseInt(rotaElementos[3]);
            Bike bicicleta = Bike.findBikeByNumber(numBike);
            int totalBikes = Integer.parseInt(rotaElementos[4]);
            int custo = bicicleta.calculateCost(numDias) * totalBikes;
            Hire aluguel = new Hire(Date.from(LocalDate.now().atStartOfDay(ZoneOffset.UTC).toInstant()), numDias, bicicleta, cliente);
            PagamentoConsultaView.atualizaInterfaceUsuario(aluguel, custo, totalBikes, t);
        }
    }
}
