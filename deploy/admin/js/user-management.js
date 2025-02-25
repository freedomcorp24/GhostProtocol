// User Management for GhostProtocol Admin Panel

// API endpoint
const API_ENDPOINT = 'http://54.215.16.4/api';

// Get authentication token from localStorage
const getAuthToken = () => localStorage.getItem('authToken');

// Check if user is authenticated and has admin privileges
const checkAdminAuth = async () => {
  const token = getAuthToken();
  
  if (!token) {
    window.location.href = '/admin/login.html';
    return false;
  }
  
  try {
    const response = await fetch(`${API_ENDPOINT}/auth/me`, {
      method: 'GET',
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      }
    });
    
    if (!response.ok) {
      throw new Error('Authentication failed');
    }
    
    const userData = await response.json();
    
    if (userData.role !== 'admin' && userData.role !== 'super_admin') {
      window.location.href = '/admin/unauthorized.html';
      return false;
    }
    
    return userData;
  } catch (error) {
    console.error('Auth check failed:', error);
    window.location.href = '/admin/login.html';
    return false;
  }
};

// Fetch all users
const fetchUsers = async () => {
  try {
    const token = getAuthToken();
    
    const response = await fetch(`${API_ENDPOINT}/users`, {
      method: 'GET',
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      }
    });
    
    if (!response.ok) {
      throw new Error('Failed to fetch users');
    }
    
    return await response.json();
  } catch (error) {
    console.error('Error fetching users:', error);
    return [];
  }
};

// Ban a user
const banUser = async (userId) => {
  try {
    const token = getAuthToken();
    
    const response = await fetch(`${API_ENDPOINT}/users/${userId}/ban`, {
      method: 'GET',
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      }
    });
    
    if (!response.ok) {
      throw new Error('Failed to ban user');
    }
    
    return await response.json();
  } catch (error) {
    console.error('Error banning user:', error);
    throw error;
  }
};

// Unban a user
const unbanUser = async (userId) => {
  try {
    const token = getAuthToken();
    
    const response = await fetch(`${API_ENDPOINT}/users/${userId}`, {
      method: 'PUT',
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ status: 'active' })
    });
    
    if (!response.ok) {
      throw new Error('Failed to unban user');
    }
    
    return await response.json();
  } catch (error) {
    console.error('Error unbanning user:', error);
    throw error;
  }
};

// Upgrade user to premium
const upgradeToPremium = async (userId, tier) => {
  try {
    const token = getAuthToken();
    
    const response = await fetch(`${API_ENDPOINT}/users/${userId}/subscription`, {
      method: 'POST',
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ 
        subscription_tier: tier,
        premium: true,
        admin_override: true
      })
    });
    
    if (!response.ok) {
      throw new Error('Failed to upgrade user');
    }
    
    return await response.json();
  } catch (error) {
    console.error('Error upgrading user:', error);
    throw error;
  }
};

// Downgrade user from premium
const downgradeFromPremium = async (userId) => {
  try {
    const token = getAuthToken();
    
    const response = await fetch(`${API_ENDPOINT}/users/${userId}/subscription`, {
      method: 'POST',
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ 
        subscription_tier: 'free',
        premium: false,
        admin_override: true
      })
    });
    
    if (!response.ok) {
      throw new Error('Failed to downgrade user');
    }
    
    return await response.json();
  } catch (error) {
    console.error('Error downgrading user:', error);
    throw error;
  }
};

// Delete a user
const deleteUser = async (userId) => {
  try {
    const token = getAuthToken();
    
    const response = await fetch(`${API_ENDPOINT}/users/${userId}`, {
      method: 'DELETE',
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      }
    });
    
    if (!response.ok) {
      throw new Error('Failed to delete user');
    }
    
    return await response.json();
  } catch (error) {
    console.error('Error deleting user:', error);
    throw error;
  }
};

// Initialize the user management interface
const initUserManagement = async () => {
  const userData = await checkAdminAuth();
  
  if (!userData) {
    return;
  }
  
  // Set admin name in the UI
  const adminNameElement = document.getElementById('admin-name');
  if (adminNameElement) {
    adminNameElement.textContent = userData.username;
  }
  
  // Set admin role in the UI
  const adminRoleElement = document.getElementById('admin-role');
  if (adminRoleElement) {
    adminRoleElement.textContent = userData.role === 'super_admin' ? 'Master Admin' : 'Admin';
  }
  
  // Load users
  const users = await fetchUsers();
  displayUsers(users);
  
  // Set up event listeners
  setupEventListeners();
};

