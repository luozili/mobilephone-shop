package net.hycollege.controll;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.get.GetIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import net.hycollege.message.bean.ESProduct;
import net.hycollege.message.bean.LayUITableMessage;
import net.hycollege.message.bean.Message;
import net.hycollege.message.bean.ProductMessage;
import net.hycollege.message.utils.CheckObjects;
import net.hycollege.message.utils.StringUtil;
import net.hycollege.mybatis.domain.Product;
import net.hycollege.services.ProductService;
import net.hycollege.util.FastDFSClient;

@RestController
public class ProductControll {
	@Autowired
	private ProductService productService;
	@Autowired
	private FastDFSClient fastDFSClient;
	@PostMapping("/full/upload")
	public void singleFileUpload(@RequestParam("file") MultipartFile file, Product product, HttpServletRequest request,
			ESProduct esProduct, String pdesc, HttpServletResponse response) throws IOException {
		Message message = upload2FastDFS(file);
		if (message.getStatus() == Message.fail)
			return;
		String[] data = message.getData();
		if (CheckObjects.checkLeng(data))
			return;
		String picture = data[0];
		product.setPicture(picture);
		productService.addProduct(product, pdesc, esProduct);
		response.sendRedirect("/admim-index.html");
	}

	@PostMapping("/single/upload")
	public Message singleFileUpload(@RequestParam("file") MultipartFile file) {
		return upload2FastDFS(file);
	}

	@GetMapping("/get/products")
	public LayUITableMessage getProducts(int currentPage, int pageSize) {

		return productService.getProducts(currentPage, pageSize);
	}
	private Message upload2FastDFS(MultipartFile file) {
		try {
			return new Message(fastDFSClient.uploadFile(file));
		} catch (IOException e) {
			e.printStackTrace();
			return new Message(Message.fail);
		}
	}
	private Message upload(MultipartFile file, String uri, HttpServletRequest request) {
		String requestPath = request.getRequestURL().toString().replaceAll(request.getServletPath(), "") + "/";
		if (file.isEmpty()) {
			return new Message(Message.fail);
		}
		try {
			byte[] bytes = file.getBytes();

			String uuid = UUID.randomUUID().toString();
			String originalFilename = file.getOriginalFilename();
			uri = StringUtil.contactString(uri, uuid, originalFilename);
			Path path = Paths.get(uri);
			Files.write(path, bytes);
			String port = request.getServerPort() + "";
			return new Message(StringUtil.contactString(requestPath.replace(port, "10000"), uri));
		} catch (Exception e) {
			e.printStackTrace();
			return new Message(Message.fail);
		}
	}

	@GetMapping("/get/product")
	public ProductMessage selectOneProduct(String productID) {
		return productService.selectOneProduct(productID);
	}

	@PostMapping("/product/delete")
	public Message deleteOneProduct(String productID) {
		productService.deleteOneProduct(productID);
		return new Message(Message.ok);
	}

	@PostMapping("/product/update")
	public void updateOneProduct(@RequestParam("file") MultipartFile file, ProductMessage productMessage, HttpServletRequest request,
			ESProduct esProduct, HttpServletResponse response) throws IOException {
		if (file != null) {
			Message message = upload(file, "upload/", request);
			if (message.getStatus() == Message.fail)
				return;
			String[] data = message.getData();
			if (CheckObjects.checkLeng(data))
				return;
			String picture = data[0];
			productMessage.getProduct().setPicture(picture);
		}

		productService.updateOneProduct(productMessage, esProduct);
		response.sendRedirect("/admim-index.html");
	}

	@PostMapping("/admin/logout")
	public void logout(HttpServletRequest request) {
		request.getSession().removeAttribute("login");
	}

	@PostMapping("/product/update/status")
	public void updateOneProductStatus(String pid, String status) {
		productService.updateOneProductStatus(pid, status);
	}
}
