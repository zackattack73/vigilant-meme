import java.util.Scanner;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;

import java.net.UnknownHostException;
import java.io.IOException;

public class Client {
	public static void main(String[] args) {
		// Arguments verification ----------------------------------------------
		if (args.length != 2) {
			System.err.println("Usage: java Client <host name> <port number>");
			System.exit(1);
		}
		String host = args[0];
		int port = Integer.parseInt(args[1]);
		// Operation choosing menu ---------------------------------------------
		Scanner sc = new Scanner(System.in);
		System.out.println("--------- Choose operation :");
		System.out.println("1 : Addition");
		System.out.println("2 : Substraction");
		System.out.println("3 : Division");
		System.out.println("4 : Multiplication");
		System.out.println("----------------------------");
		// Integer values ------------------------------------------------------
		int op = sc.nextInt();
		System.out.print("First integer : ");
		int x = sc.nextInt();
		System.out.print("Second integer : ");
		int y = sc.nextInt();
		// Socket communication ------------------------------------------------
		try (
			Socket sk = new Socket(host, port);
			PrintWriter skOut = new PrintWriter(sk.getOutputStream(), true);
			BufferedReader skIn = new BufferedReader(new InputStreamReader(sk.getInputStream()))
		) {
			// Sending data through socket -------------------------------------
			skOut.println(op);
			skOut.println(x);
			skOut.println(y);
			// Reading result --------------------------------------------------
			String res = skIn.readLine();
			// Printing result -------------------------------------------------
			System.out.println("-----------------------");
			System.out.print(x+"");
			switch(op) {
				case 1:
					System.out.print(" + ");
					break;
				case 2:
					System.out.print(" - ");
					break;
				case 3:
					System.out.print(" / ");
					break;
				case 4:
					System.out.print(" * ");
					break;
			}
			System.out.println(y + " = " + res);
			// -----------------------------------------------------------------
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + host);
			System.exit(1);
        } catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " + host);
			System.exit(1);
        }
	}
}
