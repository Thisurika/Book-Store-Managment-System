<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bookstore.model.Book" %>
<%@ page import="com.bookstore.model.CashierEmployee" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cashier Dashboard - Little Book Heaven</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="dashboard-container">
        <nav class="sidebar">
            <div class="sidebar-header">
                <h2>📚 Little Book Heaven</h2>
                <p>Cashier Portal</p>
            </div>
            
            <div class="user-info">
                <% CashierEmployee cashier = (CashierEmployee) session.getAttribute("cashier"); %>
                <div class="user-avatar">👩‍💻</div>
                <h3><%= cashier.getName() %></h3>
                <p><%= cashier.getRole() %></p>
            </div>

            <ul class="nav-menu">
                <li><a href="#books" class="nav-link active" onclick="showSection('books')">📚 Manage Books</a></li>
                <li><a href="#search" class="nav-link" onclick="showSection('search')">🔍 Search Books</a></li>
                <li><a href="cart" class="nav-link">🛒 Shopping Cart</a></li>
                <li><a href="cashier-dashboard?action=logout" class="nav-link logout">🚪 Logout</a></li>
            </ul>
        </nav>

        <main class="main-content">
            <div id="books" class="content-section active">
                <div class="section-header">
                    <h1>Book Management</h1>
                    <button class="btn btn-primary" onclick="showAddBookModal()">+ Add New Book</button>
                </div>

                <div class="books-grid">
                    <% 
                    List<Book> books = (List<Book>) request.getAttribute("books");
                    if (books != null && !books.isEmpty()) {
                        for (Book book : books) {
                    %>
                        <div class="book-card">
                            <div class="book-image">
                                <% if (book.getImageUrl() != null && !book.getImageUrl().isEmpty()) { %>
                                    <img src="<%= book.getImageUrl() %>" alt="<%= book.getTitle() %>">
                                <% } else { %>
                                    <div class="book-placeholder">📖</div>
                                <% } %>
                            </div>
                            <div class="book-info">
                                <h3><%= book.getTitle() %></h3>
                                <p><strong>Author:</strong> <%= book.getAuthor() %></p>
                                <p><strong>Genre:</strong> <%= book.getGenre() %></p>
                                <p><strong>Price:</strong> $<%= String.format("%.2f", book.getPrice()) %></p>
                                <p><strong>Quantity:</strong> <%= book.getQuantity() %></p>
                            </div>
                            <div class="book-actions">
                                <button class="btn btn-secondary btn-sm" onclick="editBook('<%= book.getBookId() %>', '<%= book.getTitle() %>', '<%= book.getAuthor() %>', '<%= book.getGenre() %>', '<%= book.getPrice() %>', '<%= book.getImageUrl() %>', '<%= book.getQuantity() %>')">Edit</button>
                                <button class="btn btn-danger btn-sm" onclick="deleteBook('<%= book.getBookId() %>')">Delete</button>
                                <form method="post" action="cart" style="display: inline;">
                                    <input type="hidden" name="action" value="add">
                                    <input type="hidden" name="bookId" value="<%= book.getBookId() %>">
                                    <input type="hidden" name="quantity" value="1">
                                    <button type="submit" class="btn btn-success btn-sm">Add to Cart</button>
                                </form>
                            </div>
                        </div>
                    <% 
                        }
                    } else {
                    %>
                        <div class="empty-state">
                            <h3>No books found</h3>
                            <p>Add your first book to get started</p>
                        </div>
                    <% } %>
                </div>
            </div>

            <div id="search" class="content-section">
                <div class="section-header">
                    <h1>Search Books by Genre</h1>
                </div>

                <div class="search-container">
                    <form method="get" action="cashier-dashboard" class="search-form">
                        <input type="hidden" name="action" value="search">
                        <div class="form-group">
                            <label for="genre">Genre</label>
                            <select id="genre" name="genre" required>
                                <option value="">Select a genre</option>
                                <option value="Fiction">Fiction</option>
                                <option value="Non-Fiction">Non-Fiction</option>
                                <option value="Mystery">Mystery</option>
                                <option value="Romance">Romance</option>
                                <option value="Science Fiction">Science Fiction</option>
                                <option value="Fantasy">Fantasy</option>
                                <option value="Biography">Biography</option>
                                <option value="History">History</option>
                                <option value="Self-Help">Self-Help</option>
                                <option value="Children">Children</option>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary">Search with Merge Sort</button>
                    </form>

                    <% String searchGenre = (String) request.getAttribute("searchGenre"); %>
                    <% if (searchGenre != null) { %>
                        <div class="search-results">
                            <h3>Search Results for "<%= searchGenre %>" (Sorted by Title)</h3>
                            <div class="books-grid">
                                <% 
                                List<Book> searchBooks = (List<Book>) request.getAttribute("books");
                                if (searchBooks != null && !searchBooks.isEmpty()) {
                                    for (Book book : searchBooks) {
                                %>
                                    <div class="book-card">
                                        <div class="book-image">
                                            <% if (book.getImageUrl() != null && !book.getImageUrl().isEmpty()) { %>
                                                <img src="<%= book.getImageUrl() %>" alt="<%= book.getTitle() %>">
                                            <% } else { %>
                                                <div class="book-placeholder">📖</div>
                                            <% } %>
                                        </div>
                                        <div class="book-info">
                                            <h3><%= book.getTitle() %></h3>
                                            <p><strong>Author:</strong> <%= book.getAuthor() %></p>
                                            <p><strong>Genre:</strong> <%= book.getGenre() %></p>
                                            <p><strong>Price:</strong> $<%= String.format("%.2f", book.getPrice()) %></p>
                                            <p><strong>Quantity:</strong> <%= book.getQuantity() %></p>
                                        </div>
                                        <div class="book-actions">
                                            <form method="post" action="cart" style="display: inline;">
                                                <input type="hidden" name="action" value="add">
                                                <input type="hidden" name="bookId" value="<%= book.getBookId() %>">
                                                <input type="hidden" name="quantity" value="1">
                                                <button type="submit" class="btn btn-success btn-sm">Add to Cart</button>
                                            </form>
                                        </div>
                                    </div>
                                <% 
                                    }
                                } else {
                                %>
                                    <p>No books found in the "<%= searchGenre %>" genre.</p>
                                <% } %>
                            </div>
                        </div>
                    <% } %>
                </div>
            </div>
        </main>
    </div>

    <!-- Add Book Modal -->
    <div id="addBookModal" class="modal">
        <div class="modal-content">
            <div class="modal-header">
                <h2>Add New Book</h2>
                <span class="close" onclick="closeModal('addBookModal')">&times;</span>
            </div>
            <form method="post" action="cashier-dashboard">
                <input type="hidden" name="action" value="add">
                <div class="form-group">
                    <label for="bookId">Book ID</label>
                    <input type="text" id="bookId" name="bookId" required>
                </div>
                <div class="form-group">
                    <label for="title">Title</label>
                    <input type="text" id="title" name="title" required>
                </div>
                <div class="form-group">
                    <label for="author">Author</label>
                    <input type="text" id="author" name="author" required>
                </div>
                <div class="form-group">
                    <label for="genre">Genre</label>
                    <select id="genre" name="genre" required>
                        <option value="">Select a genre</option>
                        <option value="Fiction">Fiction</option>
                        <option value="Non-Fiction">Non-Fiction</option>
                        <option value="Mystery">Mystery</option>
                        <option value="Romance">Romance</option>
                        <option value="Science Fiction">Science Fiction</option>
                        <option value="Fantasy">Fantasy</option>
                        <option value="Biography">Biography</option>
                        <option value="History">History</option>
                        <option value="Self-Help">Self-Help</option>
                        <option value="Children">Children</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="price">Price ($)</label>
                    <input type="number" id="price" name="price" step="0.01" min="0" required>
                </div>
                <div class="form-group">
                    <label for="imageUrl">Image URL</label>
                    <input type="url" id="imageUrl" name="imageUrl" placeholder="https://example.com/book-image.jpg">
                </div>
                <div class="form-group">
                    <label for="quantity">Quantity</label>
                    <input type="number" id="quantity" name="quantity" min="0" required>
                </div>
                <div class="modal-actions">
                    <button type="button" class="btn btn-secondary" onclick="closeModal('addBookModal')">Cancel</button>
                    <button type="submit" class="btn btn-primary">Add Book</button>
                </div>
            </form>
        </div>
    </div>

    <!-- Edit Book Modal -->
    <div id="editBookModal" class="modal">
        <div class="modal-content">
            <div class="modal-header">
                <h2>Edit Book</h2>
                <span class="close" onclick="closeModal('editBookModal')">&times;</span>
            </div>
            <form method="post" action="cashier-dashboard">
                <input type="hidden" name="action" value="update">
                <input type="hidden" id="editBookId" name="bookId">
                <div class="form-group">
                    <label for="editTitle">Title</label>
                    <input type="text" id="editTitle" name="title" required>
                </div>
                <div class="form-group">
                    <label for="editAuthor">Author</label>
                    <input type="text" id="editAuthor" name="author" required>
                </div>
                <div class="form-group">
                    <label for="editGenre">Genre</label>
                    <select id="editGenre" name="genre" required>
                        <option value="">Select a genre</option>
                        <option value="Fiction">Fiction</option>
                        <option value="Non-Fiction">Non-Fiction</option>
                        <option value="Mystery">Mystery</option>
                        <option value="Romance">Romance</option>
                        <option value="Science Fiction">Science Fiction</option>
                        <option value="Fantasy">Fantasy</option>
                        <option value="Biography">Biography</option>
                        <option value="History">History</option>
                        <option value="Self-Help">Self-Help</option>
                        <option value="Children">Children</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="editPrice">Price ($)</label>
                    <input type="number" id="editPrice" name="price" step="0.01" min="0" required>
                </div>
                <div class="form-group">
                    <label for="editImageUrl">Image URL</label>
                    <input type="url" id="editImageUrl" name="imageUrl" placeholder="https://example.com/book-image.jpg">
                </div>
                <div class="form-group">
                    <label for="editQuantity">Quantity</label>
                    <input type="number" id="editQuantity" name="quantity" min="0" required>
                </div>
                <div class="modal-actions">
                    <button type="button" class="btn btn-secondary" onclick="closeModal('editBookModal')">Cancel</button>
                    <button type="submit" class="btn btn-primary">Update Book</button>
                </div>
            </form>
        </div>
    </div>

    <script src="js/dashboard.js"></script>
</body>
</html>