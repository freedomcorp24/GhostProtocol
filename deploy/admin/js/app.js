/**
 * Main Application for GhostProtocol Admin Panel
 */
document.addEventListener('DOMContentLoaded', () => {
    // Initialize modules
    const userManagement = new UserManagement(apiService);
    const subscriptionManagement = new SubscriptionManagement(apiService);
    const dashboard = new Dashboard(apiService);
    
    // Tab navigation
    const navItems = document.querySelectorAll('.nav-item');
    const tabContents = document.querySelectorAll('.tab-content');
    
    navItems.forEach(item => {
        item.addEventListener('click', () => {
            const tabId = item.getAttribute('data-tab');
            
            // Update active nav item
            navItems.forEach(navItem => navItem.classList.remove('active'));
            item.classList.add('active');
            
            // Show the selected tab content
            tabContents.forEach(content => {
                if (content.id === tabId) {
                    content.classList.add('active');
                } else {
                    content.classList.remove('active');
                }
            });
        });
    });
    
    // Analytics tabs
    const analyticsTabs = document.querySelectorAll('.analytics-tab');
    const analyticsContents = document.querySelectorAll('.analytics-tab-content');
    
    analyticsTabs.forEach(tab => {
        tab.addEventListener('click', () => {
            const tabId = tab.getAttribute('data-analytics-tab');
            
            // Update active analytics tab
            analyticsTabs.forEach(analyticsTab => analyticsTab.classList.remove('active'));
            tab.classList.add('active');
            
            // Show the selected analytics content
            analyticsContents.forEach(content => {
                if (content.id === `${tabId}-analytics`) {
                    content.classList.add('active');
                } else {
                    content.classList.remove('active');
                }
            });
        });
    });
    
    // Settings forms
    const generalSettingsForm = document.getElementById('general-settings-form');
    const securitySettingsForm = document.getElementById('security-settings-form');
    const apiSettingsForm = document.getElementById('api-settings-form');
    
    generalSettingsForm.addEventListener('submit', (e) => {
        e.preventDefault();
        showAlert('General settings saved successfully', 'success');
    });
    
    securitySettingsForm.addEventListener('submit', (e) => {
        e.preventDefault();
        showAlert('Security settings saved successfully', 'success');
    });
    
    apiSettingsForm.addEventListener('submit', (e) => {
        e.preventDefault();
        showAlert('API settings saved successfully', 'success');
    });
    
    // Logout button
    const logoutBtn = document.getElementById('logout-btn');
    logoutBtn.addEventListener('click', () => {
        if (confirm('Are you sure you want to logout?')) {
            showAlert('Logged out successfully', 'success');
            // In a real implementation, this would redirect to the login page
        }
    });
});

/**
 * Show an alert message
 * @param {string} message - Alert message
 * @param {string} type - Alert type (success, error, warning)
 */
function showAlert(message, type = 'info') {
    const alert = document.getElementById('alert');
    const alertMessage = alert.querySelector('.alert-message');
    const alertContent = alert.querySelector('.alert-content');
    
    // Set message and type
    alertMessage.textContent = message;
    alertContent.className = 'alert-content';
    alertContent.classList.add(type);
    
    // Show the alert
    alert.style.display = 'block';
    
    // Hide the alert after 5 seconds
    setTimeout(() => {
        alert.style.display = 'none';
    }, 5000);
    
    // Close button
    const closeBtn = alert.querySelector('.alert-close');
    closeBtn.addEventListener('click', () => {
        alert.style.display = 'none';
    });
}

/**
 * Show loading overlay
 * @param {string} message - Loading message
 */
function showLoading(message = 'Loading...') {
    const loadingOverlay = document.getElementById('loading-overlay');
    const loadingMessage = loadingOverlay.querySelector('.loading-message');
    
    loadingMessage.textContent = message;
    loadingOverlay.style.display = 'flex';
}

/**
 * Hide loading overlay
 */
function hideLoading() {
    const loadingOverlay = document.getElementById('loading-overlay');
    loadingOverlay.style.display = 'none';
}
