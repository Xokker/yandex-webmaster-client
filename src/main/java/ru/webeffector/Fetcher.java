package ru.webeffector;

import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.webeffector.exception.WebmasterException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;

/**
 * Fetches information from the the given source and maps it to the given class.
 *
 * @author Ernest Sadykov
 * @since 11.03.2014
 */
class Fetcher {
    private static final Logger logger = LoggerFactory.getLogger(Fetcher.class);

    private JAXBContext context;
    private XMLInputFactory factory = XMLInputFactory.newInstance();
    private CloseableHttpClient httpClient = HttpClients.createDefault();

    private JAXBContext getJaxbContext() {
        if (context == null) {
            try {
                context = JAXBContext.newInstance(ServiceDocument.class,
                        HostList.class, Links.class, Host.class, HostStats.class,
                        IndexInfo.class, Verification.class, Tops.class);
            } catch (JAXBException e) {
                logger.error("Cannot get JAXB context", e);
            }
        }
        return context;
    }

    private void checkRespone(StatusLine statusLine) {
        if (statusLine.getStatusCode() != 200) {
            throw WebmasterException.newException(statusLine.getStatusCode(),
                    statusLine.getReasonPhrase());
        }
    }

    <T> T makeRequest(String url, String accessToken, Class<T> clazz) {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Authorization", "OAuth " + accessToken);
        T result = null;
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            Unmarshaller unmarshaller = getJaxbContext().createUnmarshaller();

            checkRespone(response.getStatusLine());

            XMLStreamReader reader = null;
            try {
                reader = factory.createXMLStreamReader(response.getEntity().getContent());
            } catch (XMLStreamException e) {
                logger.error("Error during creating XMLStreamReader.", e);
            }
            result =  unmarshaller.unmarshal(reader, clazz).getValue();

            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (XMLStreamException e) {
                logger.error("Error during closing XMLStreamReader", e);
            }

            logger.debug("Downloaded content: {}", result);
        } catch (IOException e) {
            logger.error("Exception during downloading. [URL=" + url + "]", e);
        } catch (JAXBException e) {
            logger.error("JAXB error", e);
        }
        return result;
    }
}

