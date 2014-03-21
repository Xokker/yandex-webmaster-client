package ru.webeffector;

import org.joda.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * That adapter is used by JAXB for mapping string <-> JodaTime's LocalDate.
 *
 * @author Ernest Sadykov
 * @since 12.03.2014
 */
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
