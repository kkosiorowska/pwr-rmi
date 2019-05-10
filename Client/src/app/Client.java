package app;

import knapSack.Instance;
import knapSack.Item;
import knapSack.Solution;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Client implements IClient {

    private  IServer server;
    private  IRegister registers;

    /*
    Uruchomienia i przetestowania co najmniej dwóch aplikacji klienckich.
    Aplikacje klienckie powinny posiadać interfejs umożliwiający wyświetlenie
    obecnie zarejestrowanych w spisie serwerów, wygenerowania losowej instancji
    problemu plecakowego (wraz z jej wyświetleniem w konsoli) oraz rozwiązania
    jej poprzez zadany serwer (wraz z wyświetleniem wyniku na ekranie klienta).
    * */

    public Client () throws RemoteException, NotBoundException {

        System.out.println("Client port: "+ 3007);
        IClient client = (IClient) UnicastRemoteObject.exportObject((Remote) this, 3007);
        Registry registry = LocateRegistry.getRegistry(Registry.REGISTRY_PORT);
        //server = (IServer) registry.lookup(nameServer);
//        registers = (IRegister) registry.lookup("Register");
//        id = server.registers();
//
//        registry.bind("Klient", (Remote) client);
    }

    public static void menu(Client client) throws RemoteException {

        while(true) {
            Scanner userInput = new Scanner(System.in);
            String choice;

            System.out.println("Menu");
            System.out.println("1. Show all servers");
            //System.out.println("2. Generate random instance");
//            System.out.println("3. Solve knapsack problem");
//            System.out.println("4. Exit app");
            System.out.print("Choice: ");
            choice = userInput.nextLine();

            switch(choice) {
                case "1":
                    client.getAllServers();
                    break;
////                case 2:
////                    client.genarateInstance();
////                    break;
//                case 3:
//                    client.getAllServers();
//                    int idx;
//                    System.out.print("Get numer of server: ");
//                    client.server = new Server(registers.getServers().get(idx))
//                    client.solveProblem();
//                    break;
//                case 4:
//                    // code block
//                    break;
                default:
                    System.out.println("ERROR!");
            }
        }
    }

    @Override
    public void getAllServers() throws RemoteException {
        for(int i=0;i<registers.getServers().size();i++)
        {
            int idx = i +1;
            System.out.println(idx+". "+registers.getServers().get(i).getName());
        }
    }

    @Override
    public Instance genarateInstance() throws RemoteException {
        long seedLocal = (long)new Random().nextInt(5000) + 2000;
        int capacity = (int)(seedLocal%20+ 3);
        List<Item> listItems = new ArrayList<>();

        for(int i=10; i < 120 ; i+=10 ){
            //float profit, int weight
            Item item = new Item((float) (seedLocal%i + 2) ,(int)seedLocal%i);
            listItems.add(item);
        }
        Instance instance  = new Instance(listItems, capacity);
        return  instance ;
    }

    @Override
    public void solveProblem() throws RemoteException {

        Instance instance = genarateInstance();
        Solution solution = server.solve(instance);
    }

    public static void main(String[] args) throws RemoteException, NotBoundException {
        Client client = new Client();
        menu(client);
    }
}
