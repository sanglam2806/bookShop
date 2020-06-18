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

import dao.RollUserDAO;
import defines.Define;
import entites.RollUser;

@Controller
@RequestMapping(value="admincp/role")
public class AdminRoleController {
	@Autowired
	private Define define;
	@Autowired 
	private RollUserDAO role;
	@ModelAttribute
	public void addCommonObject(ModelMap modelMap) {
		modelMap.addAttribute("define", define);
		modelMap.addAttribute("role","active");
	}
	@RequestMapping(value = "")
	public String index(ModelMap modelMap, @RequestParam(value = "page", defaultValue = "1") int page) {
		
		int sumItems = role.countItems();
		int itemInPage = 5;
		int sumPage = (int) Math.ceil((float) sumItems / itemInPage);
		int offset = (page - 1) * itemInPage;
		modelMap.addAttribute("listRole",role.getItems(offset, itemInPage));
		modelMap.addAttribute("sumPage", sumPage);
		modelMap.addAttribute("currentPage", page);
		
		return "admin.role.index";
	}
	@RequestMapping(value = "add",method = RequestMethod.GET)
	public String add() {
		return "admin.role.add";
	}
	@RequestMapping( value = "add", method = RequestMethod.POST)
	public String add(@Valid @ModelAttribute("objItem") RollUser objItem,BindingResult rs, RedirectAttributes ra) {
		List<RollUser>listc= role.getItems();
		for(RollUser item :listc){
			if(objItem.getName().equals(item.getName())){
				ra.addFlashAttribute("msg", "Chức năng đã tồn tại");
				return "redirect:/admincp/role/add";
			}
		}
		if(rs.hasErrors()){
			return "admin.cat.add";
		}
		if (role.addItem(objItem) > 0) {
			ra.addFlashAttribute("msg", "Thêm thành công");
		} else {
			ra.addFlashAttribute("msg", "Thêm thất bại");
		}
		return "redirect:/admincp/role";
	}
	@RequestMapping(value = "edit/{id}")
	public String edit(@PathVariable("id") int id, ModelMap modelMap) {
		modelMap.addAttribute("objItem", role.getItems(id));
		return "admin.role.edit";
	}
	@RequestMapping(value = "edit/{id}", method = RequestMethod.POST)
	public String edit(@ModelAttribute("objcat") RollUser objcat, @PathVariable("id") int id, RedirectAttributes ra) {
		List<RollUser>listc= role.getItems();
		for(RollUser item :listc){
			if(objcat.getName().equals(item.getName())){
				ra.addFlashAttribute("msg", "Tên thưc mục đã tồn tại");
				return "redirect:/admincp/role/add";
			}
		}
		if("".equals(objcat.getName())){
			objcat.setName(role.getItems(id).getName());
		}
		if (role.editItem(id, objcat) > 0) {
			ra.addFlashAttribute("msg", "Sửa thành công");
		} else {
			ra.addFlashAttribute("msg", "Sửa thất bại");
		}
		return "redirect:/admincp/role";
	}
	@RequestMapping(value = "del/{id}")
	public String del(@PathVariable("id") int id, RedirectAttributes ra) {
		if (role.delItem(id) > 0) {
			ra.addFlashAttribute("msg", "Sửa thành công");
		} else {
			ra.addFlashAttribute("msg", "Sửa thất bại");
		}
		return "redirect:/admincp/role";
	}
}
