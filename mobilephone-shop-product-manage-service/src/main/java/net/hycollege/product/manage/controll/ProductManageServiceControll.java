package net.hycollege.product.manage.controll;

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

import net.hycollege.message.bean.Message;
import net.hycollege.message.bean.elasticsearch.ESProduct;
import net.hycollege.message.bean.layui.LayUITableMessage;
import net.hycollege.message.bean.product.ProductMessage;
import net.hycollege.message.bean.product.ProductMessageAndESProduct;
import net.hycollege.message.utils.CheckObjects;
import net.hycollege.message.utils.StringUtil;
import net.hycollege.mybatis.domain.Product;
import net.hycollege.mybatis.domain.UserCar;
import net.hycollege.mybatis.domain.UserCarExample;
import net.hycollege.product.manage.services.ProductService;

@RestController
public class ProductManageServiceControll {
	@Autowired
	private ProductService productService;

	@PostMapping("/product/add")
	public Message singleFileUpload(@RequestBody ProductMessageAndESProduct productMessageAndESProduct) {
		ProductMessage productMessage = productMessageAndESProduct.getProductMessage();
		ESProduct esProduct = productMessageAndESProduct.getEsProduct();
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
	public Message updateOneProduct(@RequestBody ProductMessageAndESProduct productMessageAndESProduct)
			throws IOException {
		ProductMessage productMessage = productMessageAndESProduct.getProductMessage();
		ESProduct esProduct = productMessageAndESProduct.getEsProduct();
		productService.updateOneProduct(productMessage, esProduct);
		return new Message(Message.ok);
	}

	@PostMapping("/product/update/status")
	public Message updateOneProductStatus(String productID, String status) {
		productService.updateOneProductStatus(productID, status);
		return new Message(Message.ok);
	}

	@PostMapping("/product/car/add")
	public Message addProduct2UserCar(HttpServletRequest request, String pid, String uid, int pnumber) {
		return productService.addProduct2UserCar(pid, uid, pnumber);
	}

	@PostMapping("/product/car/delete")
	public Message deleteProduct2UserCar(String cid) {
		return productService.deleteProduct2UserCar(cid);
	}
	@PostMapping("/product/car/delete/pid")
	public Message deleteProduct2UserCarOnPid(String uid, String pid) {
		return productService.deleteProduct2UserCarOnPid(uid, pid);
	}
	@PostMapping("/product/car/update/pnumber")
	public Message updateProduct2UserCarPnumber(String cid, int pnumber) {
		return productService.updateProduct2UserCarPnumber(cid, pnumber);
	}

	@GetMapping("/product/car/select")
	public LayUITableMessage selectProduct2UserCar(String uid) {
		return productService.selectProduct2UserCar(uid);
	}

	@GetMapping("/product/car/exist")
	public Message selectProduct2UserCarExist(String pid, String uid) {
		return productService.selectProduct2UserCarExist(pid, uid);
	}

}
