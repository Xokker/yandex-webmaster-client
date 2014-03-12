package ru.webeffector;

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

    protected Host(Webmaster webmaster) {
        this.webmaster = webmaster;
    }

    private <T> T makeRequest(LinkType linkType) {
        return webmaster.makeRequest(getLinks().get(linkType));
    }

    public HostStats stats() {
        return makeRequest(LinkType.host_information);
    }

    public IndexInfo indexed() {
        return makeRequest(LinkType.indexed_urls);
    }

    public TopQueries tops() {
        return makeRequest(LinkType.top_queries);
    }

    public IncomingLinks links() {
        return makeRequest(LinkType.incoming_links);
    }

    public Verification verify() {
        return makeRequest(LinkType.verify_host);
    }

    public ExcludedURLs excluded() {
        return makeRequest(LinkType.excluded_urls);
    }

    public List<Sitemap> sitemaps() {
        return makeRequest(LinkType.sitemaps);
    }

    public History tic() {
        return makeRequest(LinkType.tic_history);
    }

    public History incomingLinksHistory() {
        return makeRequest(LinkType.incoming_links_history);
    }

    public History crawledURLsHistory() {
        return makeRequest(LinkType.crawled_urls_history);
    }

    public History indexedURLsHistory() {
        return makeRequest(LinkType.indexed_urls_history);
    }

    public History excludedURLsHistory() {
        return makeRequest(LinkType.excluded_urls_history);
    }

    public List<OriginalText> originalTexts() {
        return makeRequest(LinkType.original_texts);
    }

    private Links getLinks() {
        if (links == null) {
            links = webmaster.makeRequest(url);
        }
        return links;
    }
}
