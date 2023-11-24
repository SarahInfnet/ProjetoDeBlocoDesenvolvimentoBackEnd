package view;

import com.sun.net.httpserver.HttpExchange;
import model.Bike;

import java.io.IOException;
import java.io.OutputStream;

public class ListaBicicletasView {
    public static void atualizaInterfaceUsuario(Bike[] bicicletas, HttpExchange t) throws IOException {
        String htmlBicicletas = "";
        String desc;
        int dep;
        int taxa;
        int num;
        String htmlBicicleta;
        for (Bike bicicleta : bicicletas){
            desc = bicicleta.getDescription();
            dep = bicicleta.getDeposit();
            taxa = bicicleta.getRate();
            num = bicicleta.getBikeNumber();
            htmlBicicleta = """
                <div style="border: 2px solid black;margin-bottom: 5px;padding: 5px">
                    <h3>%s</h3>
                    <p>Bicicleta #%d</p>
                    <p>Deposito: R$ %d,00</p>
                    <p>Taxa diaria: R$ %d,00</p>
                    <button onclick="redireciona('/bicicleta/%d')">
                        Alugar
                    </button>
                </div>
            """.formatted(desc, num, dep, taxa, num);
            htmlBicicletas = htmlBicicletas + htmlBicicleta;
        }

        String htmlBody = """
            <h1>Sarah's Bikes</h1>
            <h2>Confira nossas bicicletas disponiveis</h2>                   
        """ + htmlBicicletas + """            
            <script>
                function redireciona(url){
                    window.location.href = url;
                }
            </script>
        """;

        String html = """
                <html>
                    <head><title>Bicicletas</title></head>
                    <body>
            """ + htmlBody + """
                    </body>
                </html>
            """;

        t.sendResponseHeaders(200, html.length());
        t.getResponseHeaders().set("Content-Type", "text/html");

        OutputStream os = t.getResponseBody();
        os.write(html.getBytes());

        os.close();
    }
}
