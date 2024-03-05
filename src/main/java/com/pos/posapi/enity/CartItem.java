package com.pos.posapi.enity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private int cartItemId;

    @ManyToOne
    @JoinColumn(name = "item_id",referencedColumnName = "item_id")
    Item item;

    @ManyToOne
    @JoinColumn(name = "cart_id",referencedColumnName = "cart_id")
    Cart cart;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    private int quantity;
}
