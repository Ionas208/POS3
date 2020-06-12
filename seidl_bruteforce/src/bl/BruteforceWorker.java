/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import java.security.MessageDigest;
import java.util.concurrent.Callable;
import javax.swing.JTextArea;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author 10jon
 */
public class BruteforceWorker implements Callable<String>{

    private Person person;
    private JTextArea logArea;
    
    public BruteforceWorker(Person person, JTextArea logArea) {
        this.person = person;
        this.logArea = logArea;
    }
    
    @Override
    public String call() throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        String chars = "abcdefghijklmnopqrstuvwxyz0123456789";
        log("Starting to crack "+person.getFirstname()+" "+person.getLastname()+"'s password");
        long time = System.currentTimeMillis();
        for (int i = 0; i < chars.length(); i++) {
            for (int j = 0; j < chars.length(); j++) {
                for (int k = 0; k < chars.length(); k++) {
                    for (int l = 0; l < chars.length(); l++) {
                        for (int m = 0; m < chars.length(); m++) {
                            String personPart = this.person.getFirstname()+""+this.person.getLastname();
                            String pass = 
                                    chars.charAt(i)+""+chars.charAt(j)+""
                                    + chars.charAt(k)+""+chars.charAt(l)+""
                                    + chars.charAt(m)+"";
                            byte[] possHash = md.digest((personPart+""+pass).getBytes());
                            String hashStr = DatatypeConverter.printHexBinary(possHash).toLowerCase();
                            if(hashStr.equals(this.person.getHash())){
                                time = System.currentTimeMillis() - time;
                                log("Successfully cracked "+person.getFirstname()+" "+person.getLastname()+"'s password in "+time/1000+"s: "+pass);
                                return pass;
                            }
                        }
                    } 
                } 
            }
        }
        return null;
    }

    private void log(String message){
        this.logArea.append(message+"\n");
    }
}
