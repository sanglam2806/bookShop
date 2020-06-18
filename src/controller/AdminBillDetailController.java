package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.DetailDAO;
import defines.Define;

@Controller
@RequestMapping(value="admincp/details")
public class AdminBillDetailController {
	@Autowired
	private Define define;
	@Autowired
	private DetailDAO detailDAO;
	@ModelAttribute
	public void addCommonObject(ModelMap modelMap) {
		modelMap.addAttribute("define", define);
		modelMap.addAttribute("type","5");
	}
	@RequestMapping(value = "/{id_bill}")
	public String index(@PathVariable ("id_bill") int id_bill,ModelMap modelMap){
		modelMap.addAttribute("listDetail", detailDAO.getItems(id_bill));
		return"admin.details.index";
	}
}
