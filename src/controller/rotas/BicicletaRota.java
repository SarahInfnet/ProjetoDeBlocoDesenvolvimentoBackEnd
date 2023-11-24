package controller.rotas;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import view.BicicletaDetalhesView;
import view.ListaBicicletasView;
import model.Bike;

import java.io.IOException;

public class BicicletaRota {
    public static String rota = "/bicicleta";
    public static HttpHandler tratadorRota = new TratadorRota();

    static class TratadorRota implements HttpHandler{
        public void handle(HttpExchange t) throws IOException {
            String rota = t.getRequestURI().getPath();
            String[] rotaElementos = rota.split("/");
            if (rotaElementos.length == 2) {
                Bike[] bicicletas = Bike.getBikeList();
                ListaBicicletasView.atualizaInterfaceUsuario(bicicletas, t);
            }

            else if (rotaElementos.length == 3) {
                String idBicicleta = rotaElementos[2];
                Bike bicicleta = Bike.findBikeByNumber(Integer.parseInt(idBicicleta));
                BicicletaDetalhesView.atualizaInterfaceUsuario(bicicleta, t);
            }
        }
    }
}
