import java.rmi.*;

public class Client_Info implements Info_itf {
	private String name;

	public Client_Info(String name) {this.name=name;}

    public String getName() throws RemoteException {
		return name;
    }
}
