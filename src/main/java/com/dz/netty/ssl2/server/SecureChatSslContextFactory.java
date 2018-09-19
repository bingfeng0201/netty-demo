package com.dz.netty.ssl2.server;

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

    private static SSLContext SERVER_CONTEXT;

    static{
        SSLContext sslContext;
        try {
            //服务端keystore文件
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(new FileInputStream("E:\\security\\server\\sChat.jks"),"heyangyang".toCharArray());
            //客户端证书导入到服务端的keystore文件
            KeyStore trustKeyStore = KeyStore.getInstance("JKS");
            trustKeyStore.load(new FileInputStream("E:\\security\\server\\sChat.jks"),"heyangyang".toCharArray());

            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(keyStore,"heyangyang".toCharArray());
            trustManagerFactory.init(trustKeyStore);

            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(keyManagerFactory.getKeyManagers(),trustManagerFactory.getTrustManagers(),null);

            SERVER_CONTEXT = sslContext;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SSLContext getServerContext(){
        return SERVER_CONTEXT;
    }




}
