/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import java.util.Arrays;
import java.util.concurrent.Callable;

/**
 *
 * @author 10jon
 */
public class XORWorker implements Callable<String>{
    char[] key = new char[3];

    public XORWorker(char a, char b, char c) {
        key[0] = a;
        key[1] = b;
        key[2] = c;
    }

    @Override
    public String call() throws Exception {
        char[] text = XORLauncher.text;
        char[] result = new char[text.length];
        for (int i = 0; i < text.length; i++) {
            result[i] = (char)(text[i] ^ key[i%3]);
        }
        return String.valueOf(result);
    }
    
    
}
