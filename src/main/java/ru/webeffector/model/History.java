package ru.webeffector.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joda.time.LocalDate;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author Ernest Sadykov
 * @since 11.03.2014
 */
@XmlRootElement(name = "history")
public class History {

    private static class HistoryEntry {
        @XmlAttribute(name = "date")
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate date;

        @XmlValue
        private Integer value;
    }

    @XmlAttribute(name = "type")
    private HistoryType historyType;

    private SortedMap<LocalDate, Integer> history;

    public History() {
        history = new TreeMap<>();
    }

    @XmlElement(name = "value")
    private void setHistoryList(List<HistoryEntry> historyEntryList) {
        for (HistoryEntry historyEntry : historyEntryList) {
            history.put(historyEntry.date, historyEntry.value);
        }
    }

    /**
     * Merges current history with another history object. Mutates this
     * history.
     *
     * @param anotherHistory history object to merge with, nullable
     * @return this history object
     */
    public History mergeWith(final History anotherHistory) {
        if (anotherHistory != null && anotherHistory.getHistory() != null) {
            history.putAll(anotherHistory.getHistory());
        }
        return this;
    }

    private List<HistoryEntry> getHistoryList() {
        return null;
    }

    public SortedMap<LocalDate, Integer> getHistory() {
        return history;
    }

    public void setHistory(SortedMap<LocalDate, Integer> history) {
        this.history = history;
    }

    public HistoryType getHistoryType() {
        return historyType;
    }

    public void setHistoryType(HistoryType historyType) {
        this.historyType = historyType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("historyType", historyType)
                .append("history", history)
                .toString();
    }
}

class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    @Override
    public LocalDate unmarshal(String v) throws Exception {
        return new LocalDate(v);
    }

    @Override
    public String marshal(LocalDate v) throws Exception {
        return v.toString();
    }
}
