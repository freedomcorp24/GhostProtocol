/**
 * Main Application for GhostProtocol Admin Panel
 */
document.addEventListener('DOMContentLoaded', async () => {
  // Initialize API service
  const apiService = new ApiService();
  
  // Initialize components
  const dashboard = new Dashboard(apiService);
  const userManagement = new UserManagement(apiService);
  const subscriptionManagement = new SubscriptionManagement(apiService);
  
  // Set up tab navigation
  const tabs = document.querySelectorAll('.tab-link');
  const tabContents = document.querySelectorAll('.tab-content');
  
  tabs.forEach(tab => {
    tab.addEventListener('click', (e) => {
      e.preventDefault();
      
      // Remove active class from all tabs and tab contents
      tabs.forEach(t => t.classList.remove('active'));
      tabContents.forEach(content => content.classList.remove('active'));
      
      // Add active class to clicked tab and corresponding content
      tab.classList.add('active');
      const tabId = tab.getAttribute('href').substring(1);
      document.getElementById(tabId).classList.add('active');
    });
  });
  
  // Initialize dashboard by default
  await dashboard.init();
  
  // Initialize other components when their tabs are clicked
  document.querySelector('a[href="#users-tab"]').addEventListener('click', async () => {
    if (!userManagement.initialized) {
      await userManagement.init();
      userManagement.initialized = true;
    }
  });
  
  document.querySelector('a[href="#subscriptions-tab"]').addEventListener('click', async () => {
    if (!subscriptionManagement.initialized) {
      await subscriptionManagement.init();
      subscriptionManagement.initialized = true;
    }
  });
  
  // Show loading indicator
  const loadingIndicator = document.getElementById('loading-indicator');
  if (loadingIndicator) {
    loadingIndicator.style.display = 'none';
  }
  
  // Show main content
  const mainContent = document.getElementById('main-content');
  if (mainContent) {
    mainContent.style.display = 'block';
  }
});
