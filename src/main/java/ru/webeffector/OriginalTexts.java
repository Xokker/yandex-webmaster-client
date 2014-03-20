package ru.webeffector;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of all original texts from the site that were added to
 * Yandex.Webmaster.
 *
 * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/host-original-texts.xml
 * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/host-original-texts.xml
 *
 * @author Ernest Sadykov
 * @since 20.03.2014
 */
@XmlRootElement(name = "original-texts")
@XmlAccessorType(XmlAccessType.FIELD)
public class OriginalTexts {

    @XmlAttribute
    private Integer total;

    @XmlAttribute(name = "can-add")
    private Boolean canAdd;

    @XmlElement(name = "original-text")
    private List<OriginalText> originalTexts;

    OriginalTexts() {
        originalTexts = new ArrayList<>();
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Boolean getCanAdd() {
        return canAdd;
    }

    public void setCanAdd(Boolean canAdd) {
        this.canAdd = canAdd;
    }

    public List<OriginalText> getTexts() {
        return originalTexts;
    }

    public void setTexts(List<OriginalText> originalTexts) {
        this.originalTexts = originalTexts;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("total", total)
                .append("canAdd", canAdd)
                .append("originalTexts", originalTexts)
                .toString();
    }
}
