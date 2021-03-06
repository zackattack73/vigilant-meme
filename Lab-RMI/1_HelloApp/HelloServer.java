import java.rmi.server.*;
import java.rmi.registry.*;

public class HelloServer {
	public static void main(String[] args) {
		try {
			// Create a Hello remote object
			HelloImpl h = new HelloImpl("Hello world !");
			Hello h_stub = (Hello) UnicastRemoteObject.exportObject(h, 0);

			// Register the remote object in RMI registry with a given identifier
			Registry registry = LocateRegistry.getRegistry(); // Local host
			// to connect to distant registry use : getRegistry(String host, int port)
			registry.bind("HelloService", h_stub);

			System.out.println("Server ready");
		} catch (Exception e) {
			System.err.println("Error on server : " + e.getMessage());
			e.printStackTrace();
		}
	}
}
