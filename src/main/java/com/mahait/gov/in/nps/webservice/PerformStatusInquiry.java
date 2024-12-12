
package com.mahait.gov.in.nps.webservice;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "performStatusInquiry", propOrder = {
    "arg0"
})
public class PerformStatusInquiry {
    protected List<byte[]> arg0;
    public List<byte[]> getArg0() {
        if (arg0 == null) {
            arg0 = new ArrayList<byte[]>();
        }
        return this.arg0;
    }

}
