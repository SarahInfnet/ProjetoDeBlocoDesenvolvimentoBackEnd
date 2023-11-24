package view;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class AluguelCustoView {
    public static void enviaCusto(int custo, HttpExchange t) throws IOException {
        String custoString = Integer.toString(custo);
        t.sendResponseHeaders(200, custoString.length());
        t.getResponseHeaders().set("Content-Type", "text/text");

        OutputStream os = t.getResponseBody();
        os.write(custoString.getBytes());

        os.close();
    }
}
