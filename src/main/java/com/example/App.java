
package com.example;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class App {

    public String getGreeting() {
        return "Hello, CI/CD Pipeline!";
    }

    public static void main(String[] args) throws IOException {
        int port = 8080;

        HttpServer server = HttpServer.create(
                new InetSocketAddress("0.0.0.0", port), 0);

        server.createContext("/", (HttpExchange exchange) -> {
            String html = """
                    <!DOCTYPE html>
                    <html>
                    <head>
                        <title>Java CI/CD App</title>
                        <meta charset="UTF-8">
                        <meta name="viewport" content="width=device-width, initial-scale=1">
                        <style>
                            body {
                                font-family: Arial, sans-serif;
                                text-align: center;
                                margin-top: 100px;
                                background: #f4f7fb;
                                color: #222;
                            }
                            .card {
                                display: inline-block;
                                padding: 35px;
                                background: white;
                                border-radius: 12px;
                                box-shadow: 0 4px 15px #0001;
                            }
                            h1 { color: #1769aa; }
                        </style>
                    </head>
                    <body>
                        <div class="card">
                            <h1>Hello, Cloud CI/CD Pipeline!</h1>
                            <p>Your Java application is running on Kubernetes.</p>
                            <p>Maven + Docker + Kubernetes on AWS EC2</p>
                        </div>
                    </body>
                    </html>
                    """;

            byte[] response = html.getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().set(
                    "Content-Type", "text/html; charset=UTF-8");
            exchange.sendResponseHeaders(200, response.length);

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response);
            }
        });

        server.start();
        System.out.println("Web server started on port " + port);
    }
}
