package ru.webeffector;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.xml.bind.annotation.XmlAttribute;
import java.util.List;

/**
 * @author Ernest Sadykov
 * @since 12.03.2014
 */
public class Host {
    private Webmaster webmaster;

    @XmlAttribute(name = "href")
    private String url;

    private Links links;

    public Host() {
    }

    Host(Webmaster webmaster) {
        this.webmaster = webmaster;
    }

    private <T> T makeRequest(LinkType linkType, Class<T> clazz) {
        return webmaster.makeRequest(getLinks().get(linkType), clazz);
    }

    public HostStats stats() {
        return makeRequest(LinkType.host_information, HostStats.class);
    }

    public IndexInfo indexed() {
        return makeRequest(LinkType.indexed_urls, IndexInfo.class);
    }

    public TopQueries tops() {
        return makeRequest(LinkType.top_queries, TopQueries.class);
    }

    public IncomingLinks links() {
        return makeRequest(LinkType.incoming_links, IncomingLinks.class);
    }

    public Verification verify() {
        return makeRequest(LinkType.verify_host, Verification.class);
    }

    public ExcludedURLs excluded() {
        return makeRequest(LinkType.excluded_urls, ExcludedURLs.class);
    }

    public List<Sitemap> sitemaps() {   // TODO: fix it
        return makeRequest(LinkType.sitemaps, List.class);
    }

    public History tic() {
        return makeRequest(LinkType.tic_history, History.class);
    }

    public History incomingLinksHistory() {
        return makeRequest(LinkType.incoming_links_history, History.class);
    }

    public History crawledURLsHistory() {
        return makeRequest(LinkType.crawled_urls_history, History.class);
    }

    public History indexedURLsHistory() {
        return makeRequest(LinkType.indexed_urls_history, History.class);
    }

    public History excludedURLsHistory() {
        return makeRequest(LinkType.excluded_urls_history, History.class);
    }

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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("url", url)
                .toString();
    }
}
