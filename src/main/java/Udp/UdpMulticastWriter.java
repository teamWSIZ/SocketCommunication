package Udp;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpMulticastWriter {
    //more: http://michieldemey.be/blog/network-discovery-using-udp-broadcast/
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket();
        ds.setBroadcast(true);
        InetAddress net = InetAddress.getByName("10.11.12.0");
        byte[] data = "Abra kad..".getBytes();
        long st = System.currentTimeMillis();
        DatagramPacket packet = new DatagramPacket(data, data.length, net, 11111);
        for (int i = 0; i < 1e4; i++) {
            ds.send(packet);
//            waitFewMicroseconds();
        }
        ds.send(new DatagramPacket("exit".getBytes(), 4, net, 11111));
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
