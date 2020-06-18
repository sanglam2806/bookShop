package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

import dao.BillDAO;
import dao.BookDAO;
import dao.CatDAO;
import dao.CustomerDAO;
import dao.DetailDAO;
import dao.UserDAO;
import defines.Define;
import entites.Book;
import entites.Customer;
import entites.User;

@Controller
public class PublicController {

	@Autowired
	private Define define;
	@Autowired
	private BookDAO bookDAO;
	@Autowired
	private CatDAO catDAO;
	@Autowired 
	private UserDAO userDAO;
	@Autowired
	private BillDAO billDAO;
	@Autowired
	private DetailDAO detailDAO;
	@Autowired
	private CustomerDAO customerDAO;
	@ModelAttribute
	public void addCommonObject(ModelMap modelMap) {
		modelMap.addAttribute("listCat", catDAO.getItems());
		modelMap.addAttribute("define", define);
	}

	@RequestMapping(value = "")
	public String index(ModelMap modelMap,Principal principal,HttpSession session) {
		modelMap.addAttribute("index", "active");
		modelMap.addAttribute("listShow", bookDAO.getItemShow());
		modelMap.addAttribute("listSell", bookDAO.getItemSell());
		modelMap.addAttribute("type", "public");
		modelMap.addAttribute("public","active");
		return "public.public.index";
	}

	@RequestMapping(value = "buy")
	public String buy(ModelMap modelMap) {
		modelMap.addAttribute("buy", "active");
		return "public.public.buy";
	}
	
	@RequestMapping(value = "infor")
	public String infor(Principal principal,HttpSession session) {
		session.setAttribute("objUser", userDAO.getItem(principal.getName()));
		return "public.public.infor";
	}
	
	@RequestMapping(value = "detailbill/{id}")
	public String detailbill(@PathVariable("id") int id,ModelMap modelMap) {
		modelMap.addAttribute("listDetail",detailDAO.getItems(id));
		return "public.public.detailbill";
	}
	
	@RequestMapping(value = "bill")
	public String bill(Principal principal,HttpSession session,ModelMap modelMap) {
		modelMap.addAttribute("bill", "active");
		session.setAttribute("objUser", userDAO.getItem(principal.getName()));
		modelMap.addAttribute("listBill", billDAO.getItem( userDAO.getItem(principal.getName()).getId() ));
		return "public.public.bill";
	}
	
	@RequestMapping(value = "infor", method = RequestMethod.POST)
	public String infor(@Valid @ModelAttribute("objItem") Customer objItem, BindingResult rs, ModelMap modelMap,HttpSession session , RedirectAttributes ra) {
		if (rs.hasErrors()) {
			return "public.public.infor";
		}
		int tong=0;
		User userLogin=(User) session.getAttribute("objUser");
		if(session.getAttribute("tong") == null){
			tong=0;
		}else{
			try{
				tong = (int) session.getAttribute("tong");
			}catch (NumberFormatException e){}
		}
		int quantity[] = (int[]) session.getAttribute("idsach");
		List<Book> listBookcp = (List<Book>) session.getAttribute("listBook");
		if(quantity == null){
			ra.addFlashAttribute("msg", "Chưa chọn sách");
			return "public.public.infor";
		}
		// add bill
		if(billDAO.add(tong,userLogin.getId()) > 0){
			System.out.println("Thanh cong");
		}
		int hoaDon= billDAO.getItems().getId();
		for(Book item : listBookcp){
			// add detail bill
			int tien= item.getCost()* quantity[item.getId_book()];
			if(detailDAO.add(item.getId_book(),userLogin.getId(),quantity[item.getId_book()],tien,hoaDon) >0){
				System.out.println("seikou");
			}
			// up date list book in DataBase
			int buy = quantity[item.getId_book()];
			int quan= item.getQuantity()-quantity[item.getId_book()];
			if(bookDAO.buyItem(item.getId_book(),buy,quan) >0){
				System.out.println("Katta");
			}
		}
		objItem.setId_bill(hoaDon);
		objItem.setId_user(userLogin.getId());
		if(objItem.getNote()== null){
			objItem.setNote(" ");
		}
		//add customer information
		if(customerDAO.add(objItem)>0){
			System.out.println("Them khach thanh cong");
		}

		
		//sau khi mua hang reset toan bo session
		session.removeAttribute("idsach");
		session.removeAttribute("book");
		session.removeAttribute("tong");
		session.removeAttribute("listBook");
		
		/*session.setAttribute("idsach", null);
		session.setAttribute("book", null);
		session.setAttribute("tong", '0');
		session.setAttribute("listBook", null);*/
		return "redirect:/";
	}
	
