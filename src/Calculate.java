/*
Filename: Calculate.java         
Author: Kaylin Moodley
Created: 21/09/2020
Operating System: Windows 10
*/

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculate extends Remote
{
    //Return the sum of num1 and num2
    public int add(int num1, int num2) throws RemoteException;
    
    //Return the difference of num1 and num2
    public int subtract(int num1, int num2) throws RemoteException;
    
    //Return the quotient of num1 and num2
    public int divide(int num1, int num2) throws RemoteException;
    
    //Return the product  of num1 and num2
    public int multiply(int num1, int num2) throws RemoteException;
}

