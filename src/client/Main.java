package server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 * Главный класс серверной части, который создает серверный сокет
 * и обрабатывает входящие подключения от клиентов.
 */
public class Main {
    /**
     * Главный метод, который выполняется при запуске серверной программы.
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        String IPaddr = "127.0.0.1"; // IP адрес сервера
        int port = 55555; // Порт сервера
        List<Socket> clients = new LinkedList<>(); // Список подключенных клиентов

        try {
            // Создание сервера и ожидание подключения клиентов
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Сервер запущен на порту " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept(); // Ожидание подключения клиента
                clients.add(clientSocket); // Добавление клиента в список

                // Отправка приветственного сообщения клиенту
                sendWelcomeMessage(clientSocket, IPaddr, port);
            }
        } catch (IOException e) {
            // Обработка ошибки сервера
            System.out.println("Ошибка сервера: " + e.getMessage());
        }
    }

    /**
     * Метод для отправки приветственного сообщения подключившемуся клиенту.
     *
     * @param clientSocket сокет клиента
     * @param ip IP адрес сервера
     * @param port порт сервера
     */
    private static void sendWelcomeMessage(Socket clientSocket, String ip, int port) {
        try {
            // Отправка приветственного сообщения клиенту
            OutputStream out = clientSocket.getOutputStream();
            String welcomeMessage = "Вы подключены к серверу " + ip + ":" + port + "\n";
            out.write(welcomeMessage.getBytes());
            out.flush(); // Принудительная отправка данных
        } catch (IOException e) {
            // Обработка ошибки при отправке сообщения
            System.out.println("Ошибка при отправке сообщения клиенту: " + e.getMessage());
        }
    }
}
