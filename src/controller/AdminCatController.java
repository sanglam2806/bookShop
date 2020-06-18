package controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dao.CatDAO;
import defines.Define;
import entites.Category;

@Controller
@RequestMapping(value="admincp/cat")
public class AdminCatController {
	@Autowired
	private CatDAO catDAO;
	@Autowired
	private Define define;
	@ModelAttribute
	public void addCommonObject(ModelMap modelMap) {
		modelMap.addAttribute("define", define);
		modelMap.addAttribute("cat","active");
	}
	@RequestMapping(value = "")
	public String index(ModelMap modelMap, @RequestParam(value = "page", defaultValue = "1") int page) {
		
		int sumItems = catDAO.countItems();
		int itemInPage = 5;
		int sumPage = (int) Math.ceil((float) sumItems / itemInPage);
		int offset = (page - 1) * itemInPage;
		// modelMap.addAttribute("listNews",newsDAO.getNews());
		modelMap.addAttribute("listCat",catDAO.getItems(offset, itemInPage));
		modelMap.addAttribute("sumPage", sumPage);
		modelMap.addAttribute("currentPage", page);
		
		//modelMap.addAttribute("listCat", catDAO.getItems());
		return "admin.cat.index";
	}

	@RequestMapping(value = "add",method = RequestMethod.GET)
	public String add() {
		return "admin.cat.add";
	}

	@RequestMapping( value = "add", method = RequestMethod.POST)
	public String add(@Valid @ModelAttribute("objItem") Category objItem,BindingResult rs, RedirectAttributes ra) {
		List<Category>listc= catDAO.getItems();
		for(Category item :listc){
			if(objItem.getName().equals(item.getName())){
				ra.addFlashAttribute("msg", "Tên thưc mục đã tồn tại");
				return "redirect:/admincp/cat/add";
			}
		}
		if(rs.hasErrors()){
			return "admin.cat.add";
		}
		if (catDAO.addItem(objItem) > 0) {
			ra.addFlashAttribute("msg", "Thêm thành công");
		} else {
			ra.addFlashAttribute("msg", "Thêm thất bại");
		}
		return "redirect:/admincp/cat";
	}

	@RequestMapping(value = "edit/{id}")
	public String edit(@PathVariable("id") int id, ModelMap modelMap) {
		modelMap.addAttribute("objc", catDAO.getItems(id));
		return "admin.cat.edit";
	}

	@RequestMapping(value = "edit/{id}", method = RequestMethod.POST)
	public String edit(@ModelAttribute("objcat") Category objcat, @PathVariable("id") int id, RedirectAttributes ra) {
		List<Category>listc= catDAO.getItems();
		for(Category item :listc){
			if(objcat.getName().equals(item.getName())){
				ra.addFlashAttribute("msg", "Tên thưc mục đã tồn tại");
				return "redirect:/admincp/cat/add";
			}
		}
		if("".equals(objcat.getName())){
			objcat.setName(catDAO.getItems(id).getName());
		}
		if (catDAO.editItem(id, objcat) > 0) {
			ra.addFlashAttribute("msg", "Sửa thành công");
		} else {
			ra.addFlashAttribute("msg", "Sửa thất bại");
		}
		return "redirect:/admincp/cat";
	}

	@RequestMapping(value = "del/{id}")
	public String del(@PathVariable("id") int id, RedirectAttributes ra) {
		if (catDAO.delItem(id) > 0) {
			ra.addFlashAttribute("msg", "Xóa thành công");
		} else {
			ra.addFlashAttribute("msg", "Xóa thất bại");
		}
		return "redirect:/admincp/cat";
	}
}
