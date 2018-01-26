import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java Client <host name> <port number>");
            System.exit(1);
        }
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        System.out.println("Connected to " + host + " on port " + port);

        boolean exit = false;
        int op;

        try (
                Scanner sysIn = new Scanner(System.in);
                Socket sk = new Socket(host, port);
                ObjectOutputStream out = new ObjectOutputStream(sk.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(sk.getInputStream())
        ) {
            while (!exit) {
                op = chooseOp(sysIn);
                out.write(op);
                out.flush(); // must be called

                switch(op) {
                    case 1:
                        addPerson(sysIn, out);
                        break;
                    case 2:
                        getPhone(sysIn, in, out);
                        break;
                    case 3:
                        getAll(in);
                        break;
                    case 4:
                        search(sysIn, in, out);
                        break;
                    case 5:
                        exit = true;
                        break;
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + host);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + host);
            System.exit(1);
        } catch (ClassNotFoundException e) {
            System.out.println("Classe n'existe pas!");
        }
    }

    private static int chooseOp(Scanner sysIn) {
        System.out.println("--------- Choose operation :");
        System.out.println("1 : Add person");
        System.out.println("2 : Get phone");
        System.out.println("3 : Get all");
        System.out.println("4 : Search");
        System.out.println("5 : Exit");
        System.out.println("----------------------------");

        int op = sysIn.nextInt();
        sysIn.nextLine(); // empty buffer

        return op;
    }

    private static void addPerson(Scanner sysIn, ObjectOutputStream out) throws IOException {
        String name;
        String phone;

        System.out.print("Nom : ");
        name = sysIn.nextLine();
        System.out.print("Telephone : ");
        phone = sysIn.nextLine();

        Person p = new Person(name, phone);
        out.writeObject(p);
    }

    private static void getPhone(Scanner sysIn, ObjectInputStream in, ObjectOutputStream out) throws IOException, ClassNotFoundException {
        String name;
        String phone;

        System.out.print("Nom : ");
        name = sysIn.nextLine();

        out.writeObject(name);
        phone = (String) in.readObject();
        System.out.println("Telephone de " + name + " est " + phone);
    }

    private static void getAll(ObjectInputStream in) throws IOException, ClassNotFoundException {
        Object o;

        while ((o = in.readObject()) != null) {
            System.out.println(o);
        }
    }

    private static void search(Scanner sysIn, ObjectInputStream in, ObjectOutputStream out) throws IOException, ClassNotFoundException {
        String name;
        Person p;

        System.out.print("Nom : ");
        name = sysIn.nextLine();

        out.writeObject(name);
        p = (Person) in.readObject();
        System.out.println(p);
    }
}
