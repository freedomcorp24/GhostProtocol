/**
 * User Management Module for GhostProtocol Admin Panel
 */
class UserManagement {
    constructor(apiService) {
        this.apiService = apiService;
        this.users = [];
        this.currentUser = null;
        
        // DOM elements
        this.usersTable = document.getElementById('users-table');
        this.userModal = document.getElementById('user-modal');
        this.userForm = document.getElementById('user-form');
        this.userModalTitle = document.getElementById('user-modal-title');
        this.addUserBtn = document.getElementById('add-user-btn');
        this.cancelUserBtn = document.getElementById('cancel-user-btn');
        this.saveUserBtn = document.getElementById('save-user-btn');
        this.userSearch = document.getElementById('user-search');
        this.userFilter = document.getElementById('user-filter');
        
        // Initialize
        this.init();
    }
    
    /**
     * Initialize the user management module
     */
    init() {
        // Load users
        this.loadUsers();
        
        // Event listeners
        this.addUserBtn.addEventListener('click', () => this.showAddUserModal());
        this.cancelUserBtn.addEventListener('click', () => this.hideUserModal());
        this.userForm.addEventListener('submit', (e) => this.handleUserFormSubmit(e));
        this.userSearch.addEventListener('input', () => this.filterUsers());
        this.userFilter.addEventListener('change', () => this.filterUsers());
        
        // Close modal when clicking on the close button or outside the modal
        document.querySelector('#user-modal .close').addEventListener('click', () => this.hideUserModal());
        window.addEventListener('click', (e) => {
            if (e.target === this.userModal) {
                this.hideUserModal();
            }
        });
    }
    
    /**
     * Load users from the API
     */
    async loadUsers() {
        try {
            showLoading('Loading users...');
            const response = await this.apiService.getUsers();
            this.users = response.users || [];
            this.renderUsersTable();
            hideLoading();
        } catch (error) {
            hideLoading();
            showAlert(`Error loading users: ${error.message}`, 'error');
        }
    }
    
    /**
     * Render the users table
     */
    renderUsersTable() {
        // Clear the table body
        const tbody = this.usersTable.querySelector('tbody');
        tbody.innerHTML = '';
        
        // If no users, show a message
        if (this.users.length === 0) {
            const tr = document.createElement('tr');
            tr.innerHTML = '<td colspan="7">No users found</td>';
            tbody.appendChild(tr);
            return;
        }
        
        // Add users to the table
        this.users.forEach(user => {
            const tr = document.createElement('tr');
            
            // Format the created date
            const createdDate = new Date(user.created_at);
            const formattedDate = createdDate.toLocaleDateString();
            
            tr.innerHTML = `
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td><span class="status-badge ${user.status}">${user.status}</span></td>
                <td>${user.subscription || 'free'}</td>
                <td>${formattedDate}</td>
                <td class="actions">
                    <button class="btn btn-outline btn-sm btn-edit" data-id="${user.id}">Edit</button>
                    <button class="btn btn-outline btn-sm btn-ban" data-id="${user.id}" ${user.status === 'banned' ? 'disabled' : ''}>Ban</button>
                    <button class="btn btn-danger btn-sm btn-delete" data-id="${user.id}">Delete</button>
                </td>
            `;
            
            // Add event listeners to the buttons
            tr.querySelector('.btn-edit').addEventListener('click', () => this.showEditUserModal(user.id));
            tr.querySelector('.btn-ban').addEventListener('click', () => this.banUser(user.id));
            tr.querySelector('.btn-delete').addEventListener('click', () => this.deleteUser(user.id));
            
            tbody.appendChild(tr);
        });
    }
    
