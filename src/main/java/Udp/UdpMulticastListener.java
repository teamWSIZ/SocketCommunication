package Udp;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class UdpMulticastListener {
    String ip;

    public UdpMulticastListener(String ip) {
        this.ip = ip;
    }

    public void start() {
        try {
            System.out.println(".....");
            MulticastSocket socket = new MulticastSocket(11111);
            InetAddress group = InetAddress.getByName("10.11.12.0");
            socket.joinGroup(group);
//
//            System.out.println("starting UDP multi-socket @" + ip);
//            MulticastSocket ds = new MulticastSocket(11111);
//            InetAddress net = InetAddress.getByName(ip);
//            ds.joinGroup(net);

            byte[] receiveData = new byte[16];  //adjust as you need
            int num = 0;
            System.out.println("UDP multi-socket @" + ip + " listens...");
            while(true) {
                DatagramPacket packet = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(packet);
                String line = new String(packet.getData());
                num++;
                if (line.startsWith("ex")) break;
            }
            System.out.println(ip + "-> " + num);
            socket.leaveGroup(group);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
