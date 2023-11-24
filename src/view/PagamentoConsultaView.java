package view;

import com.sun.net.httpserver.HttpExchange;
import model.Bike;
import model.Customer;
import model.Hire;

import java.io.IOException;
import java.io.OutputStream;

public class PagamentoConsultaView {
    public static void atualizaInterfaceUsuario(Hire aluguel, int custo, int totalBikes, HttpExchange t) throws IOException {
        Bike bicicleta = aluguel.getBike();
        Customer cliente = aluguel.getCustomer();
        String html = """
                <html>
                <head><title>Pagamento Realizado</title></head>
                <body>
                    <h1>Sarah's Bikes</h1>
                    <h2>Pedido realizado com sucesso</h2>
                    <p>%s</p>
                    <p>Quantidade de bicicletas: %d</p>
                    <p>Quantidade de dias: %d</p>
                    <p>Valor: R$ %d,00</p>
                    
                    <h3>Dados do cliente:</h3>
                    <p>Nome: %s</p>
                    <p>Telefone: %d</p>
                    <p>CEP: %s</p>
                    
                    <script>
                        function redireciona(url) {
                            window.location.href = url;
                        }
                    </script>
                </body>
            </html>
        """.formatted(
                bicicleta.getDescription(),
                totalBikes,
                aluguel.getNumberOfDays(),
                custo, cliente.getName(),
                cliente.getTelephone(),
                cliente.getPostcode()
        );

        t.sendResponseHeaders(200, html.length());
        t.getResponseHeaders().set("Content-Type", "text/html");

        OutputStream os = t.getResponseBody();
        os.write(html.getBytes());

        os.close();
    }
}