    /**
     * Filter users based on search and filter
     */
    filterUsers() {
        const searchTerm = this.userSearch.value.toLowerCase();
        const filterValue = this.userFilter.value;
        
        const filteredUsers = this.users.filter(user => {
            // Filter by search term
            const matchesSearch = 
                user.username.toLowerCase().includes(searchTerm) ||
                user.email.toLowerCase().includes(searchTerm) ||
                user.id.toLowerCase().includes(searchTerm);
            
            // Filter by status/subscription
            let matchesFilter = true;
            if (filterValue !== 'all') {
                if (filterValue === 'active' || filterValue === 'banned') {
                    matchesFilter = user.status === filterValue;
                } else if (filterValue === 'premium' || filterValue === 'free') {
                    matchesFilter = user.subscription === filterValue;
                }
            }
            
            return matchesSearch && matchesFilter;
        });
        
        // Update the table with filtered users
        const tbody = this.usersTable.querySelector('tbody');
        tbody.innerHTML = '';
        
        if (filteredUsers.length === 0) {
            const tr = document.createElement('tr');
            tr.innerHTML = '<td colspan="7">No users found</td>';
            tbody.appendChild(tr);
            return;
        }
        
        filteredUsers.forEach(user => {
            const tr = document.createElement('tr');
            
            // Format the created date
            const createdDate = new Date(user.created_at);
            const formattedDate = createdDate.toLocaleDateString();
            
            tr.innerHTML = `
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td><span class="status-badge ${user.status}">${user.status}</span></td>
                <td>${user.subscription || 'free'}</td>
                <td>${formattedDate}</td>
                <td class="actions">
                    <button class="btn btn-outline btn-sm btn-edit" data-id="${user.id}">Edit</button>
                    <button class="btn btn-outline btn-sm btn-ban" data-id="${user.id}" ${user.status === 'banned' ? 'disabled' : ''}>Ban</button>
                    <button class="btn btn-danger btn-sm btn-delete" data-id="${user.id}">Delete</button>
                </td>
            `;
            
            // Add event listeners to the buttons
            tr.querySelector('.btn-edit').addEventListener('click', () => this.showEditUserModal(user.id));
            tr.querySelector('.btn-ban').addEventListener('click', () => this.banUser(user.id));
            tr.querySelector('.btn-delete').addEventListener('click', () => this.deleteUser(user.id));
            
            tbody.appendChild(tr);
        });
    }
    
    /**
     * Show the add user modal
     */
    showAddUserModal() {
        this.currentUser = null;
        this.userModalTitle.textContent = 'Add New User';
        this.userForm.reset();
        this.userModal.style.display = 'block';
    }
    
    /**
     * Show the edit user modal
     * @param {string} userId - User ID
     */
    async showEditUserModal(userId) {
        try {
            showLoading('Loading user details...');
            const response = await this.apiService.getUser(userId);
            this.currentUser = response.user;
            
            this.userModalTitle.textContent = 'Edit User';
            
            // Fill the form with user data
            document.getElementById('username').value = this.currentUser.username;
            document.getElementById('email').value = this.currentUser.email;
            document.getElementById('status').value = this.currentUser.status;
            document.getElementById('role').value = this.currentUser.role || 'user';
            document.getElementById('subscription').value = this.currentUser.subscription || 'free';
            
            this.userModal.style.display = 'block';
            hideLoading();
        } catch (error) {
            hideLoading();
            showAlert(`Error loading user details: ${error.message}`, 'error');
        }
    }
    
    /**
     * Hide the user modal
     */
    hideUserModal() {
        this.userModal.style.display = 'none';
    }
    
    /**
     * Handle user form submission
     * @param {Event} e - Form submit event
     */
    async handleUserFormSubmit(e) {
        e.preventDefault();
        
        // Get form data
        const userData = {
            username: document.getElementById('username').value,
            email: document.getElementById('email').value,
            status: document.getElementById('status').value,
            role: document.getElementById('role').value,
            subscription: document.getElementById('subscription').value
        };
        
        try {
            showLoading(this.currentUser ? 'Updating user...' : 'Creating user...');
            
            let response;
            if (this.currentUser) {
                // Update existing user
                response = await this.apiService.updateUser(this.currentUser.id, userData);
                showAlert('User updated successfully', 'success');
            } else {
                // Create new user
                response = await this.apiService.createUser(userData);
                showAlert('User created successfully', 'success');
            }
            
            // Reload users and hide modal
            await this.loadUsers();
            this.hideUserModal();
            hideLoading();
        } catch (error) {
            hideLoading();
            showAlert(`Error ${this.currentUser ? 'updating' : 'creating'} user: ${error.message}`, 'error');
        }
    }
    
    /**
     * Ban a user
     * @param {string} userId - User ID
     */
    async banUser(userId) {
        if (!confirm('Are you sure you want to ban this user?')) {
            return;
        }
        
        try {
            showLoading('Banning user...');
            await this.apiService.banUser(userId);
            showAlert('User banned successfully', 'success');
            await this.loadUsers();
            hideLoading();
        } catch (error) {
            hideLoading();
            showAlert(`Error banning user: ${error.message}`, 'error');
        }
    }
    
    /**
     * Delete a user
     * @param {string} userId - User ID
     */
    async deleteUser(userId) {
        if (!confirm('Are you sure you want to delete this user? This action cannot be undone.')) {
            return;
        }
        
        try {
            showLoading('Deleting user...');
            await this.apiService.deleteUser(userId);
            showAlert('User deleted successfully', 'success');
            await this.loadUsers();
            hideLoading();
        } catch (error) {
            hideLoading();
            showAlert(`Error deleting user: ${error.message}`, 'error');
        }
    }
}
