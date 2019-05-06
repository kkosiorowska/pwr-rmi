package app;

import abstractClass.AbstractAlgorithm;
import algorithms.Bruteforce;
import algorithms.Greedy;
import algorithms.RandomSolution;
import knapSack.Instance;
import knapSack.Solution;

import java.rmi.RemoteException;

public class Server implements IServer {
    AbstractAlgorithm algorithm;

    public Server(String name, String nameAlg ) {



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

    public static void main(String[] args){
        Server server = new Server("Server", "greedy");
    }
}
