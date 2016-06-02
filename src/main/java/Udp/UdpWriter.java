package Udp;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpWriter {

    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("localhost");
        byte[] data = new byte[1024];
        String sentence = "Abra kadabra";
        data = sentence.getBytes();
        long st = System.currentTimeMillis();
        DatagramPacket packet = new DatagramPacket(data, data.length, ip, 11111);
        double trash = 0;
        for (int i = 0; i < 1e6; i++) {
            ds.send(packet);
            waitFewMicroseconds();
        }
        ds.send(new DatagramPacket("exit".getBytes(), 4, ip, 11111));
        ds.close();
        System.out.println(System.currentTimeMillis() - st + "[ms]");
    }

    private static void waitFewMicroseconds() {
        double x = 0;
        for (int i = 0; i < 100; i++) {
            x += Math.sin(Math.sin(i));
//            x += Math.sin(Math.sin(i));
        }
        //400ms --> full 1e4 packets sent && received

    }
}
