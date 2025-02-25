/**
 * API Service for GhostProtocol Admin Panel
 */
class ApiService {
    constructor(baseUrl = 'http://54.215.16.4/api') {
        this.baseUrl = baseUrl;
    }

    /**
     * Make an API request
     * @param {string} endpoint - API endpoint
     * @param {string} method - HTTP method
     * @param {object} data - Request data
     * @returns {Promise<object>} - Response data
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
            
            if (!response.ok) {
                const errorData = await response.json().catch(() => ({}));
                throw new Error(errorData.error || `Request failed with status ${response.status}`);
            }
            
            return await response.json();
        } catch (error) {
            console.error('API request error:', error);
            throw error;
        }
    }

    /**
     * Get all users
     * @returns {Promise<object>} - Users data
     */
    async getUsers() {
        return this.request('/users');
    }

    /**
     * Get a specific user
     * @param {string} userId - User ID
     * @returns {Promise<object>} - User data
     */
    async getUser(userId) {
        return this.request(`/users/${userId}`);
    }

    /**
     * Create a new user
     * @param {object} userData - User data
     * @returns {Promise<object>} - Created user data
     */
    async createUser(userData) {
        return this.request('/users', 'POST', userData);
    }

    /**
     * Update a user
     * @param {string} userId - User ID
     * @param {object} userData - User data
     * @returns {Promise<object>} - Updated user data
     */
    async updateUser(userId, userData) {
        return this.request(`/users/${userId}`, 'PUT', userData);
    }

    /**
     * Delete a user
     * @param {string} userId - User ID
     * @returns {Promise<object>} - Response data
     */
    async deleteUser(userId) {
        return this.request(`/users/${userId}`, 'DELETE');
    }

    /**
     * Ban a user
     * @param {string} userId - User ID
     * @returns {Promise<object>} - Response data
     */
    async banUser(userId) {
        return this.request(`/users/${userId}/ban`);
    }

    /**
     * Get a user's subscription
     * @param {string} userId - User ID
     * @returns {Promise<object>} - Subscription data
     */
    async getUserSubscription(userId) {
        return this.request(`/users/${userId}/subscription`);
    }

    /**
     * Update a user's subscription
     * @param {string} userId - User ID
     * @param {object} subscriptionData - Subscription data
     * @returns {Promise<object>} - Updated subscription data
     */
    async updateUserSubscription(userId, subscriptionData) {
        return this.request(`/users/${userId}/subscription`, 'PUT', subscriptionData);
    }

    /**
     * Get all subscription tiers
     * @returns {Promise<object>} - Subscription tiers data
     */
    async getSubscriptionTiers() {
        return this.request('/subscriptions');
    }

    /**
     * Get a specific subscription tier
     * @param {string} tierId - Tier ID
     * @returns {Promise<object>} - Subscription tier data
     */
    async getSubscriptionTier(tierId) {
        return this.request(`/subscriptions/${tierId}`);
    }

    /**
     * Create a new subscription tier
     * @param {object} tierData - Tier data
     * @returns {Promise<object>} - Created tier data
     */
    async createSubscriptionTier(tierData) {
        return this.request('/subscriptions', 'POST', tierData);
    }

    /**
     * Update a subscription tier
     * @param {string} tierId - Tier ID
     * @param {object} tierData - Tier data
     * @returns {Promise<object>} - Updated tier data
     */
    async updateSubscriptionTier(tierId, tierData) {
        return this.request(`/subscriptions/${tierId}`, 'PUT', tierData);
    }

    /**
     * Delete a subscription tier
     * @param {string} tierId - Tier ID
     * @returns {Promise<object>} - Response data
     */
    async deleteSubscriptionTier(tierId) {
        return this.request(`/subscriptions/${tierId}`, 'DELETE');
    }

    /**
     * Get user's vault items
     * @param {string} userId - User ID
     * @returns {Promise<object>} - Vault items data
     */
    async getUserVaultItems(userId) {
        return this.request(`/users/${userId}/vault`);
    }

    /**
     * Get dashboard statistics
     * @returns {Promise<object>} - Dashboard statistics
     */
    async getDashboardStats() {
        return this.request('/analytics/dashboard');
    }

    /**
     * Get user analytics
     * @returns {Promise<object>} - User analytics
     */
    async getUserAnalytics() {
        return this.request('/analytics/users');
    }

    /**
     * Get message analytics
     * @returns {Promise<object>} - Message analytics
     */
    async getMessageAnalytics() {
        return this.request('/analytics/messages');
    }

    /**
     * Get subscription analytics
     * @returns {Promise<object>} - Subscription analytics
     */
    async getSubscriptionAnalytics() {
        return this.request('/analytics/subscriptions');
    }

    /**
     * Get system analytics
     * @returns {Promise<object>} - System analytics
     */
    async getSystemAnalytics() {
        return this.request('/analytics/system');
    }
}

// Create a global API service instance
const apiService = new ApiService();
