package TcpSimple;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class SimpleWriter {
    public static void main(String[] args) throws Exception {
        System.out.println("Which id?");
        Integer id = new Scanner(System.in).nextInt();
        Socket s = new Socket("10.11.12." + id,22222);
        OutputStreamWriter osw = new OutputStreamWriter(s.getOutputStream());
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write("Abra kadabra");
        bw.newLine();
        bw.write("exit");
        bw.newLine();
        bw.flush();
        s.close();
    }
}
