package ru.webeffector;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
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
import java.util.List;

/**
 * @author Ernest Sadykov
 * @since 11.03.2014
 */
class Fetcher {
    private static final Logger logger = LoggerFactory.getLogger(Fetcher.class);
    private static final String WEBMASTER_API_URL = "https://webmaster.yandex.ru/api/v2";

    private JAXBContext jaxbContext;
    private String hostsUrl;
    private CloseableHttpClient httpClient = HttpClients.createDefault();

    private JAXBContext getJaxbContext() {
        if (jaxbContext == null) {
            try {
                jaxbContext = JAXBContext.newInstance(ServiceDocument.class, HostList.class);
            } catch (JAXBException e) {
                logger.error("Cannot get JAXB context", e);
            }
        }
        return jaxbContext;
    }

    private <T> T makeRequest(String url, String accessToken) {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Authorization", "OAuth " + accessToken);
        T result = null;
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            Unmarshaller unmarshaller = getJaxbContext().createUnmarshaller();
            if (response.getStatusLine().getStatusCode() != 200) {
                logger.error("Error during fetching: " + response.getStatusLine().getReasonPhrase());
                return null;
            }
            result = (T)unmarshaller.unmarshal(response.getEntity().getContent());
            logger.debug("Downloaded content: {}", result);
        } catch (IOException e) {    // TODO: what to do with exceptions?
            logger.error("Exception during downloading. [URL=" + url + "]", e);
        } catch (JAXBException e) {
            logger.error("JAXB error", e);
        }
        return result;
    }

    private String getHostsUrl() {
        ServiceDocument serviceDocument = makeRequest(WEBMASTER_API_URL, "");
        return serviceDocument == null ? null : serviceDocument.getHostsUrl();
    }

    /**
     * Downloads list of hosts.
     *
     * @param accessToken OAuth 2.0 access token
     * @return list of fetched hosts
     */
    List<Host> getHosts(String accessToken) {
        HostList hostList = makeRequest(getHostsUrl(), accessToken);
        return hostList.hosts;
    }
}

@XmlRootElement(name = "hostlist")
class HostList {

    @XmlElement(name = "host")
    List<Host> hosts;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("hosts", hosts)
                .toString();
    }
}
