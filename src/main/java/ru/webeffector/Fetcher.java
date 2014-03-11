package ru.webeffector;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.webeffector.host.Host;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @author Ernest Sadykov
 */
class Fetcher {
    private static final Logger logger = LoggerFactory.getLogger(Fetcher.class);
    private static final String WEBMASTER_API_URL = "https://webmaster.yandex.ru/api/v2";

    private static JAXBContext jaxbContext;
    private static String hostsUrl;
    private static CloseableHttpClient httpClient = HttpClients.createDefault();

    private Fetcher() { }

    private static JAXBContext getJaxbContext() {
        if (jaxbContext == null) {
            try {
                jaxbContext = JAXBContext.newInstance(ServiceDocument.class, HostList.class);
            } catch (JAXBException e) {
                logger.error("Cannot get JAXB context", e);
            }
        }
        return jaxbContext;
    }

    private static String getHostsUrl() {
        if (hostsUrl == null) {
            HttpGet httpGet = new HttpGet(WEBMASTER_API_URL);
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                Unmarshaller unmarshaller = getJaxbContext().createUnmarshaller();
                ServiceDocument serviceDocument = (ServiceDocument)unmarshaller.unmarshal(response.getEntity().getContent());
                hostsUrl = serviceDocument.getHostsUrl();
                logger.debug("Hosts url: {}", hostsUrl);
            } catch (IOException e) {   // TODO: what to do with exceptions?
                logger.error("", e);
                return null;
            } catch (JAXBException e) {
                logger.error("jaxb error", e);
                return null;
            }
        }
        return hostsUrl;
    }

    /**
     * Downloads list of hosts.
     *
     * @param accessToken OAuth 2.0 access token
     * @return list of fetched hosts
     */
    static List<Host> getHosts(String accessToken) {
        HttpGet httpGet = new HttpGet(getHostsUrl());
        httpGet.setHeader("Authorization", "OAuth " + accessToken);
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            if (response.getStatusLine().getStatusCode() != 200) {
                logger.error("Error during fetching: " + response.getStatusLine().getReasonPhrase());
                return null;
            }

            HostList hostList = (HostList)unmarshaller.unmarshal(response.getEntity().getContent());
            logger.debug("Hosts: {}", hostList.hosts);
            return hostList.hosts;
        } catch (IOException e) {    // TODO: what to do with exceptions?
            logger.error("xkjn", e);
        } catch (JAXBException e) {
            logger.error("jaxb error", e);
        }
        return null;
    }

    public static void main(String[] args) {
        final String serviceDocumentUrl = "https://webmaster.yandex.ru/api/v2";
        String token = "sdfdsfs";

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(serviceDocumentUrl);
        httpGet.addHeader("Authorization", "OAuth " + token);
        String hostsUrl;
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            JAXBContext jc = JAXBContext.newInstance(ServiceDocument.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();

            ServiceDocument serviceDocument = (ServiceDocument)unmarshaller.unmarshal(response.getEntity().getContent());
            hostsUrl = serviceDocument.getHostsUrl();
        } catch (IOException e) {
            logger.error("xkjn", e);
            return;
        } catch (JAXBException e) {
            logger.error("jaxb error", e);
            return;
        }

        List<Host> hosts;

        try {
            httpGet.setURI(new URI(hostsUrl));
        } catch (URISyntaxException e) {
            logger.error("should not happened", e);
            return;
        }
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            JAXBContext jc = JAXBContext.newInstance(HostList.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();

            HostList hostList = (HostList)unmarshaller.unmarshal(response.getEntity().getContent());
            hosts = hostList.hosts;
            logger.debug("Hosts: {}", hosts);
        } catch (IOException e) {
            logger.error("xkjn", e);
        } catch (JAXBException e) {
            logger.error("jaxb error", e);
        }
    }
}

@XmlRootElement(name = "hostlist")
class HostList {
    @XmlElement(name = "host")
    List<Host> hosts;
}
