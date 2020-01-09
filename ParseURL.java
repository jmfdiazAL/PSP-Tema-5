import java.net.*;
import java.io.*;

class ParseURL {
    public static void main(String[] args) {
        URL aURL = null;
        try {
            aURL = new URL("http://java.sun.com:80/tutorial/intro.html#DOWNLOADING");
            System.out.println("protocol = " + aURL.getProtocol());
            System.out.println("host = " + aURL.getHost());
            System.out.println("filename = " + aURL.getFile());
            System.out.println("port = " + aURL.getPort());
            System.out.println("ref = " + aURL.getRef());
        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException: " + e);
        }
    }
}
