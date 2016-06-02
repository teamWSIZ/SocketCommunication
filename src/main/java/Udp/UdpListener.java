package Udp;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpListener {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket(11111);
        byte[] receiveData = new byte[1024];
        int num = 0;
        while(true) {
            DatagramPacket packet = new DatagramPacket(receiveData, receiveData.length);
            ds.receive(packet);
            String line = new String(packet.getData());
            num++;
            if (line.startsWith("ex")) break;
        }
        System.out.println(num);
    }
}
