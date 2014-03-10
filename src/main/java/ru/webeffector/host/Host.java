package ru.webeffector.host;

/**
 * @author Ernest Sadykov
 */
public class Host {
    private String name;
    private Stats stats;
    private Verification verification;

    public Verification getVerification() {
        if (verification == null) {
            throw new IllegalStateException(
                    "Verification info is not fetched. Use Webmaster.loadVerificationInfo");
        }
        return verification;
    }

    private void checkStatsPresence() {
        if (stats == null) {
            throw new IllegalStateException(
                    "Statistics is not fetched. User Webmaster.loadHostStats");
        }
    }

    public Stats getStats() {
        checkStatsPresence();
        return stats;
    }

    public int getUrlErrorsCount() {
        checkStatsPresence();
        return stats.getUrlErrorsCount();
    }

    public int getUrlCount() {
        checkStatsPresence();
        return stats.getUrlCount();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
