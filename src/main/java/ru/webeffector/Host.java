package ru.webeffector;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * @author Ernest Sadykov
 * @since 12.03.2014
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Host {
    private Webmaster webmaster;

    @XmlAttribute(name = "href")
    private String url;

    @XmlElement
    private String name;

    private Links links;

    public Host() {
    }

    Host(Webmaster webmaster) {
        this.webmaster = webmaster;
    }

    private <T> T makeRequest(LinkType linkType, Class<T> clazz) {
        return webmaster.makeRequest(getLinks().get(linkType), clazz);
    }

    /**
     * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/hosts-stats.xml
     * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/hosts-stats.xml
     * @return
     */
    public HostStats stats() {
        return makeRequest(LinkType.host_information, HostStats.class);
    }

    /**
     * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/hosts-indexed.xml
     * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/hosts-indexed.xml
     * @return
     */
    public IndexInfo indexed() {
        return makeRequest(LinkType.indexed_urls, IndexInfo.class);
    }

    /**
     * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/host-tops.xml
     * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/host-tops.xml
     * @return
     */
    public Tops tops() {
        return makeRequest(LinkType.top_queries, Tops.class);
    }

    /**
     * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/host-links.xml
     * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/host-links.xml
     * @return
     */
    public IncomingLinks links() {
        return makeRequest(LinkType.incoming_links, IncomingLinks.class);
    }

    /**
     * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/hosts-verify.xml
     * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/hosts-verify.xml
     * @return
     */
    public Verification verify() {
        return makeRequest(LinkType.verify_host, Verification.class);
    }

    /**
     * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/hosts-errors.xml
     * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/hosts-errors.xml
     * @return
     */
    public ExcludedURLs excluded() {
        return makeRequest(LinkType.excluded_urls, ExcludedURLs.class);
    }

    /**
     * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/sitemaps.xml
     * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/sitemaps.xml
     * @return
     */
    public List<Sitemap> sitemaps() {   // TODO: fix it
        return makeRequest(LinkType.sitemaps, List.class);
    }

    /**
     * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/history-tic.xml
     * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/history-tic.xml
     * @return
     */
    public History tic() {
        return makeRequest(LinkType.tic_history, History.class);
    }

    /**
     * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/history-incoming-links.xml
     * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/history-incoming-links.xml
     * @return
     */
    public History incomingLinksHistory() {
        return makeRequest(LinkType.incoming_links_history, History.class);
    }

    /**
     * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/history-crawled-urls.xml
     * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/history-crawled-urls.xml
     * @return
     */
    public History crawledURLsHistory() {
        return makeRequest(LinkType.crawled_urls_history, History.class);
    }

    /**
     * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/history-indexed-urls.xml
     * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/history-indexed-urls.xml
     * @return
     */
    public History indexedURLsHistory() {
        return makeRequest(LinkType.indexed_urls_history, History.class);
    }

    /**
     * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/history-excluded-urls.xml
     * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/history-excluded-urls.xml
     * @return
     */
    public History excludedURLsHistory() {
        return makeRequest(LinkType.excluded_urls_history, History.class);
    }

    /**
     * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/host-original-texts.xml
     * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/host-original-texts.xml
     * @return
     */
    public List<OriginalText> originalTexts() { // TODO: fix it
        return makeRequest(LinkType.original_texts, List.class);
    }

    private Links getLinks() {
        if (links == null) {
            links = webmaster.makeRequest(url, Links.class);
        }
        return links;
    }

    public String getUrl() {
        return url;
    }

    void setWebmaster(Webmaster webmaster) {
        this.webmaster = webmaster;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("url", url)
                .toString();
    }
}
