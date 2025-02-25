/**
 * User Management Component for GhostProtocol Admin Panel
 */
class UserManagement {
  constructor(apiService) {
    this.apiService = apiService;
    this.users = [];
    this.currentUser = null;
  }

  /**
   * Initialize the user management component
   */
  async init() {
    this.bindEvents();
    await this.loadUsers();
    this.renderUserTable();
  }

  /**
   * Bind event listeners
   */
  bindEvents() {
    // User form submission
    document.getElementById('user-form').addEventListener('submit', async (e) => {
      e.preventDefault();
      await this.saveUser();
    });

    // New user button
    document.getElementById('new-user-btn').addEventListener('click', () => {
      this.showUserForm();
    });

    // Close modal button
    document.querySelectorAll('.close-modal').forEach(button => {
      button.addEventListener('click', () => {
        this.hideModals();
      });
    });
  }

  /**
   * Load users from the API
   */
  async loadUsers() {
    try {
      const response = await this.apiService.getUsers();
      this.users = response.users || [];
      return this.users;
    } catch (error) {
      this.showAlert('Error loading users: ' + error.message, 'danger');
      return [];
    }
  }

  /**
   * Render the user table
   */
  renderUserTable() {
    const tableBody = document.getElementById('user-table-body');
    tableBody.innerHTML = '';

    if (this.users.length === 0) {
      tableBody.innerHTML = '<tr><td colspan="6" class="text-center">No users found</td></tr>';
      return;
    }

    this.users.forEach(user => {
      const row = document.createElement('tr');
      
      // Status class
      let statusClass = '';
      if (user.status === 'active') {
        statusClass = 'text-success';
      } else if (user.status === 'banned') {
        statusClass = 'text-danger';
      }

      // Subscription class
      let subscriptionClass = '';
      if (user.subscription === 'premium') {
        subscriptionClass = 'text-primary';
      } else if (user.subscription === 'business') {
        subscriptionClass = 'text-success';
      }

      row.innerHTML = `
        <td>${user.username}</td>
        <td>${user.email}</td>
        <td class="${statusClass}">${user.status}</td>
        <td class="${subscriptionClass}">${user.subscription}</td>
        <td>${user.created_at}</td>
        <td>
          <button class="btn btn-sm btn-primary edit-user" data-id="${user.id}">Edit</button>
          ${user.status === 'active' ? 
            `<button class="btn btn-sm btn-warning ban-user" data-id="${user.id}">Ban</button>` : 
            `<button class="btn btn-sm btn-success unban-user" data-id="${user.id}">Unban</button>`
          }
          <button class="btn btn-sm btn-danger delete-user" data-id="${user.id}">Delete</button>
        </td>
      `;

      // Add event listeners to buttons
      tableBody.appendChild(row);
    });

    // Add event listeners to buttons after they're added to the DOM
    document.querySelectorAll('.edit-user').forEach(button => {
      button.addEventListener('click', async (e) => {
        const userId = e.target.getAttribute('data-id');
        await this.editUser(userId);
      });
    });

    document.querySelectorAll('.ban-user').forEach(button => {
      button.addEventListener('click', async (e) => {
        const userId = e.target.getAttribute('data-id');
        await this.banUser(userId);
      });
    });

    document.querySelectorAll('.unban-user').forEach(button => {
      button.addEventListener('click', async (e) => {
        const userId = e.target.getAttribute('data-id');
        await this.unbanUser(userId);
      });
    });

    document.querySelectorAll('.delete-user').forEach(button => {
      button.addEventListener('click', async (e) => {
        const userId = e.target.getAttribute('data-id');
        await this.deleteUser(userId);
      });
    });
  }

  /**
   * Show the user form modal
   */
  showUserForm(user = null) {
    this.currentUser = user;
    const form = document.getElementById('user-form');
    const modal = document.getElementById('user-modal');

    // Reset form
    form.reset();

    // Set form values if editing a user
    if (user) {
      document.getElementById('user-id').value = user.id;
      document.getElementById('username').value = user.username;
      document.getElementById('email').value = user.email;
      document.getElementById('role').value = user.role;
      document.getElementById('status').value = user.status;
      document.getElementById('subscription').value = user.subscription;
      document.getElementById('user-modal-title').textContent = 'Edit User';
    } else {
      document.getElementById('user-id').value = '';
      document.getElementById('user-modal-title').textContent = 'Add New User';
    }

    // Show modal
    modal.style.display = 'block';
  }

  /**
   * Hide all modals
   */
  hideModals() {
    document.querySelectorAll('.modal').forEach(modal => {
      modal.style.display = 'none';
    });
  }

  /**
   * Save a user (create or update)
   */
  async saveUser() {
    try {
      const userId = document.getElementById('user-id').value;
      const userData = {
        username: document.getElementById('username').value,
        email: document.getElementById('email').value,
        role: document.getElementById('role').value,
        status: document.getElementById('status').value,
        subscription: document.getElementById('subscription').value
      };

      let response;
      if (userId) {
        // Update existing user
        response = await this.apiService.updateUser(userId, userData);
        this.showAlert('User updated successfully', 'success');
      } else {
        // Create new user
        response = await this.apiService.createUser(userData);
        this.showAlert('User created successfully', 'success');
      }

      // Hide modal and reload users
      this.hideModals();
      await this.loadUsers();
      this.renderUserTable();
    } catch (error) {
      this.showAlert('Error saving user: ' + error.message, 'danger');
    }
  }

  /**
   * Edit a user
   */
  async editUser(userId) {
    try {
      const response = await this.apiService.getUser(userId);
      this.showUserForm(response.user);
    } catch (error) {
      this.showAlert('Error loading user: ' + error.message, 'danger');
    }
  }

  /**
   * Ban a user
   */
  async banUser(userId) {
    try {
      await this.apiService.banUser(userId);
      this.showAlert('User banned successfully', 'success');
      await this.loadUsers();
      this.renderUserTable();
    } catch (error) {
      this.showAlert('Error banning user: ' + error.message, 'danger');
    }
  }

  /**
   * Unban a user
   */
  async unbanUser(userId) {
    try {
      await this.apiService.updateUser(userId, { status: 'active' });
      this.showAlert('User unbanned successfully', 'success');
      await this.loadUsers();
      this.renderUserTable();
    } catch (error) {
      this.showAlert('Error unbanning user: ' + error.message, 'danger');
    }
  }

  /**
   * Delete a user
   */
  async deleteUser(userId) {
    if (!confirm('Are you sure you want to delete this user? This action cannot be undone.')) {
      return;
    }

    try {
      await this.apiService.deleteUser(userId);
      this.showAlert('User deleted successfully', 'success');
      await this.loadUsers();
      this.renderUserTable();
    } catch (error) {
      this.showAlert('Error deleting user: ' + error.message, 'danger');
    }
  }

  /**
   * Show an alert message
   */
  showAlert(message, type) {
    const alertContainer = document.getElementById('alert-container');
    const alert = document.createElement('div');
    alert.className = `alert alert-${type}`;
    alert.textContent = message;
    
    // Clear previous alerts
    alertContainer.innerHTML = '';
    alertContainer.appendChild(alert);
    
    // Auto-hide after 5 seconds
    setTimeout(() => {
      alert.remove();
    }, 5000);
  }
}
