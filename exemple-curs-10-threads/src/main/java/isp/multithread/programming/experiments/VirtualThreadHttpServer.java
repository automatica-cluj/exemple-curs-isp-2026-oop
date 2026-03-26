package isp.multithread.programming.experiments;

import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class VirtualThreadHttpServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(9999), 0);

        // Configurare executor cu Virtual Threads
        server.setExecutor(Executors.newVirtualThreadPerTaskExecutor());

        server.createContext("/api", exchange -> {
            // Simulăm o operație care durează
            try {
                Thread.sleep(500); // Simulăm procesarea sau I/O

                String response = "Procesat de " + Thread.currentThread() +
                        ", Virtual: " + Thread.currentThread().isVirtual();

                exchange.sendResponseHeaders(200, response.length());
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                exchange.sendResponseHeaders(500, 0);
            }
        });

        server.start();
        System.out.println("Server pornit pe portul 9999");
    }
}
