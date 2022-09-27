import java.io.*;
import java.net.Socket;

public class Client {


    private static BufferedReader reader; // нам нужен ридер читающий с консоли, иначе как
    // мы узнаем что хочет сказать клиент?
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет

    public static void main(String[] args) {
        try (Socket clientSocket = new Socket("localhost", 8989)
        ) {
            ; // этой строкой мы запрашиваем

            reader = new BufferedReader(new InputStreamReader(System.in));
            // читать соообщения с сервера
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // писать туда же
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            String serverWord = in.readLine(); // ждём, что скажет сервер
            System.out.println(serverWord); // получив - выводим на экран

            String word = reader.readLine(); // ждём ввод
            out.write(word + "\n"); // отправляем сообщение на сервер
            out.flush();

            String serverWord2 = in.readLine(); // ждём, что скажет сервер
            System.out.println(serverWord2); // получив - выводим на экран


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}