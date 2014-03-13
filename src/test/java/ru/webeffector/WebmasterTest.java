package ru.webeffector;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * @author Ernest Sadykov
 * @since 11.03.2014
 */
public class WebmasterTest {
    private static final Logger logger = LoggerFactory.getLogger(WebmasterTest.class);
    private static List<Host> hosts;
    private static List<Verification> verifications = new ArrayList<>();

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
    public void testVerification() throws Exception {
        for (Host host : hosts) {
            Verification verification = host.verify();
            assertNotNull("Verification is null [" + host.getUrl() + "]",
                    verification);
            verifications.add(verification);
            switch (verification.getVerificationState()) {
                case VERIFIED:
                    assertNotNull("Cancellation possibility is null",
                            verification.getCancellationPossibility());
                    assertNotNull("Verification time is null",
                            verification.getDate());
                    assertNotNull("Verification type is null",
                            verification.getVerificationConfirmationType());
                    break;
                case NEVER_VERIFIED:
                case VERIFICATION_FAILED:
                    assertNotNull("uin is null", verification.getUin());
                    break;

            }
        }
        assert hosts.size() == verifications.size() : "Not all verifications fetched!";
    }

    @Test
    public void testHostStats() throws Exception {
        for (Host host : hosts) {
            HostStats hostStats = host.stats();
            assertNotNull("Name is null", hostStats.getName());
        }
    }

    @Test
    public void testIndexInfo() throws Exception {
        for (Host host : hosts) {
            IndexInfo indexInfo = host.indexed();
            if (indexInfo != null) {
                assertNotNull("indexCount is not initialized", indexInfo.getIndexCount());
                logger.trace("indexCount: {}", indexInfo.getIndexCount());
                assertNotNull("links is not fetched", indexInfo.getUrls());
            }
        }
    }
}
