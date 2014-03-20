package ru.webeffector;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.webeffector.exception.ForbiddenException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Ernest Sadykov
 * @since 11.03.2014
 */
public class WebmasterTest {
    private static final Logger logger = LoggerFactory.getLogger(WebmasterTest.class);
    private static List<Host> hosts;
    private static Map<String, Verification> verifications = new HashMap<>();

    @BeforeClass
    public static void setUp() throws Exception {
        String accessToken = System.getProperty("accessToken");
        assertNotNull(accessToken);
        Webmaster webmaster = new Webmaster(accessToken);
        hosts = webmaster.getHosts();
        assertNotNull("Hosts is not fetched", hosts);
    }

    @Test
    public void testBasicInfo() throws Exception {
        for (Host host : hosts) {
            assertNotNull("Url is null", host.getUrl());
            assertNotNull("Name is null", host.getName());
            logger.trace("url: {}", host.getUrl());
        }
    }

    @Test
    public void testVerification() throws Exception {
        for (Host host : hosts) {
            Verification verification = host.verify();
            assertNotNull("Verification is null [" + host.getUrl() + "]",
                    verification);
            verifications.put(host.getName(), verification);
            switch (verification.getVerificationState()) {
                case VERIFIED:
                    assertNotNull("Cancellation possibility is null",
                            verification.getCancellationPossibility());
                    assertNotNull("Verification time is null",
                            verification.getDate());
                    assertNotNull("Verification type is null",
                            verification.getVerificationConfirmationType());
                    assertNotNull("other users exist is null",
                            verification.getOtherUsersExist());
                    break;
                case NEVER_VERIFIED:
                case VERIFICATION_FAILED:
                    assertNotNull("uin is null", verification.getUin());
                    break;

            }
        }
    }

    private Verification getOrPutVerification(Host host) {
        Verification verification = verifications.get(host.getName());
        if (verification == null) {
            verification = host.verify();
            verifications.put(host.getName(), verification);
        }
        return verification;
    }

    @Test
    @Ignore
    public void testHostStats() throws Exception {
        for (Host host : hosts) {
            HostStats hostStats = host.stats();
            assertNotNull("name is null", hostStats.getName());
            Verification verification = getOrPutVerification(host);
            if (verification.getVerificationState() == VerificationState.VERIFIED) {
                assertNotNull("virused is null", hostStats.getVirused());
                assertNotNull("last access is null", hostStats.getLastAccess());
                Crawling crawling = hostStats.getCrawling();
                assertNotNull("crawling is null", crawling);
                assertNotNull("crawling state is null", crawling.getCrawlingState());
                if (crawling.getCrawlingState() == CrawlingState.INDEXED) {
                    assertNotNull("index count is null", hostStats.getIndexCount());
                    assertNotNull("internal links count is null", hostStats.getInternalLinksCount());
                    assertNotNull("links count is null", hostStats.getLinksCount());
                    assertNotNull("tcy is null", hostStats.getTcy());
                    assertNotNull("url count is null", hostStats.getUrlCount());
                    assertNotNull("url errors is null", hostStats.getUrlErrors());
                } else if (crawling.getCrawlingState() == CrawlingState.NOT_INDEXED) {
                    assertNotNull("crawling denial details is null",
                            crawling.getCrawlingDenialDetails());
                }
            }
        }
    }

    @Test
    @Ignore
    public void testIndexInfo() throws Exception {
        for (Host host : hosts) {
            Verification verification = getOrPutVerification(host);
            if (verification.getVerificationState() == VerificationState.VERIFIED) {
                IndexInfo indexInfo = host.indexed();
                if (indexInfo != null) {
                    assertNotNull("indexCount is not initialized", indexInfo.getIndexCount());
                    List<String> urls = indexInfo.getLastWeekIndexUrls();
                    assertNotNull("links is not fetched", urls);
                    for (String url : urls) {
                        assertNotNull("Url is null", url);
                    }
                }
            }
        }
    }

    private void testTopEntities(List<TopEntity> entities) {
        for (TopEntity topEntity : entities) {
            assertNotNull("query text is null", topEntity.getQueryText());
            assertNotNull("query count is null", topEntity.getCount());
            assertNotNull("query position is null", topEntity.getPosition());
            assertNotNull("query's parameter 'is custom' is null", topEntity.isCustom());
        }
    }

    @Test
    @Ignore
    public void testTops() throws Exception {
        for (Host host : hosts) {
            Verification verification = getOrPutVerification(host);
            if (verification.getVerificationState() == VerificationState.VERIFIED) {
                Tops tops = host.tops();
                logger.trace("tops [Host={},{}]: {}", host.getName(),
                        host.getUrl(), tops);
                assertNotNull("tops is not fetched", tops);
                assertNotNull("total shows count is null", tops.getTotalShowsCount());
                assertNotNull("top shows percent is null", tops.getTopShowsPercent());
                testTopEntities(tops.getTopShows());
                assertNotNull("total clicks count is null", tops.getTotalClicksCount());
                assertNotNull("top clicks percent is null", tops.getTopClicksPercent());
                testTopEntities(tops.getTopClicks());
            }
        }
    }

