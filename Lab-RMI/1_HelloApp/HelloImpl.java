import java.rmi.*;
import java.util.HashMap;

public class HelloImpl implements Hello {
	private String message;
	private HashMap<String, Accounting_itf> clients;

	HelloImpl(String s) {
		message = s;
		clients = new HashMap<>();
	}

	public String sayHello(Info_itf client) throws RemoteException {
		return message + " (" + client.getName() + ")";
	}
}
