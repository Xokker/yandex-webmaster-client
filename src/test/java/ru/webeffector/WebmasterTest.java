package ru.webeffector;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
            assertNotNull("Host's URL is null", host.getUrl());
            Verification verification = host.getVerification();
            assertNotNull("Verification is null", verification);
            assertNotNull("Verification status is null", verification.getVerificationStatus());
            if (verification.getVerificationStatus() == VerificationStatus.VERIFICATION_FAILED) {
                assertNotNull("Verification refusal details is null",
                        verification.getVerificationRefusalDetails());
            }
            Crawling crawling = host.getCrawling();
            if (crawling != null) {
                assertNotNull("Crawling state is null", crawling.getCrawlingState());
                if (crawling.getCrawlingState() == CrawlingState.NOT_INDEXED) {
                    assertNotNull("Crawling denial details", crawling.getCrawlingDenialDetails());
                }
                if (crawling.getCrawlingState() == CrawlingState.INDEXED) {
                    assertTrue("Index count < 0", host.getIndexCount() >= 0);
                    assertTrue("URL count < 0", host.getUrlCount() >= 0);
                    assertTrue("TCY < 0", host.getTcy() >= 0);
                    assertNotNull("Last access date is null", host.getLastAccess());
                }
            }
        }
    }

    @Test
    public void testAdditionalInfo() throws Exception {
        for (Host host : hosts) {
            if (host.getVerification().getVerificationStatus() == VerificationStatus.VERIFIED) {
                assertNotNull("", host.getUrlErrors());
            }
        }
    }
}
