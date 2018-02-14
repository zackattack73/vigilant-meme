import java.rmi.*;

public class Client_Info implements Info_itf, Accounting_itf {
	private String name;

	Client_Info(String name) {
		this.name = name;
	}

    public String getName() throws RemoteException {
		return name;
    }

	public void numberOfCalls(int number) throws RemoteException {
	    System.out.println(number);
	}
}
