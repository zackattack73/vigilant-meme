import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Accounting_itf extends Remote {
    void numberOfCalls(int number) throws RemoteException;
}
