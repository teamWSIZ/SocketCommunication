package TcpSimple;

public class SimpleStarter {
    public static void main(String[] args) throws Exception {
        new EasyListener("10.11.12.13").start();
    }
}