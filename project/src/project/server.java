package project;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

public class server {

    public static void main(String[] args) throws Exception {
        try (var listener = new ServerSocket(7592)) {
            System.out.println("The server is running...");
            var pool = Executors.newFixedThreadPool(20);
            while (true) {
                pool.execute(new Capitalizer(listener.accept()));
            }
        }
    }

    private static class Capitalizer implements Runnable {
        private Socket socket;

        Capitalizer(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            System.out.println("Connected: " + socket);
            try {
                socket.close();
            } catch (IOException e) {
            }
            
        }
    }
}