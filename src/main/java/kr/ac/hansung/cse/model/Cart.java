package kr.ac.hansung.cse.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Cart implements Serializable{ // Cart를 JSON form으로 직렬화
	// 도메인 모델을 네트워크, db로 전송하려면 Serialize를 해야한다. 
	private static final long serialVersionUID = 1413783241344225820L;

	@Id
	@GeneratedValue
	private int cartId;
	
	@OneToMany(mappedBy="cart", cascade=CascadeType.ALL, fetch=FetchType.EAGER)// fetch default LAZY
	private List<CartItem> cartItems = new ArrayList<CartItem>(); // 테이블에 컬럼x
	
	private double grandTotal;
}
