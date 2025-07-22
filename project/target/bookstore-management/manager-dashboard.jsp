<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bookstore.model.CashierEmployee" %>
<%@ page import="com.bookstore.model.Manager" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manager Dashboard - Little Book Heaven</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="dashboard-container">
        <nav class="sidebar">
            <div class="sidebar-header">
                <h2>📚 Little Book Heaven</h2>
                <p>Manager Portal</p>
            </div>
            
            <div class="user-info">
                <% Manager manager = (Manager) session.getAttribute("manager"); %>
                <div class="user-avatar">👨‍💼</div>
                <h3><%= manager.getName() %></h3>
                <p><%= manager.getRole() %></p>
            </div>

            <ul class="nav-menu">
                <li><a href="#cashiers" class="nav-link active" onclick="showSection('cashiers')">👥 Manage Cashiers</a></li>
                <li><a href="manager-dashboard?action=logout" class="nav-link logout">🚪 Logout</a></li>
            </ul>
        </nav>

        <main class="main-content">
            <div id="cashiers" class="content-section active">
                <div class="section-header">
                    <h1>Cashier Management</h1>
                    <button class="btn btn-primary" onclick="showAddCashierModal()">+ Add New Cashier</button>
                </div>

                <div class="cashiers-grid">
                    <% 
                    List<CashierEmployee> cashiers = (List<CashierEmployee>) request.getAttribute("cashiers");
                    if (cashiers != null && !cashiers.isEmpty()) {
                        for (CashierEmployee cashier : cashiers) {
                    %>
                        <div class="cashier-card">
                            <div class="cashier-avatar">👩‍💻</div>
                            <div class="cashier-info">
                                <h3><%= cashier.getName() %></h3>
                                <p><strong>Username:</strong> <%= cashier.getUsername() %></p>
                                <p><strong>Email:</strong> <%= cashier.getEmail() %></p>
                                <p><strong>Employee ID:</strong> <%= cashier.getEmployeeId() %></p>
                                <p><strong>Department:</strong> <%= cashier.getDepartment() %></p>
                            </div>
                            <div class="cashier-actions">
                                <button class="btn btn-secondary btn-sm" onclick="editCashier('<%= cashier.getId() %>', '<%= cashier.getUsername() %>', '<%= cashier.getName() %>', '<%= cashier.getEmail() %>', '<%= cashier.getEmployeeId() %>', '<%= cashier.getDepartment() %>')">Edit</button>
                                <button class="btn btn-danger btn-sm" onclick="deleteCashier('<%= cashier.getId() %>')">Delete</button>
                            </div>
                        </div>
                    <% 
                        }
                    } else {
                    %>
                        <div class="empty-state">
                            <h3>No cashiers found</h3>
                            <p>Add your first cashier to get started</p>
                        </div>
                    <% } %>
                </div>
            </div>
        </main>
    </div>

    <!-- Add Cashier Modal -->
    <div id="addCashierModal" class="modal">
        <div class="modal-content">
            <div class="modal-header">
                <h2>Add New Cashier</h2>
                <span class="close" onclick="closeModal('addCashierModal')">&times;</span>
            </div>
            <form method="post" action="manager-dashboard">
                <input type="hidden" name="action" value="add">
                <div class="form-group">
                    <label for="id">ID</label>
                    <input type="text" id="id" name="id" required>
                </div>
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <div class="form-group">
                    <label for="name">Full Name</label>
                    <input type="text" id="name" name="name" required>
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" required>
                </div>
                <div class="form-group">
                    <label for="employeeId">Employee ID</label>
                    <input type="text" id="employeeId" name="employeeId" required>
                </div>
                <div class="form-group">
                    <label for="department">Department</label>
                    <input type="text" id="department" name="department" required>
                </div>
                <div class="modal-actions">
                    <button type="button" class="btn btn-secondary" onclick="closeModal('addCashierModal')">Cancel</button>
                    <button type="submit" class="btn btn-primary">Add Cashier</button>
                </div>
            </form>
        </div>
    </div>

    <!-- Edit Cashier Modal -->
    <div id="editCashierModal" class="modal">
        <div class="modal-content">
            <div class="modal-header">
                <h2>Edit Cashier</h2>
                <span class="close" onclick="closeModal('editCashierModal')">&times;</span>
            </div>
            <form method="post" action="manager-dashboard">
                <input type="hidden" name="action" value="update">
                <input type="hidden" id="editId" name="id">
                <div class="form-group">
                    <label for="editUsername">Username</label>
                    <input type="text" id="editUsername" name="username" required>
                </div>
                <div class="form-group">
                    <label for="editPassword">Password</label>
                    <input type="password" id="editPassword" name="password" required>
                </div>
                <div class="form-group">
                    <label for="editName">Full Name</label>
                    <input type="text" id="editName" name="name" required>
                </div>
                <div class="form-group">
                    <label for="editEmail">Email</label>
                    <input type="email" id="editEmail" name="email" required>
                </div>
                <div class="form-group">
                    <label for="editEmployeeId">Employee ID</label>
                    <input type="text" id="editEmployeeId" name="employeeId" required>
                </div>
                <div class="form-group">
                    <label for="editDepartment">Department</label>
                    <input type="text" id="editDepartment" name="department" required>
                </div>
                <div class="modal-actions">
                    <button type="button" class="btn btn-secondary" onclick="closeModal('editCashierModal')">Cancel</button>
                    <button type="submit" class="btn btn-primary">Update Cashier</button>
                </div>
            </form>
        </div>
    </div>

    <script src="js/dashboard.js"></script>
</body>
</html>