/**
 * Subscription Management Component for GhostProtocol Admin Panel
 */
class SubscriptionManagement {
  constructor(apiService) {
    this.apiService = apiService;
    this.subscriptionTiers = [];
    this.currentTier = null;
  }

  /**
   * Initialize the subscription management component
   */
  async init() {
    this.bindEvents();
    await this.loadSubscriptionTiers();
    this.renderSubscriptionTable();
  }

  /**
   * Bind event listeners
   */
  bindEvents() {
    // Subscription form submission
    document.getElementById('subscription-form').addEventListener('submit', async (e) => {
      e.preventDefault();
      await this.saveSubscriptionTier();
    });

    // New subscription button
    document.getElementById('new-subscription-btn').addEventListener('click', () => {
      this.showSubscriptionForm();
    });

    // Close modal button
    document.querySelectorAll('.close-modal').forEach(button => {
      button.addEventListener('click', () => {
        this.hideModals();
      });
    });
  }

  /**
   * Load subscription tiers from the API
   */
  async loadSubscriptionTiers() {
    try {
      const response = await this.apiService.getSubscriptionTiers();
      this.subscriptionTiers = response.tiers || [];
      return this.subscriptionTiers;
    } catch (error) {
      this.showAlert('Error loading subscription tiers: ' + error.message, 'danger');
      return [];
    }
  }

  /**
   * Render the subscription table
   */
  renderSubscriptionTable() {
    const tableBody = document.getElementById('subscription-table-body');
    tableBody.innerHTML = '';

    if (this.subscriptionTiers.length === 0) {
      tableBody.innerHTML = '<tr><td colspan="5" class="text-center">No subscription tiers found</td></tr>';
      return;
    }

    this.subscriptionTiers.forEach(tier => {
      const row = document.createElement('tr');
      
      row.innerHTML = `
        <td>${tier.name}</td>
        <td>$${tier.price.toFixed(2)}</td>
        <td>${tier.features.join(', ')}</td>
        <td>
          <div>Storage: ${tier.limits.storage}</div>
          <div>History: ${tier.limits.message_history}</div>
          <div>Group Size: ${tier.limits.group_size}</div>
        </td>
        <td>
          <button class="btn btn-sm btn-primary edit-subscription" data-id="${tier.id}">Edit</button>
          <button class="btn btn-sm btn-danger delete-subscription" data-id="${tier.id}">Delete</button>
        </td>
      `;

      tableBody.appendChild(row);
    });

    // Add event listeners to buttons after they're added to the DOM
    document.querySelectorAll('.edit-subscription').forEach(button => {
      button.addEventListener('click', async (e) => {
        const tierId = e.target.getAttribute('data-id');
        await this.editSubscriptionTier(tierId);
      });
    });

    document.querySelectorAll('.delete-subscription').forEach(button => {
      button.addEventListener('click', async (e) => {
        const tierId = e.target.getAttribute('data-id');
        await this.deleteSubscriptionTier(tierId);
      });
    });
  }

  /**
   * Show the subscription form modal
   */
  showSubscriptionForm(tier = null) {
    this.currentTier = tier;
    const form = document.getElementById('subscription-form');
    const modal = document.getElementById('subscription-modal');

    // Reset form
    form.reset();

    // Set form values if editing a tier
    if (tier) {
      document.getElementById('tier-id').value = tier.id;
      document.getElementById('tier-name').value = tier.name;
      document.getElementById('tier-price').value = tier.price;
      document.getElementById('tier-features').value = tier.features.join(', ');
      document.getElementById('tier-storage').value = tier.limits.storage;
      document.getElementById('tier-history').value = tier.limits.message_history;
      document.getElementById('tier-group-size').value = tier.limits.group_size;
      document.getElementById('subscription-modal-title').textContent = 'Edit Subscription Tier';
    } else {
      document.getElementById('tier-id').value = '';
      document.getElementById('subscription-modal-title').textContent = 'Add New Subscription Tier';
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
   * Save a subscription tier (create or update)
   */
  async saveSubscriptionTier() {
    try {
      const tierId = document.getElementById('tier-id').value;
      const tierData = {
        name: document.getElementById('tier-name').value,
        price: parseFloat(document.getElementById('tier-price').value),
        features: document.getElementById('tier-features').value.split(',').map(f => f.trim()),
        limits: {
          storage: document.getElementById('tier-storage').value,
          message_history: document.getElementById('tier-history').value,
          group_size: parseInt(document.getElementById('tier-group-size').value)
        }
      };

      // If creating a new tier, add an ID
      if (!tierId) {
        tierData.id = document.getElementById('tier-name').value.toLowerCase().replace(/\s+/g, '_');
      }

      let response;
      if (tierId) {
        // Update existing tier
        response = await this.apiService.updateSubscriptionTier(tierId, tierData);
        this.showAlert('Subscription tier updated successfully', 'success');
      } else {
        // Create new tier
        response = await this.apiService.createSubscriptionTier(tierData);
        this.showAlert('Subscription tier created successfully', 'success');
      }

      // Hide modal and reload tiers
      this.hideModals();
      await this.loadSubscriptionTiers();
      this.renderSubscriptionTable();
    } catch (error) {
      this.showAlert('Error saving subscription tier: ' + error.message, 'danger');
    }
  }

  /**
   * Edit a subscription tier
   */
  async editSubscriptionTier(tierId) {
    try {
      const response = await this.apiService.getSubscriptionTier(tierId);
      this.showSubscriptionForm(response.tier);
    } catch (error) {
      this.showAlert('Error loading subscription tier: ' + error.message, 'danger');
    }
  }

  /**
   * Delete a subscription tier
   */
  async deleteSubscriptionTier(tierId) {
    if (!confirm('Are you sure you want to delete this subscription tier? This action cannot be undone.')) {
      return;
    }

    try {
      await this.apiService.deleteSubscriptionTier(tierId);
      this.showAlert('Subscription tier deleted successfully', 'success');
      await this.loadSubscriptionTiers();
      this.renderSubscriptionTable();
    } catch (error) {
      this.showAlert('Error deleting subscription tier: ' + error.message, 'danger');
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