	@RequestMapping(value = "cat/{id}")
	public String edit(@PathVariable("id") int id, ModelMap modelMap, 
			@RequestParam(value = "page", defaultValue = "1") int page) {
		
		int sumItems = bookDAO.countItems(id);
		int itemInPage = 8;
		int sumPage = (int) Math.ceil((float) sumItems / itemInPage);
		int offset = (page - 1) * itemInPage;
		// modelMap.addAttribute("listNews",newsDAO.getNews());
		
		modelMap.addAttribute("listBook",bookDAO.getItems(offset, itemInPage,id));
		modelMap.addAttribute("sumPage", sumPage);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("objcat", catDAO.getItems(id));
		//modelMap.addAttribute("listBook", bookDAO.getItemsIdCat(id));
		modelMap.addAttribute("idcat", id);
		return "public.public.cat";
	}

	@RequestMapping(value = "detail/{id}")
	public String detail(@PathVariable("id") int id, ModelMap modelMap) {
		modelMap.addAttribute("objbook", bookDAO.getItemID(id));
		modelMap.addAttribute("listSell", bookDAO.getItemSell());
		return "public.public.detail";
	}

	@RequestMapping(value = "addProduct/{id}", method = RequestMethod.POST)
	public String addProduct(@PathVariable("id") int id, ModelMap modelMap, HttpServletRequest request,
			HttpSession session, RedirectAttributes ra) {
		// mang quantity có trọng số là id sách đã chọn, có giá trị là số lượng đã chọn.
		int quantity[] = (int[]) session.getAttribute("idsach");
		if(quantity== null){
			quantity= new int [1000];
		}
		int book = 0;
		int tong =0;
		if(session.getAttribute("tong") == null){
			tong=0;
		}else{
			try{
				tong = (int) session.getAttribute("tong");
			}catch (NumberFormatException e){}
		}
		int check = 0;
		List<Book> listBookcp = (List<Book>) session.getAttribute("listBook");
		int soluong = 0;
		try {
			soluong = Integer.parseInt((String) request.getParameter("qty"));
		} catch (NumberFormatException e) {

		}
		if (listBookcp == null) {
			listBookcp = new ArrayList<>();
			listBookcp.add(bookDAO.getItemID(id));
		}
		for (Book objBook : listBookcp) {
			if (objBook.getId_book() == id)
				check = 1;
		}
		if (check == 0) {
			listBookcp.add(bookDAO.getItemID(id));
		}
		System.out.println(soluong);
		// id : sách đã chọn. Chọn là +
		quantity[id] += soluong;
		for (Book objBookccp : listBookcp) {
			tong += objBookccp.getCost() * quantity[objBookccp.getId_book()];
			book += quantity[objBookccp.getId_book()];
		}
		
		session.removeAttribute("idsach");
		session.removeAttribute("book");
		session.removeAttribute("tong");
		session.removeAttribute("listBook");
		
		session.setAttribute("idsach", quantity);
		session.setAttribute("book", book);
		session.setAttribute("tong", tong);
		session.setAttribute("listBook", listBookcp);
		modelMap.addAttribute("objbook", bookDAO.getItemID(id));
		ra.addFlashAttribute("msg", "Đã thêm thành công");
		return "public.public.detail";
	}
	
