import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {


    static String textTemp;
    static String text;

    private static Socket clientSocket; //сокет для общения
    private static ServerSocket server; // серверсокет
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет
    private static char endChar;
    private static char oneChar;


    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(8989)) {// стартуем сервер один(!) раз
            System.out.println("Сервер запущен!");
            while (true) { // в цикле(!) принимаем подключения
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream())
                ) {
                    if (text == null) {
                        out.write("???:" + "\n");
                        out.flush();
                    } else {
                        endChar = text.charAt(text.length() - 1);
                        out.write("Вам на " + endChar + "\n");
                        out.flush();
                    }
                    textTemp = in.readLine();
                    System.out.println(textTemp);
                    if (text != null && endChar == textTemp.charAt(0)) {
                        out.write("OK" + "\n");
                        text = textTemp;
                    } else if (text == null) {
                        out.write("OK" + "\n");
                        text = textTemp;
                    } else {
                        out.write("NOT OK" + "\n");
                    }
                    out.flush();


                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}


