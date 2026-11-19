package com.growmighty.lectures.firstday.tangledmonolith.order;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(nullable = false)
    private Long productId;

    // 주문 당시 상품 정보 스냅샷: 이후 상품명·가격이 바뀌어도 주문 내역은 그대로 유지된다
    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private BigDecimal unitPrice;

    @Column(nullable = false)
    private Integer quantity;

    public static OrderItem create(Long productId, String productName, BigDecimal unitPrice, int quantity) {
        OrderItem orderItem = new OrderItem();
        orderItem.productId = productId;
        orderItem.productName = productName;
        orderItem.unitPrice = unitPrice;
        orderItem.quantity = quantity;

        return orderItem;
    }

    void assignOrder(Order order) {
        this.order = order;
    }
}
