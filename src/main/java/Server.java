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
        /*try {
            ServerSocket server = new ServerSocket(4004); // серверсокет прослушивает порт 4004
            System.out.println("Сервер запущен!");
            while (true) {

                try {
                    clientSocket = server.accept();
                    System.out.println("New client connected to server");

                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));




                    if (text == null) {
                        out.write("Привет, это Сервер! Ты первый вводи любой город" + "\n");
                        }else if (oneChar==text.charAt(text.length()-1) && text != null ){
                        out.write("Вы ввели неправильное значение, было нужно : " + oneChar + "\n");
                    }else {
                        out.write("Вам на: " + endChar + "\n");
                        oneChar=endChar;
                    }

                    out.flush();

                    text = in.readLine();
                    oneChar = text.charAt(0);


                    System.out.println("Вам написали текст: " + text + "\n" + "Номер порта: " + clientSocket.getPort());
                    System.out.println(endChar);
                    System.out.println(oneChar);
                    clientSocket.close();
                    // потоки тоже хорошо бы закрыть
                    in.close();
                    out.close();


                    // выталкиваем все из буфера
                } finally { // в любом случае сокет будет закрыт
                    clientSocket.close();
                    // потоки тоже хорошо бы закрыть
                    in.close();
                    out.close();
                }
            }


        } catch (IOException e) {
            System.err.println(e);
        }*/
        try (ServerSocket serverSocket = new ServerSocket(8989);) {// стартуем сервер один(!) раз
            System.out.println("Сервер запущен!");
            while (true) { // в цикле(!) принимаем подключения
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream());
                ) {
                    if (text==null){
                        out.write("???:"+"\n");
                        out.flush();
                    }else {
                        endChar=text.charAt(text.length()-1);
                        out.write("Вам на "+endChar+"\n");
                        out.flush();
                    }
                    textTemp = in.readLine();
                    System.out.println(textTemp);
                    if (text!=null && endChar==textTemp.charAt(0) ){
                        out.write("OK"+"\n");
                        text=textTemp;
                    }else if (text==null){
                        out.write("OK"+"\n");
                        text=textTemp;
                    }else{
                        out.write("NOT OK"+"\n");
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


