<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Little Book Heaven - Login</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="container">
        <header class="header">
            <h1 class="logo">📚 Little Book Heaven</h1>
            <p class="tagline">Your Gateway to Literary Paradise</p>
        </header>

        <div class="login-container">
            <div class="login-card">
                <h2>Welcome Back!</h2>
                <p>Choose your login type to continue</p>
                
                <div class="login-options">
                    <div class="login-option">
                        <div class="option-icon manager-icon">👨‍💼</div>
                        <h3>Manager Login</h3>
                        <p>Access management dashboard and cashier accounts</p>
                        <a href="manager-login" class="btn btn-manager">Login as Manager</a>
                    </div>
                    
                    <div class="login-option">
                        <div class="option-icon cashier-icon">👩‍💻</div>
                        <h3>Cashier Login</h3>
                        <p>Manage books, inventory, and customer orders</p>
                        <a href="cashier-login" class="btn btn-cashier">Login as Cashier</a>
                    </div>
                </div>
            </div>
        </div>

        <footer class="footer">
            <p>&copy; 2024 Little Book Heaven. All rights reserved.</p>
        </footer>
    </div>
</body>
</html>