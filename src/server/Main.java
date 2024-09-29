package client;

import java.net.Socket;
import java.io.IOException;

/**
 * Главный класс клиентской части, который устанавливает соединение с сервером.
 */
public class Main {
    /**
     * Главный метод, который выполняется при запуске клиентской программы.
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        String IPaddr = "127.0.0.1"; // IP адрес сервера
        int port = 55555; // Порт сервера

        try {
            // Подключение к серверу
            Socket socket = new Socket(IPaddr, port);
            System.out.println("Подключение к серверу " + IPaddr + ":" + port);

            // Закрытие сокета
            socket.close(); // Сокет закрывается сразу после подключения
        } catch (IOException e) {
            // Обработка ошибки подключения
            System.out.println("Ошибка подключения к серверу: " + e.getMessage());
        }
    }
}
