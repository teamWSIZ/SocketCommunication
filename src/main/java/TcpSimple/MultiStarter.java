package TcpSimple;

public class MultiStarter {
    public static void main(String[] args) {
        for (int i = 1; i < 3; i++) {
            Integer id = i;
            new Thread(() -> {
                new EasyListener("10.11.12." + id).start();
            }).start();
        }
    }
}
