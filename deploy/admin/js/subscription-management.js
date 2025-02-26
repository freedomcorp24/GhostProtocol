/**
 * Subscription Management Module for GhostProtocol Admin Panel
 */
class SubscriptionManagement {
    constructor(apiService) {
        this.apiService = apiService;
        this.subscriptionTiers = [];
        this.currentTier = null;
        
        // DOM elements
        this.subscriptionTiersContainer = document.getElementById('subscription-tiers-container');
        this.tierModal = document.getElementById('tier-modal');
        this.tierForm = document.getElementById('tier-form');
        this.tierModalTitle = document.getElementById('tier-modal-title');
        this.addTierBtn = document.getElementById('add-tier-btn');
        this.cancelTierBtn = document.getElementById('cancel-tier-btn');
        this.saveTierBtn = document.getElementById('save-tier-btn');
        
        // Initialize
        this.init();
    }
    
    /**
     * Initialize the subscription management module
     */
    init() {
        // Load subscription tiers
        this.loadSubscriptionTiers();
        
        // Event listeners
        this.addTierBtn.addEventListener('click', () => this.showAddTierModal());
        this.cancelTierBtn.addEventListener('click', () => this.hideTierModal());
        this.tierForm.addEventListener('submit', (e) => this.handleTierFormSubmit(e));
        
        // Close modal when clicking on the close button or outside the modal
        document.querySelector('#tier-modal .close').addEventListener('click', () => this.hideTierModal());
        window.addEventListener('click', (e) => {
            if (e.target === this.tierModal) {
                this.hideTierModal();
            }
        });
    }
    
    /**
     * Load subscription tiers from the API
     */
    async loadSubscriptionTiers() {
        try {
            showLoading('Loading subscription tiers...');
            const response = await this.apiService.getSubscriptionTiers();
            this.subscriptionTiers = response.tiers || [];
            this.renderSubscriptionTiers();
            hideLoading();
        } catch (error) {
            hideLoading();
            showAlert(`Error loading subscription tiers: ${error.message}`, 'error');
        }
    }
    
    /**
     * Render the subscription tiers
     */
    renderSubscriptionTiers() {
        // Clear the container
        this.subscriptionTiersContainer.innerHTML = '';
        
        // If no tiers, show a message
        if (this.subscriptionTiers.length === 0) {
            const message = document.createElement('div');
            message.className = 'no-tiers-message';
            message.textContent = 'No subscription tiers found';
            this.subscriptionTiersContainer.appendChild(message);
            return;
        }
        
        // Add tiers to the container
        this.subscriptionTiers.forEach(tier => {
            const tierCard = document.createElement('div');
            tierCard.className = 'tier-card';
            
            // Create features list
            const featuresList = tier.features.map(feature => `<li>${feature}</li>`).join('');
            
            tierCard.innerHTML = `
                <div class="tier-header">
                    <h3>${tier.name}</h3>
                    <div class="tier-price">$${tier.price.toFixed(2)}</div>
                </div>
                <div class="tier-features">
                    <ul>
                        ${featuresList}
                    </ul>
                </div>
                <div class="tier-limits">
                    <div class="limit-item">
                        <span class="limit-label">Storage</span>
                        <span class="limit-value">${tier.limits.storage}</span>
                    </div>
                    <div class="limit-item">
                        <span class="limit-label">Message History</span>
                        <span class="limit-value">${tier.limits.message_history}</span>
                    </div>
                    <div class="limit-item">
                        <span class="limit-label">Group Size</span>
                        <span class="limit-value">${tier.limits.group_size} people</span>
                    </div>
                </div>
                <div class="tier-actions">
                    <button class="btn btn-outline btn-edit-tier" data-tier-id="${tier.id}">Edit</button>
                    <button class="btn btn-danger btn-delete-tier" data-tier-id="${tier.id}" ${tier.id === 'free' ? 'disabled' : ''}>Delete</button>
                </div>
            `;
            
            // Add event listeners to the buttons
            tierCard.querySelector('.btn-edit-tier').addEventListener('click', () => this.showEditTierModal(tier.id));
            tierCard.querySelector('.btn-delete-tier').addEventListener('click', () => this.deleteTier(tier.id));
            
            this.subscriptionTiersContainer.appendChild(tierCard);
        });
    }
    
