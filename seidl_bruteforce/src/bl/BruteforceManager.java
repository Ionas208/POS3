/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JTextArea;
import javax.xml.bind.DatatypeConverter;
import ui.BruteforceGUI;

/**
 *
 * @author 10jon
 */
public class BruteforceManager implements Runnable{

    private List<Person> persons;
    public JTextArea logArea;
    private int numPersons;
    private int numThreads;
    
    public BruteforceManager(JTextArea logArea, int numPersons, int numThreads) {
        persons = new ArrayList<>();
        File passwords = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "bl" + File.separator + "passwords.csv");
        InputStream is = null;
        try {
            is = new FileInputStream(passwords);
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
        persons = new BufferedReader(new InputStreamReader(is))
                .lines()
                .skip(1)
                .map(Person::new)
                .collect(Collectors.toList());
        this.logArea = logArea;
        this.numThreads = numThreads;
        this.numPersons = numPersons;
    }

    @Override
    public void run() {
        long time = System.currentTimeMillis();
        ExecutorService pool = Executors.newFixedThreadPool(numThreads);
        List<BruteforceWorker> workers = new ArrayList<>();
        for (int i = 0; i < numPersons; i++) {
            workers.add(new BruteforceWorker(persons.get(i), this.logArea));
        }
        List<Future<String>> results = null;
        try {
            results = pool.invokeAll(workers);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.shutdown();
        time = System.currentTimeMillis() - time;
        log("------------------------------------------------");
        log("Cracked all passwords in "+time/1000+"s");
        BruteforceGUI.running = false;
    }
    
    private void log(String text){
        logArea.append(text+"\n");
    }
}
