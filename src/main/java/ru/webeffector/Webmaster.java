package ru.webeffector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.webeffector.host.Host;

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
        List<Host> hosts = fetcher.getHosts(accessToken);
        for (Host host : hosts) {
            host.setWebmaster(this);
        }
        return hosts;
    }
}
