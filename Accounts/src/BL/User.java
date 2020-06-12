/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import java.util.Random;
import javax.swing.JTextArea;

/**
 *
 * @author 10jon
 */
public class User implements Runnable {

    private Account account;
    private int cnt;
    private String name;
    private JTextArea area;
    private static final Random rand = new Random();

    public User(Account account, int cnt, String name, JTextArea area) {
        this.account = account;
        this.cnt = cnt;
        this.name = name;
        this.area = area;
    }

    @Override
    public void run() {
        synchronized(account){
            area.append("-----" + name + " started-----\n");
        }  
        for (int i = 0; i < cnt;) {
            boolean retrieving = rand.nextBoolean();
            int amount = rand.nextInt(50 - 10) + 10;
            if (!retrieving) {
                synchronized (account) {
                    area.append(name + " is now transferring " + amount + "\n");
                    account.setBalance(account.getBalance() + amount);
                    i++;
                    account.notifyAll();
                }
            } else {  
                synchronized (account) {
                    area.append(name + " is now trying to retrieve " + amount + "\n");
                    boolean ableToRetrieve = account.getBalance() >= amount;
                    if(!ableToRetrieve) {
                        area.append("Account balance too low for " + name + "\n");
                        try {
                            area.append(name + " is waiting\n");
                            account.wait(2000);
                            ableToRetrieve = account.getBalance() >= amount;
                            area.append(name+" has finished waiting\n");
                        } catch (InterruptedException ex) {
                        }
                    }
                    if (ableToRetrieve) {
                        account.setBalance(account.getBalance() - amount);
                        area.append(name + " successfully retrieved " + amount + "\n");
                        i++;
                        account.notifyAll();
                    } else {
                        area.append(name + " was not able to retrieve\n");
                    }
                }
            }
            try {
                Thread.sleep(rand.nextInt(1000));
            } catch (InterruptedException ex) {
            }
        }
        synchronized(account){
            area.append("-----" + name + " finished-----\n");
        }
    }

    @Override
    public String toString() {
        return this.name + "";
    }

}
