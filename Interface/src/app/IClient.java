package app;

import knapSack.Instance;
import java.rmi.RemoteException;

public interface IClient {
    /*
    Aplikacje klienckie powinny posiadać interfejs umożliwiający
    wyświetlenie obecnie zarejestrowanych w spisie serwerów, wygenerowania
    losowej instancji problemu plecakowego (wraz z jej wyświetleniem w konsoli)
    oraz rozwiązania jej poprzez zadany serwer (wraz z wyświetleniem wyniku na ekranie klienta).
     */
    void getAllServers() throws RemoteException;
    Instance genarateInstance() throws RemoteException;
    void solveProblem() throws RemoteException;
}
