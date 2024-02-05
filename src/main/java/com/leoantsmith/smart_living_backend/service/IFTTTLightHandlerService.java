package com.leoantsmith.smart_living_backend.service;

import com.leoantsmith.smart_living_backend.model.exceptions.RestConnectionException;
import com.leoantsmith.smart_living_backend.model.helper.RequestType;
import com.leoantsmith.smart_living_backend.service.intf.ILightHandlerService;
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
     * @return the open connection
     * @throws IOException if the connection fails
     */
    private HttpURLConnection openConnection(String url) throws IOException {

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod(RequestType.POST.name());
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
            throw new RestConnectionException("Returned with incorrect response code: " + connection.getResponseCode());
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
            HttpURLConnection httpURLConnection = openConnection(url);
            validateConnection(httpURLConnection);
        } catch (IOException e) {
            throw new RestConnectionException(e.toString());
        }
    }

    @Override
    public void turnOnLight(String url) {
        logger.log(Level.INFO,"Sending Request to Turn On light");
        triggerUrl(url);
    }

    @Override
    public void turnOffLight(String url) {
        logger.log(Level.INFO,"Sending Request to Turn Off light");
        triggerUrl(url);
    }

}