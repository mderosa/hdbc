package com.googlecode.hdbc.view.policy;

import java.util.Map;

public interface ICustomOutputPolicy {
	
	Map<String, Object> customOutput(Map<String, Object> model); 

}
