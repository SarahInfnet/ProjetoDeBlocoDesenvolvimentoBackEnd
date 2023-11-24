package view;

import com.sun.net.httpserver.HttpExchange;
import model.Bike;

import java.io.IOException;
import java.io.OutputStream;

public class BicicletaDetalhesView {
    public static void atualizaInterfaceUsuario(Bike bicicleta, HttpExchange t) throws IOException {
        String bicicletaHTMLTitulo = "<h2>" + bicicleta.getDescription() + "</h2>";
        String bicicletaHTMLDeposito = "<p>Deposito: " + bicicleta.getDeposit() + "</p>";
        String bicicletaHTMLTaxaDiaria = "<p>Taxa diaria: " + bicicleta.getRate() + "</p>";
        String bicicletaHTML = bicicletaHTMLTitulo + bicicletaHTMLDeposito + bicicletaHTMLTaxaDiaria;
        String html = """
            <html>
                <head><title>Bicicleta</title></head>
                <body>
                    <h1>Sarah's Bikes</h1>
                    %s
                    <label>Quantidade de bicicletas: </label>
                    <input id="qtdBicicletas" type="number" min="1" value="1"> <br>
                    <label>Quantidade de dias: </label>
                    <input id="qtdDias" type="number" min="1" value="1"> <br>
                    <p id="custo">Custo Total: -------</p>
                    <button onclick="obtemCustoAluguel()">Calcular Valor</button>
                    <button onclick="redireciona(geraURLFinalizacaoAluguel())">Alugar</button>
                    
                    <script>
                        function redireciona(url){
                            window.location.href = url;
                        }
                        
                        function geraURLFinalizacaoAluguel() {
                            let qtdDias = document.getElementById("qtdDias").value;
                            let qtdBicicletas = document.getElementById("qtdBicicletas").value;
                            let numBike = %d;
                            let url = `/aluguel/finalizaAluguel/${numBike}/${qtdBicicletas}/${qtdDias}`;
                            return url;
                        }
                        
                        async function obtemCustoAluguel() {
                            let qtdDias = document.getElementById("qtdDias").value;
                            let qtdBicicletas = document.getElementById("qtdBicicletas").value;
                            let numBike = %d;
                            let url = `/aluguel/custo/${numBike}/${qtdBicicletas}/${qtdDias}`;
                            let custoTotal = await fetch(url).then(resposta => resposta.text());
                            custoTotal = Number.parseInt(custoTotal);
                            let custoElem = document.getElementById('custo');
                            custoElem.innerHTML = `Custo Total: R$ ${custoTotal},00`;
                        }
                    </script>
                </body>
            </html>
        """.formatted(bicicletaHTML, bicicleta.getBikeNumber(), bicicleta.getBikeNumber());

        t.sendResponseHeaders(200, html.length());
        t.getResponseHeaders().set("Content-Type", "text/html");

        OutputStream os = t.getResponseBody();
        os.write(html.getBytes());

        os.close();
    }
}
