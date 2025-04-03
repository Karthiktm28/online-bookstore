package com.bookstore.order_service.service;

import com.bookstore.order_service.dto.Book;
import com.bookstore.order_service.model.Order;
import com.bookstore.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Order placeOrder(Order orderRequest) {
        try {
            String bookServiceUrl = "http://BOOK-SERVICE/books/" + orderRequest.getBookId();
            Book book = restTemplate.getForObject(bookServiceUrl, Book.class);

            if (book == null) {
                throw new RuntimeException("Book not found");
            }

            orderRequest.setBookName(book.getTitle());
            orderRequest.setPrice(19.99);
            return orderRepository.save(orderRequest);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to place order: " + e.getMessage());
        }
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}