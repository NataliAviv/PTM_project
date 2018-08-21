package server;

public interface Board<T> {
    T getHieghtWidth(Integer x, Integer y);
    Integer getH();//get hieght
    Integer getW();//get width

}