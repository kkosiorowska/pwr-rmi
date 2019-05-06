package app;

import knapSack.Instance;
import knapSack.Item;
import knapSack.Solution;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Client implements IClient {

    private  IServer server;
    private  IRegister registers;

    @Override
    public void getAllServers() throws RemoteException {
        for(int i=0;i<registers.getServers().size();i++)
        {
            System.out.println(registers.getServers().get(i).getName());
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
}
