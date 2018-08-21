package server;

public interface Server{
    public void start(ClientHandler ch);
    public void stop();
}
