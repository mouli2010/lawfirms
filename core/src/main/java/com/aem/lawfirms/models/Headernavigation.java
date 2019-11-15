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

import com.aem.lawfirms.bean.Headernavigationbean;


@Model(adaptables=Resource.class)
public class Headernavigation {
	@Inject
	@Optional
	private String itemtext ;
	public String getItemtext() {
		return itemtext;
	}


	@Inject
	@Optional
	private  Resource navItems;
	private List<Headernavigationbean> navigationItems;
	
public List<Headernavigationbean> getNavigationItems() {
		return navigationItems;
	}
@PostConstruct
private void init() {
	if(null!= navItems && navItems.hasChildren() )
	{
		navigationItems =new ArrayList<>();
		Iterator<Resource>listChildren=navItems.listChildren();
		while(listChildren.hasNext())
		{
			Headernavigationbean bean=new Headernavigationbean();
			Resource resource=listChildren.next();
			ValueMap valueMap= resource.getValueMap();
		bean.setTitle(valueMap.get("linkTitle", String.class));
		bean.setLink(valueMap.get("linkURL", String.class));
		navigationItems.add(bean);
		
		}
	}
	
}
}