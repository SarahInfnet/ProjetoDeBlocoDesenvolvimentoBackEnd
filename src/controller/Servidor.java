package controller;

import com.sun.net.httpserver.HttpServer;
import controller.rotas.*;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Servidor {
    public static void main(String[] args) throws IOException {
        int porta = 3000;
        HttpServer servidor = HttpServer.create(new InetSocketAddress(porta), 0);

        servidor.createContext(MenuPrincipalRota.rota, MenuPrincipalRota.tratadorRota);
        servidor.createContext(BicicletaRota.rota, BicicletaRota.tratadorRota);
        servidor.createContext(AluguelFinalizacaoRota.rota, AluguelFinalizacaoRota.tratadorRota);
        servidor.createContext(AluguelCustoRota.rota, AluguelCustoRota.tratadorRota);
        servidor.createContext(PagamentoConsultaRota.rota, PagamentoConsultaRota.tratadorRota);

        servidor.setExecutor(null);
        servidor.start();
        System.out.println("Servidor rodando na porta " + porta + "...");
    }
}
