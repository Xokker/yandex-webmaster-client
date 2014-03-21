package ru.webeffector;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import ru.webeffector.exception.ForbiddenException;
import ru.webeffector.model.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * Represents information about user's site.
 *
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

    Host() {
    }

    Host(Webmaster webmaster) {
        this.webmaster = webmaster;
    }

    private <T> T makeRequest(LinkType linkType, Class<T> clazz) {
        String url = getLinks().get(linkType);
        if (url == null) {
            throw new ForbiddenException("Application does not have permission" +
                    " for operation " + linkType);
        }
        return webmaster.makeRequest(url, clazz);
    }

    /**
     * Gets statistics about the site's current state of indexing by Yandex.
     *
     * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/hosts-stats.xml
     * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/hosts-stats.xml
     *
     * @return statistics of this host
     *
     * @throws ru.webeffector.exception.ForbiddenException if application does
     *       not have rights for performing request
     */
    public HostStats stats() {
        return makeRequest(LinkType.host_information, HostStats.class);
    }

    /**
     * Gets information about site pages that participate in search.
     *
     * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/hosts-indexed.xml
     * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/hosts-indexed.xml
     *
     * @return index information about this host
     *
     * @throws ru.webeffector.exception.ForbiddenException if application does
     *       not have rights for performing request
     */
    public IndexInfo indexed() {
        return makeRequest(LinkType.indexed_urls, IndexInfo.class);
    }

    /**
     * Gets information about popular queries that brought visitors to the site
     * over the past week.
     *
     * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/host-tops.xml
     * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/host-tops.xml
     *
     * @return object that aggregates information about popular queries and clicks
     *
     * @throws ru.webeffector.exception.ForbiddenException if application does
     *       not have rights for performing request
     */
    public Tops tops() {
        return makeRequest(LinkType.top_queries, Tops.class);
    }

    /**
     * Gets information about external links to the site that were detected by
     * the robot over the past week.
     *
     * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/host-links.xml
     * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/host-links.xml
     *
     * @return objects that represents information about external links
     *
     * @throws ru.webeffector.exception.ForbiddenException if application does
     *       not have rights for performing request
     */
    public IncomingLinks links() {
        return makeRequest(LinkType.incoming_links, IncomingLinks.class);
    }

    /**
     * Gets details about the current state of site verification on Yandex.Webmaster.
     *
     * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/hosts-verify.xml
     * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/hosts-verify.xml
     *
     * @return verification info
     *
     * @throws ru.webeffector.exception.ForbiddenException if application does
     *       not have rights for performing request
     */
    public Verification verify() {
        return makeRequest(LinkType.verify_host, Verification.class);
    }

    /**
     * Gets information about site pages that for some reason were excluded
     * by the robot from the list of indexed pages.
     *
     * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/hosts-errors.xml
     * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/hosts-errors.xml
     *
     * @return urls that were excluded from index because of errors
     *
     * @throws ru.webeffector.exception.ForbiddenException if application does
     *       not have rights for performing request
     */
    public HostErrors excluded() {
        return makeRequest(LinkType.excluded_urls, HostErrors.class);
    }

    /**
     * Gets a list of a user site's Sitemap files and information about each file.
     *
     * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/sitemaps.xml
     * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/sitemaps.xml
     *
     * @return list of sitemaps
     *
     * @throws ru.webeffector.exception.ForbiddenException if application does
     *       not have rights for performing request
     */
    public List<Sitemap> sitemaps() {
        List<Sitemap> sitemaps =
                makeRequest(LinkType.sitemaps, SitemapList.class).sitemaps;
        for (Sitemap sitemap : sitemaps) {
            sitemap.setWebmaster(webmaster);
        }
        return sitemaps;
    }

    /**
     * Gets the history of changes to the site's TIC (Citation Index) value over
     * the past six months, including the current month. For each month,
     * the list contains dates when the TIC value changed. If the TIC did not
     * change in a certain month, the list will have a single entry for this
     * month with the value of the TIC at that time.
     *
     * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/history-tic.xml
     * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/history-tic.xml
     *
     * @return tcy history
     *
     * @throws ru.webeffector.exception.ForbiddenException if application does
     *       not have rights for performing request
     */
    public History tic() {
        return makeRequest(LinkType.tic_history, History.class);
    }

    /**
     * Gets the history of changes to the number of incoming links to the
     * site over six months.
     *
     * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/history-incoming-links.xml
     * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/history-incoming-links.xml
     *
     * @return history of external links which refer to user's host
     *
     * @throws ru.webeffector.exception.ForbiddenException if application does
     *       not have rights for performing request
     */
    public History incomingLinksHistory() {
        return makeRequest(LinkType.incoming_links_history, History.class);
    }

    /**
     * Gets the history of changes to the number of site URLs accessed by the
     * Yandex robot over the past six months.
     *
     * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/history-crawled-urls.xml
     * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/history-crawled-urls.xml
     *
     * @return crawling history
     *
     * @throws ru.webeffector.exception.ForbiddenException if application does
     *       not have rights for performing request
     */
    public History crawledURLsHistory() {
        return makeRequest(LinkType.crawled_urls_history, History.class);
    }

    /**
     * Gets the history of changes to the number of indexed URLs on the site
     * over the past six months.
     *
     * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/history-indexed-urls.xml
     * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/history-indexed-urls.xml
     *
     * @return history of page indexation
     *
     * @throws ru.webeffector.exception.ForbiddenException if application does
     *       not have rights for performing request
     */
    public History indexedURLsHistory() {
        return makeRequest(LinkType.indexed_urls_history, History.class);
    }

    /**
     * Gets the history of changes to site URLs excluded by the Yandex robot
     * over six months.
     *
     * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/history-excluded-urls.xml
     * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/history-excluded-urls.xml
     *
     * @return history of excluded from history urls
     *
     * @throws ru.webeffector.exception.ForbiddenException if application does
     *       not have rights for performing request
     */
    public History excludedURLsHistory() {
        return makeRequest(LinkType.excluded_urls_history, History.class);
    }

    /**
     * Gets a list of all original texts from the site that were added
     * to Yandex.Webmaster.
     *
     * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/host-original-texts.xml
     * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/host-original-texts.xml
     *
     * @return original texts that were uploaded to yandex.webmaster
     *
     * @throws ru.webeffector.exception.ForbiddenException if application does
     *       not have rights for performing request
     */
    public OriginalTexts originalTexts() {
        return makeRequest(LinkType.original_texts, OriginalTexts.class);
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
