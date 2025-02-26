// API Service for GhostProtocol

const API_BASE_URL = 'http://54.215.16.4/api';

class ApiService {
  // Authentication methods
  async signup(username, password) {
    try {
      const response = await fetch(`${API_BASE_URL}/auth/signup`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username, password }),
      });
      
      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.error || 'Failed to sign up');
      }
      
      const data = await response.json();
      this.saveAuthData(data.token, data.user);
      return data;
    } catch (error) {
      console.error('Signup error:', error);
      throw error;
    }
  }
  
  async login(username, password) {
    try {
      const response = await fetch(`${API_BASE_URL}/auth/login`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username, password }),
      });
      
      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.error || 'Failed to log in');
      }
      
      const data = await response.json();
      this.saveAuthData(data.token, data.user);
      return data;
    } catch (error) {
      console.error('Login error:', error);
      throw error;
    }
  }
  
  async getCurrentUser() {
    try {
      const token = localStorage.getItem('authToken');
      
      if (!token) {
        throw new Error('No authentication token found');
      }
      
      const response = await fetch(`${API_BASE_URL}/auth/me`, {
        method: 'GET',
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
      });
      
      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.error || 'Failed to get user data');
      }
      
      return await response.json();
    } catch (error) {
      console.error('Get current user error:', error);
      throw error;
    }
  }
  
  logout() {
    localStorage.removeItem('authToken');
    localStorage.removeItem('user');
  }
  
  // Helper methods
  saveAuthData(token, user) {
    localStorage.setItem('authToken', token);
    localStorage.setItem('user', JSON.stringify(user));
  }
  
  getAuthToken() {
    return localStorage.getItem('authToken');
  }
  
  isAuthenticated() {
    return !!this.getAuthToken();
  }
  
  getUserRole() {
    const user = JSON.parse(localStorage.getItem('user') || '{}');
    return user.role || 'user';
  }
  
  isAdmin() {
    const role = this.getUserRole();
    return role === 'admin' || role === 'super_admin';
  }
  
  isSuperAdmin() {
    return this.getUserRole() === 'super_admin';
  }
}

// Create a singleton instance
const apiService = new ApiService();

// Make it available globally
window.apiService = apiService;

export default apiService;
