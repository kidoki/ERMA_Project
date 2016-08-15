package com.erma.main.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "body")
public class BodyVO {
	private ItemsVO items;

	public ItemsVO getItems() {
		return items;
	}

	public void setItems(ItemsVO items) {
		this.items = items;
	}
}
