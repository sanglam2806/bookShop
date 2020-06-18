package controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dao.BookDAO;
import dao.CatDAO;
import dao.UserDAO;
import defines.Define;
import entites.Book;

@Controller
@RequestMapping(value = "admincp/book")
public class AdminBookController {
	@Autowired
	private Define define;
	@Autowired
	private CatDAO catDAO;
	@Autowired
	private BookDAO bookDAO;
	@Autowired	
	private UserDAO userDAO;
	@ModelAttribute
	public void addCommonObject(ModelMap modelMap) {
		modelMap.addAttribute("define", define);
		modelMap.addAttribute("book", "active");
	}

	@RequestMapping(value = "")
	public String index(ModelMap modelMap, @RequestParam(value = "page", defaultValue = "1") int page,Principal principal,HttpSession session) {
		session.setAttribute("objUser", userDAO.getItem(principal.getName()));
		int sumItems = bookDAO.countItems();
		int itemInPage = 5;
		int sumPage = (int) Math.ceil((float) sumItems / itemInPage);
		int offset = (page - 1) * itemInPage;
		modelMap.addAttribute("listCat", bookDAO.getItems(offset, itemInPage));
		modelMap.addAttribute("sumPage", sumPage);
		modelMap.addAttribute("listc", catDAO.getItems());
		modelMap.addAttribute("currentPage", page);
		return "admin.book.index";
	}

	@RequestMapping(value = "add")
	public String add(ModelMap modelMap) {
		modelMap.addAttribute("listCAT", catDAO.getItems());
		return "admin.book.add";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(@Valid @ModelAttribute("objItem") Book objItem, BindingResult rs, ModelMap modelMap,
			HttpServletRequest request, @RequestParam("hinhAnh") CommonsMultipartFile cmf, RedirectAttributes ra) {
		if (rs.hasErrors()) {
			modelMap.addAttribute("listCAT", catDAO.getItems());
			return "admin.book.add";
		}
		List<Book> listb = bookDAO.getNews();
		for (Book objb : listb) {
			if (objb.getName().equals(objItem.getName())) {
				ra.addFlashAttribute("msg", "Tên sách đã có");
				return "redirect:/admincp/book/add";
			}
		}
		String fileName = cmf.getOriginalFilename();
		if (!"".equals(fileName)) {
			String dirPath = request.getServletContext().getRealPath("") + File.separator + "files";
			System.out.println(dirPath);
			File dirFile = new File(dirPath);
			if (!dirFile.exists()) {
				dirFile.mkdir();
			}
			String fileNamenew = FilenameUtils.getBaseName(fileName) + "-" + System.nanoTime() + "."
					+ FilenameUtils.getExtension(fileName);
			System.out.println(fileNamenew);
			String pathFile = dirPath + File.separator + fileNamenew;
			File file = new File(pathFile);
			try {
				cmf.transferTo(file);
			} catch (IOException e) {
				e.printStackTrace();
			}

			objItem.setPicture(fileNamenew);
		} else {
			objItem.setPicture("");
		}

		if (bookDAO.addItem(objItem) > 0) {
			ra.addFlashAttribute("msg", "Thêm thành công");
		} else
			ra.addFlashAttribute("msg", "Thất bại");
		return "redirect:/admincp/book";
	}

	@RequestMapping(value = "del/{id}")
	public String del(RedirectAttributes ra, @PathVariable("id") int id, HttpServletRequest request) {
		File file = new File(
				request.getServletContext().getRealPath("files") + File.separator + bookDAO.getItemID(id).getPicture());
		System.out.println(bookDAO.getItemID(id).getPicture());
		if (file.exists()) {
			if (file.delete())
				System.out.println("Xóa thành công");
			else
				System.out.println("Xóa thất bại");
		}
		if (bookDAO.delItem(id) > 0) {
			ra.addFlashAttribute("msg", "xóa thành công");
		}
		return "redirect:/admincp/book";
	}

	@RequestMapping(value = "edit/{id}")
	public String edit(@PathVariable("id") int id, ModelMap modelMap) {
		modelMap.addAttribute("objBook", bookDAO.getItemID(id));
		modelMap.addAttribute("listCAT", catDAO.getItems());
		return "admin.book.edit";
	}

	@RequestMapping(value = "edit/{id}", method = RequestMethod.POST)
	public String edit(@PathVariable("id") int id, ModelMap modelMap, HttpServletRequest request,
			@ModelAttribute("objb") Book objb, @RequestParam("hinhAnh") CommonsMultipartFile cmf,
			RedirectAttributes ra) {
		objb.setId_book(id);
		String fileName = cmf.getOriginalFilename();
		String dirPath = request.getServletContext().getRealPath("") + File.separator + "files";
		if (!"".equals(fileName)) {
			File file = new File(dirPath + File.separator + bookDAO.getItemID(id).getPicture());
			if (file.exists()) {
				if (file.delete())
					System.out.println("Xóa thành công");
				else
					System.out.println("Xóa thất bại");
			}
			objb.setPicture("");
		} else {
			objb.setPicture(bookDAO.getItemID(id).getPicture());
		}
		if (!"".equals(fileName)) {
			File dirFile = new File(dirPath);
			if (!dirFile.exists()) {
				dirFile.mkdir();
			}
			String fileNamenew = FilenameUtils.getBaseName(fileName) + "-" + System.nanoTime() + "."
					+ FilenameUtils.getExtension(fileName);
			String pathFile = dirPath + File.separator + fileNamenew;
			File file = new File(pathFile);
			try {
				cmf.transferTo(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(fileNamenew);
			objb.setPicture(fileNamenew);
		}
		if ("".equals(objb.getCost())) {
			objb.setCost(bookDAO.getItemID(id).getCost());
		}
		if ("".equals(objb.getQuantity())) {
			objb.setQuantity(bookDAO.getItemID(id).getQuantity());
		}
		if (bookDAO.editItem(objb) > 0) {
			ra.addFlashAttribute("msg", "Sửa thành công");
		}
		return "redirect:/admincp/book";
	}
	@RequestMapping(value = "search",method = RequestMethod.POST)
	public String search(@RequestParam("searchName") String name,@RequestParam("searchDM") String id,@RequestParam("searchcost") String cost,ModelMap modelMap) {
		System.out.println("aaa"+name+id+cost);
		int idcat= 0;
		int costsearch= 0;
		try{
			 idcat= Integer.parseInt(id);
			 costsearch= Integer.parseInt(cost);
		}catch (NumberFormatException e){
		}
		if(idcat==0 && costsearch==0){
			modelMap.addAttribute("listSearch", bookDAO.getSearch(name));
		}else if(idcat!=0 && costsearch==0){
			modelMap.addAttribute("listSearch", bookDAO.getSearchID(name, idcat));
		}else if(idcat==0 && costsearch!=0){
			modelMap.addAttribute("listSearch", bookDAO.getSearch(name, costsearch));
		}else{
			modelMap.addAttribute("listSearch", bookDAO.getSearch(name, costsearch, idcat));
		}
		modelMap.addAttribute("listc", catDAO.getItems());
		return "admin.book.search";
	}
}
