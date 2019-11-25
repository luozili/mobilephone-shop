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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

@RestController
public class ProductManageServiceControll {
	@Autowired
	private ProductService productService;

	@PostMapping("/product/add")
	public Message singleFileUpload(@RequestBody ProductMessage productMessage, @RequestBody ESProduct esProduct) {
		productService.addProduct(productMessage, esProduct);
		return new Message(Message.ok);
	}


	@GetMapping("/product/gets")
	public LayUITableMessage getProducts(int currentPage, int pageSize) {
		List<Product> data = productService.selectProduct(currentPage, pageSize);
		LayUITableMessage message = new LayUITableMessage();
		long count = productService.selectAllProductsConunt();
		message.setData(data);
		message.setCount(count);
		return message;
	}


	@GetMapping("/product/get")
	public ProductMessage selectOneProduct(String productID) {
		return productService.selectOneProduct(productID);
	}

	@PostMapping("/product/delete")
	public Message deleteOneProduct(String productID) {
		productService.deleteOneProduct(productID);
		return new Message(Message.ok);
	}

	@PostMapping("/product/update")
	public Message updateOneProduct(ProductMessage productMessage,
			ESProduct esProduct) throws IOException {
		productService.updateOneProduct(productMessage, esProduct);
		return new Message(Message.ok);
	}

	@PostMapping("/product/update/status")
	public Message updateOneProductStatus(String productID, String status) {
		productService.updateOneProductStatus(productID, status);
		return new Message(Message.ok);
	}
}
