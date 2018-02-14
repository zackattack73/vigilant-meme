import java.rmi.*;

public interface Info_itf extends Remote {
	String getName() throws RemoteException;
}
