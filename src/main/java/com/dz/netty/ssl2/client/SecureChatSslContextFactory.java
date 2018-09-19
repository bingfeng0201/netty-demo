package com.dz.netty.ssl2.client;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;

/**
 *
 * Created by heyangyang on 2018/9/17.
 */
public class SecureChatSslContextFactory {

    private static SSLContext CLIENT_CONTEXT;

    static{
        SSLContext sslContext = null;
        try {
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(new FileInputStream("E:\\security\\client\\cChat.jks"),"heyangyang".toCharArray());
            KeyStore trustKeyStore = KeyStore.getInstance("JKS");
            trustKeyStore.load(new FileInputStream("E:\\security\\client\\cChat.jks"),"heyangyang".toCharArray());

            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(keyStore,"heyangyang".toCharArray());
            trustManagerFactory.init(trustKeyStore);

            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(keyManagerFactory.getKeyManagers(),trustManagerFactory.getTrustManagers(),null);


        } catch (Exception e) {
            e.printStackTrace();
        }
        CLIENT_CONTEXT = sslContext;

    }

    public static SSLContext getClientContext(){
        return CLIENT_CONTEXT;
    }

}
