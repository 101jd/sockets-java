package server;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.LinkedList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static <socket> void main(String[] args) {
        String IPaddr = "127.0.0.1";
        int port = 55555;
        InetSocketAddress address = new InetSocketAddress(IPaddr, port);
        List<Socket> clients = new LinkedList<>();

        ServerSocket socket = null;
        try {
            socket = new ServerSocket();
            socket.bind(address);
        } catch (IOException e){
            System.err.println("Bind failed\n" + e.getMessage());
        }

        listen(socket, clients);


    }
    public static void listen(ServerSocket server, List<Socket> clients) {
        while (true){
            try {
                Socket client = server.accept();
                clients.add(client);
                client.getOutputStream()
                        .write(String.format("You connected to server %s", server.getInetAddress().toString())
                                .getBytes("ascii"));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                System.err.println("Client connection failed\n" + e.getMessage());;
            }
        }
    }
}