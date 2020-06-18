package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
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
import dao.UserDAO;
import defines.Define;
import entites.User;
import utils.StringUtils;

@Controller
@RequestMapping("/admincp/user")
public class AdminUserController {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private RollUserDAO rollUserDAO;
	@Autowired
	private Define define;
	@Autowired
	private StringUtils stringUtils;
	@ModelAttribute
	public void addCommonObject(ModelMap modelMap){
		modelMap.addAttribute("define",define);
		modelMap.addAttribute("user","active");
	}
	@RequestMapping(value="")
	public String index(ModelMap modelMap, @RequestParam(value = "page", defaultValue = "1") int page){
		
		int sumItems = userDAO.countItems();
		int itemInPage = 5;
		int sumPage = (int) Math.ceil((float) sumItems / itemInPage);
		int offset = (page - 1) * itemInPage;
		modelMap.addAttribute("listUser",userDAO.getItems(offset, itemInPage));
		modelMap.addAttribute("sumPage", sumPage);
		modelMap.addAttribute("currentPage", page);
		
		//modelMap.addAttribute("listUser",userDAO.getItems());
		return"admin.user.index";
	}
	
	@RequestMapping(value="setActive")
	public void setActive(@RequestParam("aid") int aid, @RequestParam("aactive") int aactive, HttpServletResponse respone) throws IOException{
		PrintWriter out = respone.getWriter();
		if(aactive==0){
			aactive=1;
		}else{
			aactive=0;
		}
		String img="";
		if(aactive == 0){
			img = "minus-circle.gif";
		}else{
			img = "tick-circle.gif";
		}
		if(userDAO.setActive(aid, aactive)>0){
			System.out.println("Đổi trạng thái thành công");
		}else{
			System.out.println("Đổi trạng thái thất bại");
		}
		String kq= "<a href='javascript:void(0)' onclick='return setActive("+aid+","+aactive+")'><img src='"+define.getUrlAdmin()+"/img/"+img+"' alt='' />";
		out.println(kq);
	}
	
	
	@RequestMapping(value="add")
	public String add(ModelMap modelMap){
		modelMap.addAttribute("listRoll",rollUserDAO.getItems());
		return"admin.user.add";
	}
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String add(@Valid @ModelAttribute("objItem")User objItem,BindingResult rs,ModelMap modelMap,RedirectAttributes ra){
		if(rs.hasErrors()){
			modelMap.addAttribute("listRoll",rollUserDAO.getItems());
			return "admin.user.add";
		}
		objItem.setPassword(stringUtils.md5(objItem.getPassword()));
		List<User>listuser= userDAO.getItems();
		for(User obju: listuser){
			if(obju.getUsername().equals(objItem.getUsername())){
				ra.addFlashAttribute("msg", "Username đã tồn tại");
				return "redirect:/admincp/user/add";
			}
		}
		if(userDAO.addItem(objItem)>0){
			ra.addFlashAttribute("msg", "Thêm thành công");
		}else{
			ra.addFlashAttribute("msg", "Thêm thất bại");
		}
		return"redirect:/admincp/user";
	}
	@RequestMapping(value="edit/{id}")
	public String edit(@PathVariable("id") int id,ModelMap modelMap){
		modelMap.addAttribute("listRoll",rollUserDAO.getItems());
		modelMap.addAttribute("obju", userDAO.getItem(id));
		return"admin.user.edit";
	}
	@RequestMapping(value="edit/{id}",method=RequestMethod.POST)
	public String edit(@ModelAttribute("obju") User obju,@PathVariable("id") int id, RedirectAttributes ra){
		obju.setId(id);
		User item= (User) userDAO.getItem(id);
		if("".equals(obju.getFullname())) 
			obju.setFullname(item.getFullname());
		if("".equals(obju.getPassword()))
			obju.setPassword(item.getPassword());
		else obju.setPassword(stringUtils.md5(obju.getPassword()));
		if(userDAO.editItem(obju)>0){
			ra.addFlashAttribute("msg","Sửa thành công");
		}else ra.addFlashAttribute("msg", "Sửa thất bại");
		return "redirect:/admincp/user";
	}
	@RequestMapping(value="del/{id}")
	public String del(@PathVariable ("id") int id,RedirectAttributes ra){
		if("admin".equals(userDAO.getItem(id).getUsername())){
			ra.addFlashAttribute("msg", "Không xóa được Admin");
			return "redirect:/admincp/user";
		}
		if(userDAO.delItem(id)>0){
			ra.addFlashAttribute("msg", "Xóa thành công");
		}else ra.addFlashAttribute("msg", "xóa thất bại");
		return "redirect:/admincp/user";
	}
}
