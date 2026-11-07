package com.devsuperior.dscommerce.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.devsuperior.dscommerce.entities.Order;
import com.devsuperior.dscommerce.entities.OrderItem;
import com.devsuperior.dscommerce.entities.OrderStatus;

import jakarta.validation.constraints.NotEmpty;

public class OrderDTO {

	private Long id;
	private Instant moment;
	private OrderStatus status;
	private ClientDTO client;
	
	private PaymentDTO payment;

	@NotEmpty(message = "Deve ter pelo menos um item.")
	List<OrderItemDTO> items = new ArrayList<>();
	
	private Double total;
	
	public OrderDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public OrderDTO(Long id, Instant moment, OrderStatus status, ClientDTO clientDTO, PaymentDTO paymentDTO, String imgUrl) {
		this.id = id;
		this.moment = moment;
		this.status = status;
		this.client = clientDTO;
		this.payment = paymentDTO;
	}

	public OrderDTO(Order entity) {
		this.id = entity.getId();
		this.moment = entity.getMoment();
		this.status = entity.getStatus();
		this.client = new ClientDTO(entity.getClient());
		this.payment = 
				(entity.getPayment() == null) ? null : new PaymentDTO(entity.getPayment());
		
		for(OrderItem item: entity.getItems()) {
			this.items.add(new OrderItemDTO(item));
		}
	}

	public Long getId() {
		return id;
	}

	public Instant getMoment() {
		return moment;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public ClientDTO getClient() {
		return client;
	}

	public PaymentDTO getPayment() {
		return payment;
	}

	public List<OrderItemDTO> getItems() {
		return items;
	}

	public Double getTotal() {
		this.total = 0.0;
		for(OrderItemDTO dto: this.items) {
			this.total = this.total + dto.getSubTotal();
		}
		return total;
	}
	
}
