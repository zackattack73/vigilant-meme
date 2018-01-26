public class Server implements Calculator_itf {
	public static void main(String[] args) {
		// Arguments verification ----------------------------------------------
		if (args.length != 1) {
			System.err.println("Usage: java Server <port number>");
			System.exit(1);
		}
		int port = Integer.parseInt(args[0]);
		// Socket communication ------------------------------------------------
		// TODO

	}

	public int plus(int x, int y) {return x+y;}
	public int minus(int x, int y) {return x-y;}
	public int divide(int x, int y) {return x/y;}
	public int multiply(int x, int y) {return x*y;}
}
