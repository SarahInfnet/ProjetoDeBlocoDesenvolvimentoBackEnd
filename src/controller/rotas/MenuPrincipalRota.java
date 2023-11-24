package controller.rotas;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import view.MenuPrincipalView;

import java.io.IOException;

public class MenuPrincipalRota {
    public static String rota = "/";
    public static HttpHandler tratadorRota = new TratadorRota();

    static class TratadorRota implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            MenuPrincipalView.atualizaInterfaceUsuario(t);
        }
    }
}
