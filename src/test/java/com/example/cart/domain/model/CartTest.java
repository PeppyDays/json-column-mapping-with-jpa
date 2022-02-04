package com.example.cart.domain.model;

import com.example.cart.domain.model.aggregate.Cart;
import com.example.cart.domain.model.valueobjects.CartItem;
import com.example.cart.infrastructure.repositories.jpa.CartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CartTest {

    @Autowired
    CartRepository cartRepository;

    @Test
    @Commit
    void testCreateEmptyCart() {
        Cart cart = new Cart("peppydays");
        cartRepository.save(cart);

        // Hibernate: insert into carts (items, user) values (?, ?)
    }

    @Test
    @Commit
    void testCreateFilledCart() {
        Cart cart = new Cart("peppydays");
        cart.addItem(new CartItem("KNIFE", 3));
        cart.addItem(new CartItem("BOOK", 2));
        cartRepository.save(cart);

        // Hibernate: insert into carts (items, user) values (?, ?)
    }

    @Test
    @Commit
    void testAddItemIntoEmptyCart() {
        Cart cart = new Cart("peppydays");
        cartRepository.save(cart);
        cart.addItem(new CartItem("KNIFE", 3));
        cartRepository.save(cart);

        // Hibernate: insert into carts (items, user) values (?, ?)
        // Hibernate: update carts set items=?, user=? where id=?
    }

    @Test
    void testLoadCart() {
        // insert into carts values (100, 'dongkyun', '[{"sku": "KNIFE", "quantity": 5}, {"sku": "SPOON", "quantity": 10}]');

        Cart cart = cartRepository.findById(100L).get();
        assert cart.getUser().equals("dongkyun");
        assert cart.getItems().size() == 2;

        // Hibernate: select cart0_.id as id1_0_0_, cart0_.items as items2_0_0_, cart0_.user as user3_0_0_ from carts cart0_ where cart0_.id=?
    }

    @Test
    void testLoadableWhenAttributeKeyHasTwoNames() {
        // insert into carts values (101, 'hajoon', '[{"sku": "KNIFE", "quantity": 5, "qty": 5}]');

        Cart cart = cartRepository.findById(101L).get();
        assert cart.getItems().get(0).getQuantity() == 5;

        // Hibernate: select cart0_.id as id1_0_0_, cart0_.items as items2_0_0_, cart0_.user as user3_0_0_ from carts cart0_ where cart0_.id=?
    }
}
