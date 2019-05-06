package app;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IRegister extends Remote {
    /*
    Metodę bool register( OS ), gdzie OS może być prostą klasą przechowującą dwa Stringi: nazwę rejestrowanego serwera
    i opis implementowanego przez niego algorytmu rozwiązania problemu plecakowego. Metoda powinna rejestrować podany serwer w spisie
    i zwracać wynik true/false, w zależności od tego czy operacja się udała.

    Metodę List<OS> getServers(), która zwraca listę serwerów (tj. listę par nazwa-opis algorytmu).
     */
    boolean register( OS os ) throws RemoteException;
    ArrayList<OS> getServers() throws RemoteException;
}
