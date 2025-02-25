/**
 * Dashboard Component for GhostProtocol Admin Panel
 */
class Dashboard {
  constructor(apiService) {
    this.apiService = apiService;
    this.stats = null;
  }

  /**
   * Initialize the dashboard component
   */
  async init() {
    await this.loadDashboardStats();
    this.renderDashboard();
    this.setupRefreshInterval();
  }

  /**
   * Load dashboard statistics from the API
   */
  async loadDashboardStats() {
    try {
      const response = await this.apiService.getDashboardStats();
      this.stats = response;
      return this.stats;
    } catch (error) {
      console.error('Error loading dashboard stats:', error);
      return null;
    }
  }

  /**
   * Render the dashboard with the loaded statistics
   */
  renderDashboard() {
    if (!this.stats) {
      this.showError('Failed to load dashboard statistics');
      return;
    }

    // Update user statistics
    document.getElementById('total-users').textContent = this.stats.users.total_users.toLocaleString();
    document.getElementById('active-users').textContent = this.stats.users.active_users.toLocaleString();
    document.getElementById('new-users').textContent = this.stats.users.new_users_today.toLocaleString();

    // Update message statistics
    document.getElementById('messages-today').textContent = this.stats.messages.messages_today.toLocaleString();
    document.getElementById('media-today').textContent = this.stats.messages.media_shared_today.toLocaleString();
    document.getElementById('active-groups').textContent = this.stats.messages.active_groups.toLocaleString();

    // Update subscription statistics
    const subscriptionStats = this.stats.subscriptions.subscription_distribution;
    document.getElementById('free-users').textContent = subscriptionStats.free.toLocaleString();
    document.getElementById('premium-users').textContent = subscriptionStats.premium.toLocaleString();
    document.getElementById('business-users').textContent = subscriptionStats.business.toLocaleString();

    // Update system statistics
    document.getElementById('system-status').textContent = this.stats.system.status;
    document.getElementById('api-response').textContent = `${this.stats.system.api_response_time}ms`;
    document.getElementById('storage-usage').textContent = `${this.stats.system.storage_usage}%`;
    document.getElementById('cpu-usage').textContent = `${this.stats.system.cpu_usage}%`;
    document.getElementById('memory-usage').textContent = `${this.stats.system.memory_usage}%`;

    // Update last updated timestamp
    const lastUpdated = new Date().toLocaleString();
    document.getElementById('last-updated').textContent = lastUpdated;
  }

  /**
   * Set up an interval to refresh the dashboard every 60 seconds
   */
  setupRefreshInterval() {
    setInterval(async () => {
      await this.loadDashboardStats();
      this.renderDashboard();
    }, 60000); // Refresh every 60 seconds
  }

  /**
   * Show an error message on the dashboard
   */
  showError(message) {
    const errorContainer = document.getElementById('dashboard-error');
    if (errorContainer) {
      errorContainer.textContent = message;
      errorContainer.style.display = 'block';
    }
  }
}
