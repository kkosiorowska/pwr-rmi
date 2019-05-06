package app;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Register  implements IRegister{

    @Override
    public boolean register(OS os) throws RemoteException {
        return false;
    }

    @Override
    public ArrayList<OS> getServers() throws RemoteException {
        return null;
    }
}
