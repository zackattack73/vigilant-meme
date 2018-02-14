import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import static java.lang.System.exit;

public class HelloClient {
    public static void main(String[] args) {
        try {
            if (args.length < 1) {
                System.out.println("Usage: java HelloClient <rmiregistry host>");
                return;
            }

            String host = args[0];

            Client_Info client = new Client_Info("Client lient ient ent nt t.");
            Info_itf info_stub = (Info_itf) UnicastRemoteObject.exportObject(client, 0);

            // Get remote object reference
            Registry registry = LocateRegistry.getRegistry(host);
            Hello h = (Hello) registry.lookup("HelloService");

            // Remote method invocation
            String res = h.sayHello(client);
            System.out.println(res);

            exit(0);
        } catch (Exception e) {
            System.err.println("Error on client: " + e);
        }
    }
}
