package app;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Register  implements IRegister{

    private ArrayList<OS> registeredServers = new ArrayList<>();

    public Register(){

    }

    @Override
    public boolean register(OS os) throws RemoteException {
        if(os!=null)
        {
            System.out.println("Registered: " + os.getName());
            registeredServers.add(os);
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
        for(int j=0;j< getServers().size();j++)
        {
            System.out.println(getServers().get(j).getName());
        }
    }

    public static void main(String[] args){
        Register register = new Register();
    }
}
