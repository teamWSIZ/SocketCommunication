package Udp;


public class UdpListenerStarter {
    public static void main(String[] args) {
        for (int i = 2; i <= 2; i++) {
            Integer id = i;
            //Listening on selected IP for normal UDPs
            new Thread(()->{new UdpMulticastListener("10.11.12.0").start();}).start();

            //Listening on broadcast address; cant have 2 threads on this...
            //todo: check on 2 separate OS'es in the subnet
//            new Thread(()->{new UdpListener("10.11.12.0").start();}).start();
        }

    }
}
