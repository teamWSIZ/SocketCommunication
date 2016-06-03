import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by mareckip on 03.06.16.
 */
public class C {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("127.0.0.1",22222);
        OutputStreamWriter osw = new OutputStreamWriter(s.getOutputStream());
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write("Abra kadabra");
        bw.newLine();
        bw.write("ex");
        bw.newLine();
        bw.flush();
        s.close();
    }
}