    @Test
    @Ignore
    public void testIncomingLinks() throws Exception {
        for (Host host : hosts) {
            Verification verification = getOrPutVerification(host);
            if (verification.getVerificationState() == VerificationState.VERIFIED) {
                IncomingLinks incomingLinks;
                try {
                    incomingLinks = host.links();
                } catch (ForbiddenException ignored) {
                    // that's ok if application does not have that right
                    continue;
                }
                logger.trace("external links [Host: {}]: {}", host.getUrl(), incomingLinks);
                assertNotNull("links count is null", incomingLinks.getLinksCount());
                for (String url : incomingLinks.getLastWeekLinks()) {
                    assertNotNull("url is null", url);
                }
            }
        }
    }

    @Test
    @Ignore
    public void testHostErrors() throws Exception {
        for (Host host : hosts) {
            Verification verification = getOrPutVerification(host);
            if (verification.getVerificationState() == VerificationState.VERIFIED) {
                HostErrors hostErrors = host.excluded();
                assertNotNull("total errors count is null",
                        hostErrors.getUrlErrorsTotalCount());
                for (UrlErrorsInfo urlErrorsInfo : hostErrors.getUrlErrors().values()) {
                    assertNotNull("one of the url errors is null", urlErrorsInfo);
                    assertNotNull("code is null", urlErrorsInfo.getCode());
                    assertNotNull("error count is null", urlErrorsInfo.getErrorCount());
                    assertNotNull("error severity is null", urlErrorsInfo.getErrorSeverity());
                }
            }
        }
    }

    @Test
    public void testTicHistory() throws Exception {
        for (Host host : hosts) {
            Verification verification = getOrPutVerification(host);
            if (verification.getVerificationState() == VerificationState.VERIFIED) {
                History ticHistory = host.tic();
                assertNotNull("tcy history is null", ticHistory);
                assertEquals("wrong history type", HistoryType.TIC,
                        ticHistory.getHistoryType());
                // TODO: check the most recent added host (I assume history will be null)
            }
        }
    }

    @Test
    @Ignore
    public void testIncomingLinksHistory() throws Exception {
        for (Host host : hosts) {
            Verification verification = getOrPutVerification(host);
            if (verification.getVerificationState() == VerificationState.VERIFIED) {
                History incomingLinksHistory;
                try {
                    incomingLinksHistory = host.incomingLinksHistory();
                } catch (ForbiddenException e) {
                    continue;
                }
                assertNotNull("incoming links history is null", incomingLinksHistory);
                assertEquals("wrong history type", HistoryType.INCOMING_LINKS_COUNT,
                        incomingLinksHistory.getHistoryType());
                // TODO: check the most recent added host (I assume history will be null)
            }
        }
    }

    @Test
    @Ignore
    public void testCrawledURLsHistory() throws Exception {
        for (Host host : hosts) {
            Verification verification = getOrPutVerification(host);
            if (verification.getVerificationState() == VerificationState.VERIFIED) {
                History crawledURLsHistory = host.crawledURLsHistory();
                assertNotNull("incoming links history is null", crawledURLsHistory);
                assertEquals("wrong history type", HistoryType.CRAWLED_URLS_COUNT,
                        crawledURLsHistory.getHistoryType());
                // TODO: check the most recent added host (I assume history will be null)
            }
        }
    }

    @Test
    @Ignore
    public void testIndexedURLsHistory() throws Exception {
        for (Host host : hosts) {
            Verification verification = getOrPutVerification(host);
            if (verification.getVerificationState() == VerificationState.VERIFIED) {
                History indexedURLsHistory = host.indexedURLsHistory();
                assertNotNull("incoming links history is null", indexedURLsHistory);
                assertEquals("wrong history type", HistoryType.INDEXED_URLS_COUNT,
                        indexedURLsHistory.getHistoryType());
                // TODO: check the most recent added host (I assume history will be null)
            }
        }
    }

    @Test
    @Ignore
    public void testExcludedURLsHistory() throws Exception {
        for (Host host : hosts) {
            Verification verification = getOrPutVerification(host);
            if (verification.getVerificationState() == VerificationState.VERIFIED) {
                History excludedURLsHistory = host.excludedURLsHistory();
                assertNotNull("incoming links history is null", excludedURLsHistory);
                assertEquals("wrong history type", HistoryType.EXCLUDED_URLS_COUNT,
                        excludedURLsHistory.getHistoryType());
                // TODO: check the most recent added host (I assume history will be null)
            }
        }
    }

    @Test
    public void testOriginalTexts() throws Exception {
        for (Host host : hosts) {
            Verification verification = getOrPutVerification(host);
            if (verification.getVerificationState() == VerificationState.VERIFIED) {
                OriginalTexts originalTexts = host.originalTexts();
                assertNotNull("original texts was not fetched", originalTexts);
                assertNotNull("total texts is null", originalTexts.getTotal());
                assertNotNull("can add is null", originalTexts.getCanAdd());
            }
        }
    }
}
