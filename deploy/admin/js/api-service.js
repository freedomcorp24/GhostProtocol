/**
 * API Service for GhostProtocol Admin Panel
 */
class ApiService {
  constructor(baseUrl) {
    this.baseUrl = baseUrl || 'http://54.215.16.4/api';
  }

  /**
   * Make an API request
   */
  async request(endpoint, method = 'GET', data = null) {
    const url = `${this.baseUrl}${endpoint}`;
    const options = {
      method,
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      }
    };

    if (data && (method === 'POST' || method === 'PUT')) {
      options.body = JSON.stringify(data);
    }

    try {
      const response = await fetch(url, options);
      const responseData = await response.json();
      
      if (!response.ok) {
        throw new Error(responseData.error || 'API request failed');
      }
      
      return responseData;
    } catch (error) {
      console.error('API request error:', error);
      throw error;
    }
  }

  // User Management API methods
  async getUsers() {
    return this.request('/users');
  }

  async getUser(userId) {
    return this.request(`/users/${userId}`);
  }

  async createUser(userData) {
    return this.request('/users', 'POST', userData);
  }

  async updateUser(userId, userData) {
    return this.request(`/users/${userId}`, 'PUT', userData);
  }

  async deleteUser(userId) {
    return this.request(`/users/${userId}`, 'DELETE');
  }

  async banUser(userId) {
    return this.request(`/users/${userId}/ban`, 'GET');
  }

  // Subscription Management API methods
  async getSubscriptionTiers() {
    return this.request('/subscriptions');
  }

  async getSubscriptionTier(tierId) {
    return this.request(`/subscriptions/${tierId}`);
  }

  async createSubscriptionTier(tierData) {
    return this.request('/subscriptions', 'POST', tierData);
  }

  async updateSubscriptionTier(tierId, tierData) {
    return this.request(`/subscriptions/${tierId}`, 'PUT', tierData);
  }

  async deleteSubscriptionTier(tierId) {
    return this.request(`/subscriptions/${tierId}`, 'DELETE');
  }

  // Analytics API methods
  async getDashboardStats() {
    return this.request('/analytics/dashboard');
  }
}