// Display users in the UI
const displayUsers = (users) => {
  const userTableBody = document.getElementById('user-table-body');
  
  if (!userTableBody) {
    console.error('User table body element not found');
    return;
  }
  
  userTableBody.innerHTML = '';
  
  users.forEach(user => {
    const row = document.createElement('tr');
    
    row.innerHTML = `
      <td>${user.id}</td>
      <td>${user.username}</td>
      <td>${user.role}</td>
      <td>${user.status}</td>
      <td>${user.premium ? 'Premium' : 'Free'}</td>
      <td>${user.subscription_tier}</td>
      <td>
        ${user.status === 'banned' 
          ? '<button class="unban-btn" data-user-id="' + user.id + '">Unban</button>' 
          : '<button class="ban-btn" data-user-id="' + user.id + '">Ban</button>'}
        ${user.premium 
          ? '<button class="downgrade-btn" data-user-id="' + user.id + '">Downgrade</button>' 
          : '<button class="upgrade-btn" data-user-id="' + user.id + '">Upgrade</button>'}
        <button class="delete-btn" data-user-id="${user.id}">Delete</button>
      </td>
    `;
    
    userTableBody.appendChild(row);
  });
};

// Set up event listeners for user actions
const setupEventListeners = () => {
  const userTableBody = document.getElementById('user-table-body');
  
  if (!userTableBody) {
    return;
  }
  
  // Ban user
  userTableBody.addEventListener('click', async (event) => {
    if (event.target.classList.contains('ban-btn')) {
      const userId = event.target.getAttribute('data-user-id');
      
      if (confirm('Are you sure you want to ban this user?')) {
        try {
          await banUser(userId);
          alert('User banned successfully');
          const users = await fetchUsers();
          displayUsers(users);
        } catch (error) {
          alert('Failed to ban user: ' + error.message);
        }
      }
    }
  });
  
  // Unban user
  userTableBody.addEventListener('click', async (event) => {
    if (event.target.classList.contains('unban-btn')) {
      const userId = event.target.getAttribute('data-user-id');
      
      if (confirm('Are you sure you want to unban this user?')) {
        try {
          await unbanUser(userId);
          alert('User unbanned successfully');
          const users = await fetchUsers();
          displayUsers(users);
        } catch (error) {
          alert('Failed to unban user: ' + error.message);
        }
      }
    }
  });
  
  // Upgrade user
  userTableBody.addEventListener('click', async (event) => {
    if (event.target.classList.contains('upgrade-btn')) {
      const userId = event.target.getAttribute('data-user-id');
      const tier = prompt('Enter subscription tier (basic, premium, enterprise):', 'premium');
      
      if (tier) {
        try {
          await upgradeToPremium(userId, tier);
          alert('User upgraded successfully');
          const users = await fetchUsers();
          displayUsers(users);
        } catch (error) {
          alert('Failed to upgrade user: ' + error.message);
        }
      }
    }
  });
  
  // Downgrade user
  userTableBody.addEventListener('click', async (event) => {
    if (event.target.classList.contains('downgrade-btn')) {
      const userId = event.target.getAttribute('data-user-id');
      
      if (confirm('Are you sure you want to downgrade this user to free tier?')) {
        try {
          await downgradeFromPremium(userId);
          alert('User downgraded successfully');
          const users = await fetchUsers();
          displayUsers(users);
        } catch (error) {
          alert('Failed to downgrade user: ' + error.message);
        }
      }
    }
  });
  
  // Delete user
  userTableBody.addEventListener('click', async (event) => {
    if (event.target.classList.contains('delete-btn')) {
      const userId = event.target.getAttribute('data-user-id');
      
      if (confirm('Are you sure you want to delete this user? This action cannot be undone.')) {
        try {
          await deleteUser(userId);
          alert('User deleted successfully');
          const users = await fetchUsers();
          displayUsers(users);
        } catch (error) {
          alert('Failed to delete user: ' + error.message);
        }
      }
    }
  });
  
  // Refresh button
  const refreshBtn = document.getElementById('refresh-users-btn');
  if (refreshBtn) {
    refreshBtn.addEventListener('click', async () => {
      const users = await fetchUsers();
      displayUsers(users);
    });
  }
};

// Initialize when the DOM is loaded
document.addEventListener('DOMContentLoaded', initUserManagement);

// Export functions for testing
window.adminFunctions = {
  fetchUsers,
  banUser,
  unbanUser,
  upgradeToPremium,
  downgradeFromPremium,
  deleteUser
};
