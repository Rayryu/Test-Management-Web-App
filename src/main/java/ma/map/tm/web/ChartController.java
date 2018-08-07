package ma.map.tm.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ma.map.tm.dao.CasTestRepository;
import ma.map.tm.entities.Scenario;
import ma.map.tm.services.CasTestService;
import ma.map.tm.utils.Consts;

@Controller
public class ChartController {
	
	@Autowired
	private CasTestService casTestService;
	@Autowired
	private CasTestRepository casTestRepo;
 
    @RequestMapping(value = "/chart", method=RequestMethod.GET)
    public String chart(Model model) {
    	Map<Object,Object> map = null;
    	List<List<Map<Object,Object>>> list = new ArrayList<List<Map<Object,Object>>>();
    	List<Map<Object,Object>> dataPoints1 = new ArrayList<Map<Object,Object>>();
    	
		map = new HashMap<Object,Object>(); map.put("label", "18 yrs and Under"); map.put("y", 7);dataPoints1.add(map);
		map = new HashMap<Object,Object>(); map.put("label", "19 to 32 yrs"); map.put("y", 51);dataPoints1.add(map);
		map = new HashMap<Object,Object>(); map.put("label", "32 to 45 yrs"); map.put("y", 12);dataPoints1.add(map);
		map = new HashMap<Object,Object>(); map.put("label", "45 to 60 yrs"); map.put("y", 17);dataPoints1.add(map);
		map = new HashMap<Object,Object>(); map.put("label", "60 yrs and over"); map.put("y", 13);dataPoints1.add(map);
		
		list.add(dataPoints1);
         
    	List<List<Map<Object, Object>>> canvasjsDataList = list;
    	model.addAttribute("dataPointsList", canvasjsDataList);
    	
		return "chart";
    }
    
    @RequestMapping(value = "/chart2", method=RequestMethod.GET)
    public String chart2(Model model) {
    	Scenario scenarioCourant = new Scenario();
    	scenarioCourant.setId(1L);
    	
    	Hashtable<String, Integer> scenarioCourantStats = casTestService.getStatistiquesScenario(scenarioCourant); 
    	
    	model.addAttribute("Reussis", scenarioCourantStats.get(Consts.RÉUSSI));
    	model.addAttribute("Bloques", scenarioCourantStats.get(Consts.BLOQUÉ));
    	System.out.println("Echoues"+ scenarioCourantStats.get(Consts.ECHOUÉ));
    	model.addAttribute("Echoues", scenarioCourantStats.get(Consts.ECHOUÉ));
    	
		return "chart2";
    }
     
}