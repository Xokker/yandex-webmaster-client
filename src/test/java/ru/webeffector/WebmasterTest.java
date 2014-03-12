package ru.webeffector;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.webeffector.host.*;

import static org.junit.Assert.*;

import java.util.List;

/**
 * @author Ernest Sadykov
 */
public class WebmasterTest {
    private static final Logger logger = LoggerFactory.getLogger(WebmasterTest.class);
    private static String accessToken;

    @BeforeClass
    public static void setUp() throws Exception {
        accessToken = System.getProperty("accessToken");
        assertNotNull(accessToken);
    }

    @Test
    public void testWebmaster() throws Exception {
        Webmaster webmaster = new Webmaster(accessToken, 100500);
        List<Host> hosts = webmaster.getHosts();

        for (Host host : hosts) {
            assertNotNull("Host's URL is null", host.getUrl());
            Verification verification = host.getVerification();
            assertNotNull("Verification is null", verification);
            assertNotNull("Verification status is null", verification.getVerificationStatus());
            if (verification.getVerificationStatus() == VerificationStatus.VERIFICATION_FAILED) {
                assertNotNull("Verification refusal details is null",
                        verification.getVerificationRefusalDetails());
            }
            Crawling crawling = host.getCrawling();
            assertNotNull("Crawling is null", crawling);
            assertNotNull("Crawling state is null", crawling.getCrawlingState());
            if (crawling.getCrawlingState() == CrawlingState.NOT_INDEXED) {
                assertNotNull("Crawling denial details", crawling.getCrawlingDenialDetails());
            }
            if (verification.getVerificationStatus() == VerificationStatus.VERIFIED) {
                assertTrue("Index count < 0", host.getIndexCount() >= 0);
                assertTrue("URL count < 0", host.getUrlCount() >= 0);
                assertTrue("TCY < 0", host.getTcy() >= 0);
            }
            assertNotNull("Last access date is null", host.getLastAccess());
        }

        logger.debug("Hosts: {}", hosts);
    }

}
