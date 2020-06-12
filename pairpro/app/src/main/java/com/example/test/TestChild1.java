package com.example.test;

import android.net.Uri;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TestChild1 extends TestObject {
    private transient Uri url;


    public TestChild1(String name, Uri url) {
        super(name);
        this.url = url;
    }

    public Uri getUrl() {
        return url;
    }

    public void setUrl(Uri url) {
        this.url = url;
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeUTF(url.toString());
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        try {
            this.url = Uri.parse(ois.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
