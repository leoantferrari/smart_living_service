package com.leoantsmith.SmartLivingBackend.service;

import com.leoantsmith.SmartLivingBackend.model.helper.RequestType;
import com.leoantsmith.SmartLivingBackend.service.intf.ILightHandlerService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class IFTTTLightHandlerService implements ILightHandlerService {
    private final Logger logger = Logger.getLogger(IFTTTLightHandlerService.class.getName());

    /**
     * Opens a HTML Connection
     *
     * @param url url to connect to
     * @param requestType the type of request
     * @return the open connection
     *
     * @throws IOException if the connection fails
     */
    private HttpURLConnection openConnection(String url, RequestType requestType) throws IOException {

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod(requestType.name());
        connection.setInstanceFollowRedirects(false);

        return connection;
    }

    /**
     * Make sure the connection/request was successful
     *
     * @param connection the connection to check
     * @throws IOException if request came back with incorrect code
     */
    private void validateConnection(HttpURLConnection connection) throws IOException {
        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("Returned with incorrect response code: " + connection.getResponseCode());
        }
        logger.log(Level.INFO, "Request was successful and returned with Status Code 200");
    }

    /**
     * Trigger a certain URL
     *
     * @param url URL to trigger
     */
    private void triggerUrl(String url) {
        try {
            HttpURLConnection httpURLConnection = openConnection(url, RequestType.POST);
            validateConnection(httpURLConnection);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void turnOnLight(String url) {
        triggerUrl(url);
    }

    @Override
    public void turnOffLight(String url) {
        triggerUrl(url);
    }

}