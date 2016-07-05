 

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;

public class WorkingMulticast {
    static String ip = "239.255.11.11";

    public static void main(String[] args) throws Exception{
        Thread wr = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    DatagramSocket ds = new DatagramSocket();
                    ds.setBroadcast(true);
                    InetAddress net = InetAddress.getByName(ip);
                    byte[] data = "Abra kad..".getBytes();
                    System.out.println("writer ready...");
                    Thread.sleep(1000);
                    DatagramPacket packet = new DatagramPacket(data, data.length, net, 11111);
                    for (int i = 0; i < 1e3; i++) {
                        ds.send(packet);
//                        Thread.sleep(1);
                    }
                    ds.send(new DatagramPacket("exit".getBytes(), 4, net, 11111));
                    System.out.println("writer finished...");
                    ds.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        wr.start();


        try {
            System.out.println(".....");
            MulticastSocket socket = new MulticastSocket(11111);
            InetAddress group = InetAddress.getByName(ip);
            socket.setNetworkInterface(NetworkInterface.getByName("lo"));   //or eth3 on box
            socket.joinGroup(group);

            byte[] receiveData = new byte[16];  //adjust as you need
            int num = 0;
            System.out.println("UDP multi-socket @" + ip + " listens...");
            while(true) {
                DatagramPacket packet = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(packet);
                String line = new String(packet.getData());
                num++;
                System.out.print("*");
                if (num%80==0) {
                    System.out.println();
                }
                if (line.startsWith("ex")) break;
            }
            System.out.println(ip + "-> " + num);
            socket.leaveGroup(group);
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Just list local interfaces and their description
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface netint : Collections.list(nets)) {
            System.out.println(netint.getName() + " -->" + netint.getDisplayName() + "; ");
            for(InterfaceAddress adr : netint.getInterfaceAddresses()) {
                System.out.println(" -> " + adr);
            }
        }
    }

}
