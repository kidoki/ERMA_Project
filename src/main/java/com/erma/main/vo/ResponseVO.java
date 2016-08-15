package com.erma.main.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD) 
public class ResponseVO {
    private HeaderVO header;
    private BodyVO body;

	public void setBody(BodyVO body) {
		this.body = body;
	}

	public BodyVO getBody() {
		return body;
	}

	public HeaderVO getHeader() {
		return header;
	}

	public void setHeader(HeaderVO header) {
		this.header = header;
	}
}