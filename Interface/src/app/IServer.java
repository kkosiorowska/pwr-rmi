package app;

import knapSack.Instance;
import knapSack.Solution;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends Remote {
    /*
    Aplikacja serwera powinna implementować metodę S solve( I ),
    do wywołania poprzez RMI, gdzie I jest jakąś klasą reprezentującą instancję problemu plecakowego,
    zaś S jest jakąś klasą zawierającą rozwiązanie problemu plecakowego.
     */
    Solution solve(Instance instance) throws RemoteException;
}
