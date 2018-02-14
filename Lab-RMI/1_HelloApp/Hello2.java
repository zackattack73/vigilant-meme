import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Hello2 extends Remote {
    void sayHello(Accounting_itf c) throws RemoteException;
}
