package ru.webeffector;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.joda.time.DateTime;
import org.joda.time.Interval;
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
import java.util.ArrayList;
import java.util.List;

/**
 * That class is used for retrieving information about hosts of the given
 * client. Class is not responsible for authentication.
 *
 * @author Ernest Sadykov
 */
public class Webmaster {
    private final static Logger logger = LoggerFactory.getLogger(Webmaster.class);

    private final String accessToken;
    private final DateTime expirationDate;
    private List<Host> hosts;

    /**
     * Creates Webmaster instance with given token and expiration date.
     *
     * @param accessToken OAuth 2.0 access token
     * @param expirationDate expiration date of the token,
     *                       null if token is permanent
     */
    public Webmaster(String accessToken, DateTime expirationDate) {
        this.accessToken = accessToken;
        if (expirationDate == null) {
            this.expirationDate = new DateTime().year().withMaximumValue();
        } else {
            this.expirationDate = expirationDate;
        }
    }

    /**
     * Creates Webmaster instance with given token and it's lifetime.
     *
     * @param accessToken OAuth 2.0 access token
     * @param expiresIn lifetime of token (in seconds)
     */
    public Webmaster(String accessToken, int expiresIn) { // TODO: check expiresIn param
        this(accessToken, new DateTime().plusSeconds(expiresIn));
    }

    /**
     * Creates Webmaster instance with permanent token.
     *
     * @param accessToken OAuth 2.0 access token
     */
    public Webmaster(String accessToken) {
        this(accessToken, null);
    }

    /**
     * Returns list of hosts. If hosts are not downloaded, method
     * downloads them.
     *
     * @return list of hosts
     */
    public List<Host> getHosts() {
        if (hosts == null) {
            hosts = Fetcher.getHosts(accessToken);
        }
        return hosts;
    }

    public static void main(String[] args) {
        Webmaster webmaster = new Webmaster("sdgsdg");
        List<Host> hosts = webmaster.getHosts();
        System.out.println("Hosts: " + hosts);
    }
}
