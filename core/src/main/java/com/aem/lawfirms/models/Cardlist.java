package com.aem.lawfirms.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import com.aem.lawfirms.bean.Cardlistbean;



@Model(adaptables=Resource.class)
public class Cardlist {
	@Inject
	@Optional
	private  Resource cardlist;
	@Inject
	@Optional
	public String headtext;
	public String getHeadtext() {
		return headtext;
	}

	private List<Cardlistbean>cardlistItems= new ArrayList<Cardlistbean>();
	public boolean children;
	public List<Cardlistbean> getCardlistItems() {
		return cardlistItems;
	}

	@PostConstruct
	public void init() {
		
		if(cardlist!=null&& cardlist.hasChildren()) {
			Iterator<Resource> listChildren = cardlist.listChildren();
		while(listChildren.hasNext()) {
			Cardlistbean bean= new Cardlistbean();
			Resource resource = listChildren.next();
			ValueMap valueMap = resource.getValueMap();
			bean.setTextarea(valueMap.get("textarea", String.class));
			bean.setText(valueMap.get("boldtext",String.class));
			bean.setImage(valueMap.get("image", String.class));
			bean.setLinkurl(valueMap.get("linkURL",String.class));
			bean.setLinktext(valueMap.get("linktext",String.class));
			cardlistItems.add(bean);
		}
		}
		
	}
}