    /**
     * Show the add tier modal
     */
    showAddTierModal() {
        this.currentTier = null;
        this.tierModalTitle.textContent = 'Add New Subscription Tier';
        this.tierForm.reset();
        this.tierModal.style.display = 'block';
    }
    
    /**
     * Show the edit tier modal
     * @param {string} tierId - Tier ID
     */
    async showEditTierModal(tierId) {
        try {
            showLoading('Loading tier details...');
            const response = await this.apiService.getSubscriptionTier(tierId);
            this.currentTier = response.tier;
            
            this.tierModalTitle.textContent = 'Edit Subscription Tier';
            
            // Fill the form with tier data
            document.getElementById('tier-name').value = this.currentTier.name;
            document.getElementById('tier-price').value = this.currentTier.price;
            document.getElementById('tier-features').value = this.currentTier.features.join('\n');
            document.getElementById('tier-storage').value = this.currentTier.limits.storage;
            document.getElementById('tier-history').value = this.currentTier.limits.message_history;
            document.getElementById('tier-group-size').value = this.currentTier.limits.group_size;
            
            this.tierModal.style.display = 'block';
            hideLoading();
        } catch (error) {
            hideLoading();
            showAlert(`Error loading tier details: ${error.message}`, 'error');
        }
    }
    
    /**
     * Hide the tier modal
     */
    hideTierModal() {
        this.tierModal.style.display = 'none';
    }
    
    /**
     * Handle tier form submission
     * @param {Event} e - Form submit event
     */
    async handleTierFormSubmit(e) {
        e.preventDefault();
        
        // Get form data
        const name = document.getElementById('tier-name').value;
        const price = parseFloat(document.getElementById('tier-price').value);
        const featuresText = document.getElementById('tier-features').value;
        const storage = document.getElementById('tier-storage').value;
        const history = document.getElementById('tier-history').value;
        const groupSize = parseInt(document.getElementById('tier-group-size').value);
        
        // Parse features
        const features = featuresText.split('\n').filter(feature => feature.trim() !== '');
        
        // Create tier data
        const tierData = {
            name,
            price,
            features,
            limits: {
                storage,
                message_history: history,
                group_size: groupSize
            }
        };
        
        // If editing, add the ID
        if (this.currentTier) {
            tierData.id = this.currentTier.id;
        }
        
        try {
            showLoading(this.currentTier ? 'Updating tier...' : 'Creating tier...');
            
            let response;
            if (this.currentTier) {
                // Update existing tier
                response = await this.apiService.updateSubscriptionTier(this.currentTier.id, tierData);
                showAlert('Subscription tier updated successfully', 'success');
            } else {
                // Create new tier
                response = await this.apiService.createSubscriptionTier(tierData);
                showAlert('Subscription tier created successfully', 'success');
            }
            
            // Reload tiers and hide modal
            await this.loadSubscriptionTiers();
            this.hideTierModal();
            hideLoading();
        } catch (error) {
            hideLoading();
            showAlert(`Error ${this.currentTier ? 'updating' : 'creating'} subscription tier: ${error.message}`, 'error');
        }
    }
    
    /**
     * Delete a subscription tier
     * @param {string} tierId - Tier ID
     */
    async deleteTier(tierId) {
        if (!confirm('Are you sure you want to delete this subscription tier? This action cannot be undone.')) {
            return;
        }
        
        try {
            showLoading('Deleting subscription tier...');
            await this.apiService.deleteSubscriptionTier(tierId);
            showAlert('Subscription tier deleted successfully', 'success');
            await this.loadSubscriptionTiers();
            hideLoading();
        } catch (error) {
            hideLoading();
            showAlert(`Error deleting subscription tier: ${error.message}`, 'error');
        }
    }
}
