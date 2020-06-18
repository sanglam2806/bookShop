package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dao.BillDAO;
import dao.DetailDAO;
import defines.Define;

@Controller
@RequestMapping(value = "admincp/bill")
public class AdminBillController {
	@Autowired
	private Define define;
	@Autowired
	private BillDAO billDAO;
	@Autowired
	private DetailDAO detailDAO;

	@ModelAttribute
	public void addCommonObject(ModelMap modelMap) {
		modelMap.addAttribute("define", define);
		modelMap.addAttribute("bill", "active");
	}

	@RequestMapping(value = "")
	public String index(ModelMap modelMap, @RequestParam(value = "page", defaultValue = "1") int page) {

		int sumItems = billDAO.countItems();
		int itemInPage = 5;
		int sumPage = (int) Math.ceil((float) sumItems / itemInPage);
		int offset = (page - 1) * itemInPage;
		modelMap.addAttribute("listCat", billDAO.getItems(offset, itemInPage));
		modelMap.addAttribute("sumPage", sumPage);
		modelMap.addAttribute("currentPage", page);
		return "admin.bill.index";
	}

	@RequestMapping(value = "del/{id}")
	public String del(@PathVariable("id") int id, RedirectAttributes ra) {
		if (billDAO.delItem(id) > 0) {
			if (detailDAO.delItem(id) > 0)
				ra.addFlashAttribute("msg", "Xóa thành công");
		} else {
			ra.addFlashAttribute("msg", "Xóa thất bại");
		}
		return "redirect:/admincp/bill";
	}
}
