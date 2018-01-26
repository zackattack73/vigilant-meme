import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server implements Registry_itf {
    private List<Person> personList;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Server <port number>");
            System.exit(1);
        }
        int port = Integer.parseInt(args[0]);
        System.out.println("Listening on port " + port);

        int op;
        boolean exit = false;
        final Server s = new Server();

        // Temporaries
        Person p;
        Iterable<Person> pl;
        String st;

        try (
                ServerSocket serverSocket = new ServerSocket(port);
                Socket clientSocket = serverSocket.accept();
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())
        ) {
            while (!exit) {
                op = in.read();

                switch(op) {
                    case 1:
                        s.add((Person) in.readObject());
                        break;
                    case 2:
                        st = s.getPhone((String) in.readObject());
                        out.writeObject(st);
                        break;
                    case 3:
                        pl = s.getAll();
                        for (Person pt : pl) out.writeObject(pt);
                        out.writeObject(null);
                        break;
                    case 4:
                        p = s.search((String) in.readObject());
                        out.writeObject(p);
                        break;
                    case 5:
                        exit = true;
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + port + " or listening for a connection");
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Classe n'existe pas!");
        }
    }

    private Server() {
        this.personList = new ArrayList<>();
    }

    @Override
    public void add(Person p) {
        this.personList.add(p);
    }

    @Override
    public String getPhone(String name) {
        Person p = search(name);
        if (p != null) return p.phone;
        return null;
    }

    @Override
    public Iterable<Person> getAll() {
        return this.personList;
    }

    @Override
    public Person search(String name) {
        Person p = null;
        // Basic search algorithm
        for (Person ps : personList) {
            if (ps.name.equals(name)) {
                p = ps;
                break;
            }
        }
        return p;
    }
}
