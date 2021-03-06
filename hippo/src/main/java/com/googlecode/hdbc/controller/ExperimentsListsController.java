package com.googlecode.hdbc.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.googlecode.hdbc.dao.IExperimentsDao;
import com.googlecode.hdbc.model.record.ExperimentData;

@Controller
@RequestMapping("/experiments/lists/*")
public class ExperimentsListsController {
	private IExperimentsDao experimentsDao;
	
	public ExperimentsListsController(IExperimentsDao dao) {
		this.experimentsDao = dao;
	}

	@RequestMapping(value="active", method=RequestMethod.GET)
	public ModelAndView active() {
		List<ExperimentData> experiments = experimentsDao.findActiveExperiments();

		ModelAndView mv = new ModelAndView("experiments_lists_active");
		mv.addObject(ModelAttributes.COLLECTION, experiments);
		return mv;
	}
	
	@RequestMapping(value="search", method=RequestMethod.GET)
	public void search(Model model) {
		List<ExperimentData> experiments = experimentsDao.findActiveExperiments();
		model.addAttribute(ModelAttributes.COLLECTION, experiments);
	}
}
