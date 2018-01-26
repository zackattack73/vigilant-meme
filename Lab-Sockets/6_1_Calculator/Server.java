import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Calculator_itf {
	public static void main(String[] args) {
		// Arguments verification ----------------------------------------------
		if (args.length != 1) {
			System.err.println("Usage: java Server <port number>");
			System.exit(1);
		}
		int port = Integer.parseInt(args[0]);
		// Socket communication ------------------------------------------------
		int op, l, r, result = -1;
		Server s = new Server();
        try (
                ServerSocket serverSocket =
                        new ServerSocket(Integer.parseInt(args[0]));
                Socket clientSocket = serverSocket.accept();
                PrintWriter out =
                        new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()))
        ) {
            op = Integer.parseInt(in.readLine());
            l = Integer.parseInt(in.readLine());
            r = Integer.parseInt(in.readLine());
            switch (op) {
                case 1:
                    result = s.plus(l, r);
                    break;
                case 2:
                    result = s.minus(l, r);
                    break;
                case 3:
                    result = s.divide(l, r);
                    break;
                case 4:
                    result = s.multiply(l, r);
                    break;
            }
            out.println(Integer.toString(result));
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + port + " or listening for a connection");
            System.out.println(e.getMessage());
        }
	}

	public int plus(int x, int y) {return x+y;}
	public int minus(int x, int y) {return x-y;}
	public int divide(int x, int y) {return x/y;}
	public int multiply(int x, int y) {return x*y;}
}
