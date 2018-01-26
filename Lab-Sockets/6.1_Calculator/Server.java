public class Server {
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
}