	@RequestMapping(value = "resetBuy")
	public void resetBuy(@RequestParam("idBook") int idBook, 
			HttpServletRequest request,HttpServletResponse respone, HttpSession session) throws IOException {
		PrintWriter out = respone.getWriter();
		//Book item= bookDAO.getItemID(idBook);
		int tong=0,book=0;
		System.out.println("id book:"+idBook);
		int soluong= Integer.parseInt(request.getParameter("soluong"));
		int idsach[] = (int[]) session.getAttribute("idsach");
		List<Book> listBook = (List<Book>) session.getAttribute("listBook");
		idsach[idBook]= soluong;
		System.out.println("So luong: "+soluong);
		String reset ="<tr>"+
							"<th style='font-size:16px;height:30px;background:#37CDE8;width: 330px'>Tên sách:</th>"+
							"<th style='font-size:16px;height:30px;background:#37CDE8;width: 90px'>Giá</th>"+
							"<th style='font-size:16px;height:30px;background:#37CDE8;width: 90px'>Số lượng</th>"+
							"<th style='font-size:16px;height:30px;background:#37CDE8;width: 130px'>"+
								"Thành tiền </th>"+
							"</tr>";
		for(Book item:listBook ){
			System.out.println("id book= "+item.getId_book()+" so luong: "+idsach[item.getId_book()]);
		 reset += "<tr> <td style='font-size:14px;height:30px'>"+item.getName()+"</td>"+
							"<td style='font-size:14px;height:30px'>"+item.getCost()+"</td>"+
							"<td style='font-size:14px;height:30px'>"+
								"<input style='height:15px;width:60px;font-size:12px;' type='number' name='qty' id='"+item.getId_book()+"' onclick='resetBuy("+item.getId_book()+")' value= '"+idsach[item.getId_book()]+"' class='input-text qty-input' min=0>"+
							"</td>"+
							"<td style='font-size:14px;height:30px'>"+item.getCost()* idsach[item.getId_book()]+"</td> </tr>";
		 		tong+=item.getCost() * idsach[item.getId_book()];
		 		book += idsach[item.getId_book()];
		}
		
		session.removeAttribute("idsach");
		session.removeAttribute("book");
		session.removeAttribute("tong");
		
		session.setAttribute("tong", tong);
		session.setAttribute("idsach", idsach);
		session.setAttribute("book", book);
		reset+="<tr>"+
					"<td style='font-size:15px;height:30px;font-weight:bold' colspan='3'>Tổng</td>"+
					"<td style='font-size:15px;height:30px;font-weight:bold'>"+tong+"</td>"+
				"</tr>";
		out.println(reset);
	}
	
	@RequestMapping(value = "setActive")
	public void setActive(@RequestParam("id") int id, HttpServletResponse respone, HttpSession session)
			throws IOException {
		PrintWriter out = respone.getWriter();
		int sum = 0, book = 0;
		int check = 0;
		
		int idsach[] = (int[]) session.getAttribute("idsach");
		// code tiếp
		if (idsach == null) {
			idsach = new int[1000];
		}
		idsach[id]++;
		Book obj = bookDAO.getItemID(id);
		List<Book> listBook = (List<Book>) session.getAttribute("listBook");
		if (listBook == null) {
			listBook = new ArrayList<>();
			listBook.add(obj);
		} else {
			for (Book objBook : listBook) {
				if (objBook.getId_book() == id)
					check = 1;
			}
			if (check == 0) {
				listBook.add(obj);
			}
		}

		for (Book objBook : listBook) {
			
			sum += objBook.getCost() * idsach[objBook.getId_book()];
			book += idsach[objBook.getId_book()];
		}
		System.out.println(sum);
		
		session.removeAttribute("idsach");
		session.removeAttribute("book");
		session.removeAttribute("tong");
		session.removeAttribute("listBook");
		
		session.setAttribute("idsach", idsach);
		session.setAttribute("tong", sum);
		session.setAttribute("book", book);
		session.setAttribute("listBook", listBook);
		String str = "<a href='' class='cart' ><img src='" + define.getUrlPublic()
				+ "/css/images/cart-icon.png' alt='' /></a>Shopping Cart (" + book + ") <a href='#' class='sum'>" + sum
				+ "</a>";
		out.println(str);
	}
}
