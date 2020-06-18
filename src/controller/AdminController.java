package controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.UserDAO;
import defines.Define;

@Controller
@RequestMapping("admincp")
public class AdminController {
	@Autowired
	private Define define;
	@Autowired
	private UserDAO userDAO;
	@ModelAttribute
	public void addCommonObject(ModelMap modelMap){
		modelMap.addAttribute("define",define);
	}
	@RequestMapping(value="")
	public String index(Principal principal,HttpSession session){
		session.setAttribute("objUser", userDAO.getItem(principal.getName()));
		return "admin.admin.index";
	}
	@RequestMapping(value="add")
	public String add(){
		return "admin.admin.add";
	}
	@RequestMapping(value="list")
	public String list(){
		return "admin.admin.list";
	}
}
