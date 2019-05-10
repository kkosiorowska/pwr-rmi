package app;

import abstractClass.AbstractAlgorithm;
import algorithms.Bruteforce;
import algorithms.Greedy;
import algorithms.RandomSolution;
import knapSack.Instance;
import knapSack.Solution;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements IServer {
    AbstractAlgorithm algorithm;
    IRegister registers;

    public Server(String name, String nameAlg ) throws RemoteException, AlreadyBoundException, NotBoundException {
        System.out.println("Server was created");

        System.out.println("Server port: "+3006);
        IServer server = (IServer) UnicastRemoteObject.exportObject(this,3006 );
        Registry registry = LocateRegistry.getRegistry(Registry.REGISTRY_PORT);
        registry.bind(name, this);
        registers =(IRegister) registry.lookup("Register");

        setAlgorithm(nameAlg);
        OS os = new OS(name,algorithm.showDescription());

        registers.register(os);

    }

    public boolean setAlgorithm(String name) {
        switch (name) {
            case "bruteForce":
                algorithm = new Bruteforce();
                break;
            case "greedy":
                algorithm = new Greedy();
                break;
            case "randomSolution":
                algorithm = new RandomSolution();
                break;
            default:
                System.out.println("Error!");
                return false;
        }
        return true;
    }

    @Override
    public Solution solve(Instance instance) throws RemoteException {
        System.out.println("Resolving problem...");
        if(instance != null){
            Solution solution = algorithm.algorithm(instance);
            System.out.println("Problem solved");
            return solution;
        }
        System.out.println("Instance is null");
        return null;
    }

    public static void main(String[] args) throws RemoteException, NotBoundException, AlreadyBoundException {
        Server server = new Server("Server1", "greedy");
        System.out.println("Neme of server: Server1 ");
        System.out.println("Description of algorithms: Greedy");
    }
}
