package org.big.controller;

import java.io.File;
import java.io.IOException;

import org.big.dao.ProductDao;
import org.big.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class ProjectController {
	
	@Autowired
    private ProductDao productDao;

	@RequestMapping("/project/welcome.do")
	public String welcome() {
		return "/welcome";
	}
	
	@RequestMapping("/project/member/loginMember.do")
	public String login() {
		return "/member/loginMember";
	}
	
	@RequestMapping("/project/member/logoutMember.do")
	public String logoutMember() {
		return "/member/logoutMember";
	}
	
	@RequestMapping("/project/logout.do")
	public String logout() {
		return "/logout";
	}
	
	@RequestMapping("/project/member/resultMember.do")
	public String resultMember() {
		return "/member/resultMember";
	}
	
	@RequestMapping("/project/member/processAddMember.do")
	public String processAddMember() {
		return "/member/processAddMember";
	}
	
	@RequestMapping("/project/member/updateMember.do")
	public String updateMember() {
		return "/member/updateMember";
	}
	
	@RequestMapping("/project/member/processUpdateMember.do")
	public String processUpdateMember() {
		return "/member/processUpdateMember";
	}
	
	@RequestMapping("/project/member/deleteMember.do")
	public String deleteMember() {
		return "/member/deleteMember";
	}
	
	@RequestMapping("/project/menu.do")
	public String menu() {
		return "/menu";
	}
	
	@RequestMapping("/project/footer.do")
	public String footer() {
		return "/footer";
	}
	
	@RequestMapping("/project/member/processLoginMember.do")
	public String processLoginMember() {
		return "/member/processLoginMember";
	}
	
	@RequestMapping("/project/member/addMember.do")
	public String addMember() {
		return "/member/addMember";
	}
	
	@RequestMapping("/project/products.do")
	public String books() {
		return "/products";
	}
	
	@RequestMapping("/project/dbconn.do")
	public String dbconn() {
		return "/dbconn";
	}
	
	@RequestMapping("/project/addProduct.do")
	public String addBook() {
		return "/addProduct";
	}
	
	@RequestMapping("/project/processAddProduct.do")
	public String processAddBook(@RequestParam("productId") String productId, 
	                             @RequestParam("name") String name,
	                             @RequestParam("price") String price, 
	                             @RequestParam("releaseDate") String releaseDate,
	                             @RequestParam("description") String description, 
	                             @RequestParam("category") String category,
	                             @RequestParam("soldout") String soldout, 
	                             @RequestParam("productImage") MultipartFile file, // MultipartFile로 파일 처리
	                             HttpServletRequest request) throws IOException {

	      // 가격과 재고 값 처리
	       int p_price = price.isEmpty() ? 0 : Integer.valueOf(price);
	       long stock = soldout.isEmpty() ? 0 : Long.valueOf(soldout);

	       // 파일 저장 경로
	       String uploadDir = request.getServletContext().getRealPath("/images");
	       File dir = new File(uploadDir);
	       if (!dir.exists()) {
	           dir.mkdirs(); // 디렉토리가 없다면 생성
	       }

	       String filename = file.getOriginalFilename();
	       if (!filename.isEmpty()) {
	           try {
	               file.transferTo(new File(uploadDir, filename)); // 파일 저장
	           } catch (IOException e) {
	               e.printStackTrace(); // 예외 처리
	               throw new IOException("파일 저장 중 오류 발생", e);
	           }
	       }

	       // Book DTO 객체 생성
	       Product product = new Product();
	       product.setProductId(productId);
	       product.setName(name);
	       product.setPrice(p_price); // price를 설정
	       product.setReleaseDate(releaseDate);
	       product.setDescription(description);
	       product.setCategory(category);
	       product.setSoldout(stock); // stock을 설정
	       product.setFilename(filename); // 파일명 저장

	       // DB에 저장
	       productDao.insertProduct(product);

	       return "redirect:/project/products.do";
	   }

	
	@RequestMapping("/project/editProduct.do")
	public String editProduct() {
		return "/editProduct";
	}
	
	@RequestMapping("/project/updateProduct.do")
	public String updateProduct() {
		return "/updateProduct";
	}
	
	@PostMapping("/project/processUpdateProduct.do")
    public String processUpdateProduct(@RequestParam("productId") String productId, 
                                    @RequestParam("name") String name,
                                    @RequestParam("price") String price, 
                                    @RequestParam("releaseDate") String releaseDate,
                                    @RequestParam("description") String description, 
                                    @RequestParam("category") String category,
                                    @RequestParam("soldout") String soldout,
                                    @RequestParam(value = "productImage", required = false) MultipartFile file,
                                    HttpServletRequest request) throws IOException {

        //productDao가 null 인지 체크
        if (productDao == null) {
            throw new RuntimeException("ProductDao가 주입되지 않았습니다!");
        }

        //숫자 변환
        int p_price = price.isEmpty() ? 0 : Integer.parseInt(price);
        long stock = soldout.isEmpty() ? 0 : Long.parseLong(soldout);

        //기존 상품 정보 조회
        Product product = productDao.getProductById(productId);
        if (product == null) {
            return "redirect:/project/editProduct.do?edit=notfound";
        }

        //파일 저장 경로 확인
        String uploadDir = request.getServletContext().getRealPath("/images");
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            boolean created = dir.mkdirs();
            System.out.println("디렉토리 생성 여부: " + created);
        }

        //파일 업로드 확인
        String filename = file != null && !file.isEmpty() ? file.getOriginalFilename() : product.getFilename();
        if (file != null && !file.isEmpty()) {
            try {
                file.transferTo(new File(uploadDir, filename));
            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:/project/editProduct.do?edit=fileerror";
            }
        }

        //책 정보 업데이트
        product.setName(name);
        product.setPrice(p_price);
        product.setReleaseDate(releaseDate);
        product.setDescription(description);
        product.setCategory(category);
        product.setSoldout(stock);
        product.setFilename(filename);

        //DB 업데이트
        try {
        	productDao.updateProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/project/editProduct.do?edit=error";
        }

        return "redirect:/project/products.do";  
    }

	
	@RequestMapping("/project/exceptionNoProductId.do")
	public String exceptionNoProductId() {
		return "/exceptionNoProductId";
	}
	
	@RequestMapping("/project/deleteProduct.do")
	public String deleteProduct() {
		return "/deleteProduct";
	}
	
	@RequestMapping("/project/product.do")
	public String product() {
		return "/product";
	}
	
	@RequestMapping("/project/cart.do")
	public String cart() {
		return "/cart";
	}
	
	@RequestMapping("/project/addCart.do")
	public String addCart() {
		return "/addCart";
	}
	
	@RequestMapping("/project/deleteCart.do")
	public String deleteCart() {
		return "/deleteCart";
	}
	
	@RequestMapping("/project/removeCart.do")
	public String removeCart() {
		return "/removeCart";
	}
	
	@RequestMapping("/project/shippingInfo.do")
	public String shippingInfo() {
		return "/shippingInfo";
	}
	
	@RequestMapping("/project/checkOutCancelled.do")
	public String checkOutCancelled() {
		return "/checkOutCancelled";
	}
	
	@RequestMapping("/project/processShippingInfo.do")
	public String processShippingInfo() {
		return "/processShippingInfo";
	}
	
	@RequestMapping("/project/orderConfirmation.do")
	public String orderConfirmation() {
		return "/orderConfirmation";
	}
	
	@RequestMapping("/project/thankCustomer.do")
	public String thankCustomer() {
		return "/thankCustomer";
	}
	
	@RequestMapping("/project/delivery.do")
	public String delivery() {
		return "/delivery";
	}
	
}
