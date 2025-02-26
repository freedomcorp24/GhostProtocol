/**
 * Dashboard Module for GhostProtocol Admin Panel
 */
class Dashboard {
    constructor(apiService) {
        this.apiService = apiService;
        this.dashboardStats = null;
        
        // DOM elements
        this.totalUsersElement = document.getElementById('total-users');
        this.activeUsersElement = document.getElementById('active-users');
        this.premiumSubsElement = document.getElementById('premium-subs');
        this.monthlyRevenueElement = document.getElementById('monthly-revenue');
        this.userGrowthChartElement = document.getElementById('user-growth-chart');
        this.subscriptionChartElement = document.getElementById('subscription-chart');
        this.recentActivityTableElement = document.getElementById('recent-activity-table');
        this.systemStatusTableElement = document.getElementById('system-status-table');
        
        // Initialize
        this.init();
    }
    
    /**
     * Initialize the dashboard module
     */
    init() {
        // Load dashboard statistics
        this.loadDashboardStats();
        
        // Set up refresh interval (every 5 minutes)
        setInterval(() => this.loadDashboardStats(), 5 * 60 * 1000);
    }
    
    /**
     * Load dashboard statistics from the API
     */
    async loadDashboardStats() {
        try {
            showLoading('Loading dashboard statistics...');
            const response = await this.apiService.getDashboardStats();
            this.dashboardStats = response;
            this.updateDashboard();
            hideLoading();
        } catch (error) {
            hideLoading();
            showAlert(`Error loading dashboard statistics: ${error.message}`, 'error');
        }
    }
    
    /**
     * Update the dashboard with the loaded statistics
     */
    updateDashboard() {
        if (!this.dashboardStats) {
            return;
        }
        
        // Update stat cards
        this.totalUsersElement.textContent = this.dashboardStats.users.total_users.toLocaleString();
        this.activeUsersElement.textContent = this.dashboardStats.users.active_users.toLocaleString();
        this.premiumSubsElement.textContent = this.dashboardStats.subscriptions.subscription_distribution.premium.toLocaleString();
        this.monthlyRevenueElement.textContent = `$${this.dashboardStats.subscriptions.revenue.monthly.toLocaleString(undefined, { minimumFractionDigits: 2, maximumFractionDigits: 2 })}`;
        
        // Update user growth chart
        this.updateUserGrowthChart();
        
        // Update subscription distribution chart
        this.updateSubscriptionChart();
        
        // Update recent activity table
        this.updateRecentActivityTable();
        
        // Update system status table
        this.updateSystemStatusTable();
    }
    
    /**
     * Update the user growth chart
     */
    updateUserGrowthChart() {
        // In a real implementation, this would use a charting library like Chart.js
        // For this mock implementation, we'll just show a placeholder
        this.userGrowthChartElement.innerHTML = `
            <div class="chart-placeholder">
                <p>User Growth Chart</p>
                <p>Total Users: ${this.dashboardStats.users.total_users.toLocaleString()}</p>
                <p>Daily Growth: ${this.dashboardStats.users.user_growth.daily.toFixed(1)}%</p>
                <p>Weekly Growth: ${this.dashboardStats.users.user_growth.weekly.toFixed(1)}%</p>
                <p>Monthly Growth: ${this.dashboardStats.users.user_growth.monthly.toFixed(1)}%</p>
            </div>
        `;
    }
    
    /**
     * Update the subscription distribution chart
     */
    updateSubscriptionChart() {
        // In a real implementation, this would use a charting library like Chart.js
        // For this mock implementation, we'll just show a placeholder
        const subscriptionData = this.dashboardStats.subscriptions.subscription_distribution;
        const total = subscriptionData.free + subscriptionData.premium + subscriptionData.business;
        
        const freePercentage = ((subscriptionData.free / total) * 100).toFixed(1);
        const premiumPercentage = ((subscriptionData.premium / total) * 100).toFixed(1);
        const businessPercentage = ((subscriptionData.business / total) * 100).toFixed(1);
        
        this.subscriptionChartElement.innerHTML = `
            <div class="chart-placeholder">
                <p>Subscription Distribution Chart</p>
                <p>Free: ${subscriptionData.free.toLocaleString()} (${freePercentage}%)</p>
                <p>Premium: ${subscriptionData.premium.toLocaleString()} (${premiumPercentage}%)</p>
                <p>Business: ${subscriptionData.business.toLocaleString()} (${businessPercentage}%)</p>
            </div>
        `;
    }
    
    /**
     * Update the recent activity table
     */
    updateRecentActivityTable() {
        // Generate mock recent activity data
        const activities = [
            { user: 'johndoe', action: 'Logged in', time: '5 minutes ago' },
            { user: 'janedoe', action: 'Updated profile', time: '10 minutes ago' },
            { user: 'bobsmith', action: 'Sent a message', time: '15 minutes ago' },
            { user: 'alicejones', action: 'Created a group', time: '20 minutes ago' },
            { user: 'mikebrown', action: 'Upgraded to Premium', time: '30 minutes ago' }
        ];
        
        const tbody = this.recentActivityTableElement.querySelector('tbody');
        tbody.innerHTML = '';
        
        activities.forEach(activity => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${activity.user}</td>
                <td>${activity.action}</td>
                <td>${activity.time}</td>
            `;
            tbody.appendChild(tr);
        });
    }
    
    /**
     * Update the system status table
     */
    updateSystemStatusTable() {
        // Generate mock system status data
        const systemComponents = [
            { component: 'API Server', status: this.dashboardStats.system.status, uptime: '99.9%' },
            { component: 'Database', status: 'operational', uptime: '99.95%' },
            { component: 'Storage', status: 'operational', uptime: '99.99%' },
            { component: 'Message Queue', status: 'operational', uptime: '99.8%' },
            { component: 'Web Client', status: 'operational', uptime: '99.7%' }
        ];
        
        const tbody = this.systemStatusTableElement.querySelector('tbody');
        tbody.innerHTML = '';
        
        systemComponents.forEach(component => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${component.component}</td>
                <td><span class="status-badge ${component.status}">${component.status}</span></td>
                <td>${component.uptime}</td>
            `;
            tbody.appendChild(tr);
        });
    }
}
