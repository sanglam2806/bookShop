package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import defines.Define;

@Controller
@RequestMapping("auth")
public class AuthController {
	@Autowired
	private Define define;

	@ModelAttribute
	public void addCommonObject(ModelMap modelMap) {
		modelMap.addAttribute("define", define);
	}

	@RequestMapping(value = "login")
	public String login(HttpSession session, @RequestParam(value = "error", defaultValue = "1") String error,
			RedirectAttributes ra) {
		System.out.println(error);
		if("loginErr".equals(error)){
			ra.addFlashAttribute("msg", "Tên đăng nhập hoặc mật khẩu không đúng");
			return "redirect:/auth/login";
		}
		if (session.getAttribute("objUser") != null) {
			return "redirect:/admincp/cat";
		}
		return "auth.login";
	}

	@RequestMapping("403")
	public String error403() {
		return "auth.403";
	}
}
