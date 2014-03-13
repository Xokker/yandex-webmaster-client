package ru.webeffector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * That class is used for retrieving information about hosts of the given
 * client. Class is not responsible for authentication.
 *
 * @author Ernest Sadykov
 * @since 10.03.2014
 */
public class Webmaster {
    private final static Logger logger = LoggerFactory.getLogger(Webmaster.class);
    private static final String WEBMASTER_API_URL = "https://webmaster.yandex.ru/api/v2";

    private final String accessToken;
    private final Fetcher fetcher;

    /**
     * Creates Webmaster instance with permanent token.
     *
     * @param accessToken OAuth 2.0 access token
     */
    public Webmaster(String accessToken) {
        this.accessToken = accessToken;
        this.fetcher = new Fetcher();
    }

    private String getHostsUrl() {
        ServiceDocument serviceDocument = makeRequest(WEBMASTER_API_URL, ServiceDocument.class);
        return serviceDocument == null ? null : serviceDocument.getHostsUrl();
    }

    /**
     * Returns list of hosts. If hosts are not downloaded, method
     * downloads them.
     *
     * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/hosts.xml
     * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/hosts.xml
     *
     * @return list of hosts
     */
    public List<Host> getHosts() {
        HostList hostList = makeRequest(getHostsUrl(), HostList.class);
        List<Host> hosts = hostList.hosts;
        for (Host host : hosts) {
            host.setWebmaster(this);
        }
        return hosts;
    }

    <T> T makeRequest(String url, Class<T> clazz) {
        return fetcher.makeRequest(url, accessToken, clazz);
    }
}
