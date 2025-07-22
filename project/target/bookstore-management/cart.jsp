<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bookstore.model.CartItem" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Cart - Little Book Heaven</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="container">
        <header class="header">
            <h1 class="logo">📚 Little Book Heaven</h1>
            <p class="tagline">Shopping Cart</p>
        </header>

        <div class="cart-container">
            <div class="cart-header">
                <h2>Your Shopping Cart</h2>
                <a href="cashier-dashboard" class="btn btn-secondary">← Back to Dashboard</a>
            </div>

            <% 
            List<CartItem> cart = (List<CartItem>) request.getAttribute("cart");
            Double totalPrice = (Double) request.getAttribute("totalPrice");
            
            if (cart != null && !cart.isEmpty()) {
            %>
                <div class="cart-items">
                    <% for (CartItem item : cart) { %>
                        <div class="cart-item">
                            <div class="item-image">
                                <% if (item.getBook().getImageUrl() != null && !item.getBook().getImageUrl().isEmpty()) { %>
                                    <img src="<%= item.getBook().getImageUrl() %>" alt="<%= item.getBook().getTitle() %>">
                                <% } else { %>
                                    <div class="book-placeholder">📖</div>
                                <% } %>
                            </div>
                            <div class="item-details">
                                <h3><%= item.getBook().getTitle() %></h3>
                                <p><strong>Author:</strong> <%= item.getBook().getAuthor() %></p>
                                <p><strong>Genre:</strong> <%= item.getBook().getGenre() %></p>
                                <p><strong>Price:</strong> $<%= String.format("%.2f", item.getBook().getPrice()) %></p>
                                <p><strong>Quantity:</strong> <%= item.getQuantity() %></p>
                                <p><strong>Total:</strong> $<%= String.format("%.2f", item.getTotalPrice()) %></p>
                            </div>
                            <div class="item-actions">
                                <form method="post" action="cart">
                                    <input type="hidden" name="action" value="remove">
                                    <input type="hidden" name="bookId" value="<%= item.getBook().getBookId() %>">
                                    <button type="submit" class="btn btn-danger btn-sm">Remove</button>
                                </form>
                            </div>
                        </div>
                    <% } %>
                </div>

                <div class="cart-summary">
                    <div class="summary-card">
                        <h3>Order Summary</h3>
                        <div class="summary-row">
                            <span>Total Items:</span>
                            <span><%= cart.size() %></span>
                        </div>
                        <div class="summary-row total">
                            <span><strong>Total Price:</strong></span>
                            <span><strong>$<%= String.format("%.2f", totalPrice) %></strong></span>
                        </div>
                        <div class="cart-actions">
                            <form method="post" action="cart" style="display: inline;">
                                <input type="hidden" name="action" value="clear">
                                <button type="submit" class="btn btn-secondary">Clear Cart</button>
                            </form>
                            <button class="btn btn-success">Proceed to Checkout</button>
                        </div>
                    </div>
                </div>
            <% } else { %>
                <div class="empty-cart">
                    <div class="empty-cart-icon">🛒</div>
                    <h3>Your cart is empty</h3>
                    <p>Add some books to your cart to get started</p>
                    <a href="cashier-dashboard" class="btn btn-primary">Browse Books</a>
                </div>
            <% } %>
        </div>
    </div>
</body>
</html>