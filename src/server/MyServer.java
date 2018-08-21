package server;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class MyServer implements Server
{
    private ServerSocket server;
    private int port;
    private volatile boolean stop;

    public MyServer(int port)
    {
        this.port = port;
        stop = false;
    }
    // "127.0.0.1" / "localhost"
    private void runServer(ClientHandler ch) throws IOException {
        while (!stop) {
            try {
                Socket aClient = this.server.accept(); // blocking call
                new Thread(() -> {
                    try {
                        ch.handle(aClient.getInputStream(), aClient.getOutputStream());
                        aClient.close();
                        //System.out.println("exit");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            } catch (IOException ignored) {}
        }
        server.close();
    }
    public void start(ClientHandler ch) {
        try {
            this.server = new ServerSocket(this.port);
            this.server.setSoTimeout(300);
        } catch (IOException e) {
            //
            // e.printStackTrace();
        }

        System.out.println("Listening on port " + port);

        new Thread(()->{try {
            runServer(ch);
        }catch (Exception e){
            e.printStackTrace();
        }
        }).start();
    }
    public void stop()
    {
        stop=true;
    }

    public static void main(String[] args) {
        MyServer myServer = new MyServer(6400);
        myServer.start(new MyClientHandler());

    }
}
