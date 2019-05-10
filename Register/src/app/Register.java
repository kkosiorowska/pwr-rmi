package app;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Register  implements IRegister{

    int port = 3003 ;

    private ArrayList<OS> registeredServers = new ArrayList<>();

    public Register() throws RemoteException, AlreadyBoundException {
        System.out.println("Register port: " + getPort());
        IRegister register = (IRegister) UnicastRemoteObject.exportObject(this, getPort());
        Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        registry.bind("Register", this);
    }

    @Override
    public boolean register(OS os) throws RemoteException {
        if(os!=null)
        {
            System.out.println("Registered: " + os.getName());
            registeredServers.add(os);
            showAllServers();
            // contains – jako parametr przyjmuje element listy i zwraca flagę informującą czy dany element już istnieje
            if(registeredServers.contains(os)) return true;
        }
        return false;
    }

    @Override
    public ArrayList<OS> getServers() throws RemoteException {
        return registeredServers;
    }

    public void showAllServers() throws RemoteException {
        System.out.println("Available servers:");
        for(int j=0;j<getServers().size();j++)
        {
            int idx =j+1;
            System.out.println( idx+ ". "+getServers().get(j).getName());
        }
    }

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        Register register = new Register();
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
