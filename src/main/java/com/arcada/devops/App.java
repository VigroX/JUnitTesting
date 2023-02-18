package com.arcada.devops;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class App 
{
    private static final int PORT = 8080;
    private static Facebook facebook = new Facebook(new String[] {"Ville", "Mark", "Johan"});
    
    private static int counter = 0;
    
    public static void main(String[] args)
    {
        try {
            final HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
            server.createContext("/", new FriendsHandler());
            server.setExecutor(null);
            server.start();
            System.out.printf("Server started on port %d", PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class FriendsHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {

            if (!"GET".equals(t.getRequestMethod()))
                t.sendResponseHeaders(405, -1);
            
            if (!"/".equals(t.getRequestURI().toString()))
                t.sendResponseHeaders(404, -1);

            t.getResponseHeaders().set("Content-Type", "application/json");
            StringBuilder response = new StringBuilder();
            response.append("{ \"friends\": [");
            for (String friend : facebook.getFriends()) {
                response.append("\"");
                response.append(friend);
                response.append("\",");
            }

            response.deleteCharAt(response.length() - 1);
            response.append("], ");
            response.append("\"counter\": ");
            response.append(increaseCounter());
            response.append("}");

            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.toString().getBytes());
            os.close();
        }
    }

    public static int increaseCounter() {
        return counter++;
    }
}
