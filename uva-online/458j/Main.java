import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.EOFException;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        DataInputStream din = new DataInputStream(System.in);

        DataOutputStream dout = new DataOutputStream(System.out);

        boolean first = true;
        
        while (true) {
            try {
                byte b = din.readByte();
                if (b != 10)
                    dout.write(b - 7);
                else 
                    System.out.println();
            } catch (EOFException e) {
                break;
            }

        }

        sc.close();
    }
}
