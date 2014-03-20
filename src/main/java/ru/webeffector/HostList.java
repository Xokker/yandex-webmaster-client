package ru.webeffector;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ernest Sadykov
 * @since 12.03.2014
 */
@XmlRootElement(name = "hostlist")
class HostList {

    @XmlElement(name = "host")
    List<Host> hosts;

    HostList() {
        hosts = new ArrayList<>();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("hosts", hosts)
                .toString();
    }
}
