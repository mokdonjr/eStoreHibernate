package kr.ac.hansung.cse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.hansung.cse.model.Cart;
import kr.ac.hansung.cse.model.CartItem;
import kr.ac.hansung.cse.model.Product;
import kr.ac.hansung.cse.model.User;
import kr.ac.hansung.cse.service.CartItemService;
import kr.ac.hansung.cse.service.CartService;
import kr.ac.hansung.cse.service.ProductService;
import kr.ac.hansung.cse.service.UserService;

@RestController // @Controller + @ResponseBody
@RequestMapping("/rest/cart")
public class CartRestController {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/{cartId}", method=RequestMethod.GET)
	public ResponseEntity<Cart> getCartById(@PathVariable(value="cartId") int cartId){
		Cart cart = cartService.getCartById(cartId);
		// cart객체를 Json으로 Serialization하여 response body에 JSON으로 넘겨줌
		return new ResponseEntity<Cart> (cart, HttpStatus.OK); 
	}
	
	@RequestMapping(value="/add/{productId}", method=RequestMethod.PUT)
	public ResponseEntity<Void> addItem(@PathVariable(value="productId") int productId){
		
		// 1. 로그인한 사용자가 누구인지 파악 (spring security)
		Authentication authentication = 
				SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		// 2. Cart 가져오기
		User user = userService.getUserByUsername(username);
		Cart cart = user.getCart();
		
		// 3. Product 가져오기
		Product product = productService.getProductById(productId);
		
		// 4-1. Cart내 Product에 대해 동일한 CartItem이 있는지 확인
		List<CartItem> cartItems = cart.getCartItems();
		for(int i=0; i<cartItems.size(); i++) {
			if(product.getId() == cartItems.get(i).getProduct().getId()) {
				CartItem cartItem = cartItems.get(i);
				cartItem.setQuantity(cartItem.getQuantity() + 1);
				cartItem.setTotalPrice(product.getPrice() * cartItem.getQuantity());
				cartItemService.addCartItem(cartItem); // save
				
				return new ResponseEntity<Void> (HttpStatus.OK);
			}
		}
		
		// 4-2. Cart 내 Product에 대해 동일한 CartItem이 없는 경우
		CartItem cartItem = new CartItem();
		cartItem.setProduct(product);
		cartItem.setQuantity(1);
		cartItem.setTotalPrice(product.getPrice() * cartItem.getQuantity());
		cartItem.setCart(cart);
		
		cartItemService.addCartItem(cartItem); // save
		
		cart.getCartItems().add(cartItem);
		product.getCartItemList().add(cartItem);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/cartitem/{productId}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> removeItem(@PathVariable(value="productId") int productId){
		
		// 1. 로그인한 사용자가 누구인지 파악 (spring security)
		Authentication authentication = 
				SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		// 2. Cart 가져오기
		User user = userService.getUserByUsername(username);
		Cart cart = user.getCart();
		
		CartItem cartItem = cartItemService.getCartItemByProductId(cart.getCartId(), productId);
		cartItemService.removeCartItem(cartItem);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		
	}
	
	@RequestMapping(value="/{cartId}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> clearCart(@PathVariable(value="cartId") int cartId){
		Cart cart = cartService.getCartById(cartId);
		cartItemService.removeAllCartItems(cart);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
