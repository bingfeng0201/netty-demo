package com.dz.netty.ssl;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLEngine;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.logging.SocketHandler;

/**
 * 单向连接
 * Created by heyangyang on 2018/9/17.
 */
public class SslChannelInitializer extends ChannelInitializer<SocketChannel> {

    private SslContext sslContext;

    public SslChannelInitializer(){
        System.out.println("-----------sslChannleInitializer--------------");
        String keyStoreFilePath = "E:\\security\\server\\sChat.jks";
        String keyStorePassword = "heyangyang";

        try {
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(new FileInputStream(keyStoreFilePath),keyStorePassword.toCharArray());
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(keyStore,keyStorePassword.toCharArray());
            sslContext = SslContextBuilder.forServer(keyManagerFactory).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        SSLEngine sslEngine = sslContext.newEngine(ch.alloc());
        pipeline.addLast(new SslHandler(sslEngine))
                .addLast("decoder",new HttpRequestDecoder())
                .addLast("encoder",new HttpResponseEncoder())
                .addLast("aggregator",new HttpObjectAggregator(512 * 1024))
                .addLast("handler",new HttpHandler());
    }
}
