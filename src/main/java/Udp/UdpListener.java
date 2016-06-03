package Udp;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpListener {
    String ip;

    public UdpListener(String ip) {
        this.ip = ip;
    }

    public void start() {
        try {
            DatagramSocket ds = new DatagramSocket(11111, InetAddress.getByName(ip));
            byte[] receiveData = new byte[16];  //adjust as you need
            int num = 0;
            System.out.println("UDP socket @" + ip + " listens...");
            while(true) {
                DatagramPacket packet = new DatagramPacket(receiveData, receiveData.length);
                ds.receive(packet);
                String line = new String(packet.getData());
                num++;
                if (line.startsWith("ex")) break;
            }
            System.out.println(ip + "-> " + num);
            ds.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
