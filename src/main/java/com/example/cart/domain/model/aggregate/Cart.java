package com.example.cart.domain.model.aggregate;

import com.example.cart.domain.model.valueobjects.CartItem;
import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
@Getter
@NoArgsConstructor
@TypeDef(name = "json", typeClass = JsonType.class)
public class Cart extends AbstractAggregateRoot<Cart> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user")
    private String user;

    @Type(type = "json")
    @Column(name = "items", columnDefinition = "json")
    private List<CartItem> items = new ArrayList<>();

    public Cart(String user) {
        this.user = user;
    }

    public void addItem(CartItem item) {
        this.items.add(item);
    }
}
