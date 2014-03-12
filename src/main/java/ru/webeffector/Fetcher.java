package ru.webeffector;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;

/**
 * @author Ernest Sadykov
 * @since 11.03.2014
 */
class Fetcher {
    private static final Logger logger = LoggerFactory.getLogger(Fetcher.class);

    private JAXBContext jaxbContext;
    private String hostsUrl;
    private CloseableHttpClient httpClient = HttpClients.createDefault();

    private JAXBContext getJaxbContext() {
        if (jaxbContext == null) {
            try {
                jaxbContext = JAXBContext.newInstance(ServiceDocument.class,
                        HostList.class, Links.class, Host.class);
            } catch (JAXBException e) {
                logger.error("Cannot get JAXB context", e);
            }
        }
        return jaxbContext;
    }

    <T> T makeRequest(String url, String accessToken) {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Authorization", "OAuth " + accessToken);
        T result = null;
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            Unmarshaller unmarshaller = getJaxbContext().createUnmarshaller();
            if (response.getStatusLine().getStatusCode() != 200) {
                logger.error("Error during fetching: " + response.getStatusLine().getReasonPhrase());
                return null;
            }
            InputSource inputSource = new InputSource(response.getEntity().getContent());
            result = (T) unmarshaller.unmarshal(inputSource);
            logger.debug("Downloaded content: {}", result);
        } catch (IOException e) {    // TODO: what to do with exceptions?
            logger.error("Exception during downloading. [URL=" + url + "]", e);
        } catch (JAXBException e) {
            logger.error("JAXB error", e);
        }
        return result;
    }
}

