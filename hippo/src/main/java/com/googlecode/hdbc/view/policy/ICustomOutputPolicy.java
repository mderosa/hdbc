package com.googlecode.hdbc.view.policy;

import net.sf.json.JSONObject;
import org.springframework.ui.Model;

public interface ICustomOutputPolicy {
	
	JSONObject customOutput(Model model); 

}
