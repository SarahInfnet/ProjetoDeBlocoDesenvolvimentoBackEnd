package view;

import com.sun.net.httpserver.HttpExchange;
import model.Bike;

import java.io.IOException;
import java.io.OutputStream;

public class AluguelFinalizacaoView {
    public static void atualizaInterfaceUsuario(Bike bicicleta, int numDias, int totalBikes, int custo, HttpExchange t) throws IOException {
        String descricao = bicicleta.getDescription();
        String html = """
             <html>
                <head><title>Coloque suas informacoes</title></head>
                <body>
                    <h1>Sarah's Bikes</h1>
                    <h2>Detalhes do pedido:</h2>
                    <p>%s</p>
                    <p>Valor: R$ %d,00</p>
                    
                    <h3>Dados do cliente:</h3>
                    
                    <label>Nome:</label>
                    <input id="nomeCliente" type="text" placeholder="Digite seu nome">
                    <br>
                    
                    <label>Telefone:</label>
                    <input id="telefoneCliente" type="tel" pattern="[0-9]+" placeholder="ex.: 21999999999">
                    <br>
                    
                    <label>Codigo Postal:</label>
                    <input id="cepCliente" type="text" placeholder="Digite seu CEP">
                   <br>
                   
                    <button onclick="redirecionaPaginaPagamento()">Finalizar</button>
                   
                    <script>
                        function redireciona(url) {
                            window.location.href = url;
                        }
                        
                        function redirecionaPaginaPagamento() {
                            let numBike = %d;
                            let totalBikes = %d;
                            let numDias = %d;
                            let nomeCliente = document.getElementById("nomeCliente").value;
                            let telefoneCliente = document.getElementById("telefoneCliente").value;
                            let cepCliente = document.getElementById("cepCliente").value;
                            let url = `/pagamento/consulta/${numBike}/${totalBikes}/${numDias}/${nomeCliente}/${telefoneCliente}/${cepCliente}`;
                            redireciona(url);
                        }
                    </script>
                </body>
            </html>
        """.formatted(descricao, custo, bicicleta.getBikeNumber(), totalBikes, numDias);

        t.sendResponseHeaders(200, html.length());
        t.getResponseHeaders().set("Content-Type", "text/html");

        OutputStream os = t.getResponseBody();
        os.write(html.getBytes());

        os.close();
    }
}
