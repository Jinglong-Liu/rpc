package cn.edu.nju.rpc.transport.impl;

import cn.edu.nju.rpc.Peer;
import cn.edu.nju.rpc.transport.TransportClient;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPTransportClient implements TransportClient {
    private String url;
    @Override
    public void connect(Peer peer) {
        this.url = "http://" + peer.getHost()+":"+peer.getPort();
    }

    @Override
    public InputStream write(InputStream data) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod("POST");

            connection.connect();
            IOUtils.copy(data,connection.getOutputStream());

            int resultCode = connection.getResponseCode();
            if(resultCode == HttpURLConnection.HTTP_OK){
                return connection.getInputStream();
            } else{
                connection.getErrorStream();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void close() {

    }
}
