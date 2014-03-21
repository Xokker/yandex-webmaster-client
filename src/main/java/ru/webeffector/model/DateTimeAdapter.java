package ru.webeffector.model;

import org.joda.time.DateTime;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * That adapter is used by JAXB for mapping string <-> JodaTime's DateTime.
 *
 * @author Ernest Sadykov
 * @since 12.03.2014
 */
public class DateTimeAdapter extends XmlAdapter<String, DateTime> {
    private DateTimeAdapter() {

    }

    @Override
    public DateTime unmarshal(String v) throws Exception {
        return new DateTime(v);
    }

    @Override
    public String marshal(DateTime v) throws Exception {
        return v.toString();
    }
}
