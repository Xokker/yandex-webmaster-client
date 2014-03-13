package ru.webeffector;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * @author Ernest Sadykov
 * @since 11.03.2014
 */
public class WebmasterTest {
    private static final Logger logger = LoggerFactory.getLogger(WebmasterTest.class);
    private static List<Host> hosts;

    @BeforeClass
    public static void setUp() throws Exception {
        String accessToken = System.getProperty("accessToken");
        assertNotNull(accessToken);
        Webmaster webmaster = new Webmaster(accessToken);
        hosts = webmaster.getHosts();
    }

    @Test
    public void testBasicInfo() throws Exception {
        for (Host host : hosts) {
            assertNotNull("Url is null", host.getUrl());
            logger.trace("url: {}", host.getUrl());
        }
    }

    @Test
    public void testHostStats() throws Exception {
        for (Host host : hosts) {
            assertNotNull("Name is null", host.stats().getName());
        }
    }

    @Test
    public void testIndexInfo() throws Exception {
        for (Host host : hosts) {
            IndexInfo indexInfo = host.indexed();
            if (indexInfo != null) {
                assertNotNull("indexCount is not initialized", indexInfo.getIndexCount());
                logger.trace("indexCount: {}", indexInfo.getIndexCount());
//                assertNotNull("links is not fetched", indexInfo.getIndexedUrls());
            }
        }
    }
}
