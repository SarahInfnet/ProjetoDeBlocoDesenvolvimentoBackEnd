package view;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class MenuPrincipalView {
    public static void atualizaInterfaceUsuario(HttpExchange t) throws IOException {
        String html = """
            <html>
                <head><title>Menu Principal</title></head>
                <body>
                    <h1>Sarah's Bikes</h1>
                    <h2>Seja bem-vindo!</h2>
                    <p>Como podemos te ajudar?</p>
                    <button onclick="redireciona('/bicicleta')">Alugar Bicicletas</button>
                    <br><br>
                    <input id="idBicicleta" type="text" placeholder="Digite o id da bicicleta"> 
                    <button onclick="redireciona('/bicicleta/'+document.getElementById('idBicicleta').value)">Buscar Bicicleta</button>
                    
                    <script>
                        function redireciona(url){
                            window.location.href = url;
                        }
                    </script>
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
