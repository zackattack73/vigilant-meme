import java.rmi.*;

public interface Hello extends Remote {
	String sayHello(Info_itf client) throws RemoteException;
}
