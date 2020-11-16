/*
Filename: Server.java         
Author: Kaylin Moodley
Created: 21/09/2020
Operating System: Windows 10
*/

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

public class Server implements Calculate
{
    //Constructor
    public Server()throws RemoteException
    {
        super();
    }

    //Implement the add method from the Calculate interface
    @Override
    public int add(int num1, int num2) throws RemoteException 
    {
        return num1+num2;
    }
    
    //Implement the subtract method from the Calculate interface
    @Override
    public int subtract(int num1, int num2) throws RemoteException
    {
        return num1-num2;
    }
    
    //Implement the add divide from the Calculate interface
    @Override
    public int divide(int num1, int num2) throws RemoteException
    {
        return num1/num2;
    }
    //Implement the multiply method from the Calculate interface
    @Override
    public int multiply(int num1, int num2) throws RemoteException
    {
        return num1*num2;
    }
    
    public static void main (String args[]) throws Exception 
    {    
        try
        {
            Server server = new Server();
            Calculate stub =(Calculate)UnicastRemoteObject.exportObject(server, 0);
            Registry registry = LocateRegistry.createRegistry(8080); 
            registry.rebind("Calculate", stub); 
            System.out.println("Server is ready");

        }catch (Exception e) 
        {
            System.out.println("Server not connected "+e);
            e.printStackTrace();
        }

    }
    
}
