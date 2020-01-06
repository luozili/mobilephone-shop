package net.hycollege.portal.controll;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import net.hycollege.message.bean.Message;
import net.hycollege.message.bean.elasticsearch.ESMssage;
import net.hycollege.message.bean.layui.LayUITableMessage;
import net.hycollege.message.bean.product.ProductMessage;
import net.hycollege.message.utils.WebUtil;
import net.hycollege.portal.filter.LoginInterceptor;
import net.hycollege.portal.util.CookieUtils;
import net.hycollege.protal.services.ProductService;

@RestController
public class ProductControll {
	@Autowired
	private ProductService productService;
	

	@GetMapping("/product/search")
	public ESMssage searchProduct(String productName, int pageIndex, int size) {
		ESMssage searchProduct = productService.searchProduct(productName, pageIndex, size);
		return searchProduct;
	}

	@GetMapping("/product/select")
	public ProductMessage selectProduct(String pid) {
		ProductMessage selectProduct = productService.selectProduct(pid);
		return selectProduct;
	}

	@PostMapping("/product/car/add")
	public Message addProduct2UserCar(HttpServletRequest request, String pid, int pnumber) {
		String token = CookieUtils.getToken(request);
		if(token == null)
			return new Message(Message.fail);
		if (pnumber == 0)
			pnumber++;
		return productService.addProduct2UserCar(pid, token, pnumber);
	}

	@PostMapping("/product/car/delete")
	public Message deleteProduct2UserCar(String cid) {
		return productService.deleteProduct2UserCar(cid);
	}
	@PostMapping("/product/car/delete/pid")
	public Message deleteProduct2UserCarOnPid(String pid, HttpServletRequest request) {
		String token = CookieUtils.getToken(request);
		if(token == null)
			return new Message(Message.fail);
		return productService.deleteProduct2UserCar(token, pid);
	}

	@PostMapping("/product/car/update/pnumber")
	public Message updateProduct2UserCarPnumber(String cid, int pnumber) {
		return productService.updateProduct2UserCarPnumber(cid, pnumber);
	}

	@GetMapping("/product/car/select")
	public LayUITableMessage selectProduct2UserCar(HttpServletRequest request) {
		String token = CookieUtils.getToken(request);
		return productService.selectProduct2UserCar(token);
	}
	@GetMapping("/product/car/exist")
	public Message selectProduct2UserCarExist(HttpServletRequest request, String pid) {
		String token = CookieUtils.getToken(request);
		System.out.println(token);
		if (token == null) {
			return new Message(Message.fail);
		}
		return productService.selectProduct2UserCarExist(pid, token);
	}
	
}
