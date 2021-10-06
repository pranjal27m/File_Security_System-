package project.client;

import java.net.Socket;
import project.client.signinPage;

public class client {
    public static void main(String[] args) throws Exception {

        try (var socket = new Socket("127.0.0.1", 7592)) {
        	
        	signinPage sp = new signinPage();
			sp.setVisible(true);
        	
        }
    }
}