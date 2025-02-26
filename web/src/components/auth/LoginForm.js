import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import apiService from '../../services/api';
import './LoginForm.css';

const LoginForm = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const [isAdminLogin, setIsAdminLogin] = useState(false);
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    // Validate form
    if (!username || !password) {
      setError('Username and password are required');
      return;
    }
    
    setLoading(true);
    setError('');
    
    try {
      // Use the API service for login
      const response = await apiService.login(username, password);
      
      // Check if user is admin or super_admin
      if (response.user.role === 'admin' || response.user.role === 'super_admin') {
        navigate('/admin/dashboard');
      } else {
        navigate('/dashboard');
      }
    } catch (err) {
      setError(err.message || 'Failed to log in. Please try again.');
    } finally {
      setLoading(false);
    }
  };

  const toggleAdminLogin = () => {
    setIsAdminLogin(!isAdminLogin);
    setError('');
  };

  return (
    <div className="login-container">
      <h2>{isAdminLogin ? 'Admin Login' : 'Login to GhostProtocol'}</h2>
      
      {error && <div className="error-message">{error}</div>}
      
      <form onSubmit={handleSubmit} className="login-form">
        <div className="form-group">
          <label htmlFor="username">Username</label>
          <input
            type="text"
            id="username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            placeholder="Enter your username"
            required
          />
        </div>
        
        <div className="form-group">
          <label htmlFor="password">Password</label>
          <input
            type="password"
            id="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            placeholder="Enter your password"
            required
          />
        </div>
        
        <button type="submit" className="login-button" disabled={loading}>
          {loading ? 'Logging in...' : isAdminLogin ? 'Admin Login' : 'Login'}
        </button>
      </form>
      
      {!isAdminLogin && (
        <div className="signup-link">
          Don't have an account? <a href="/signup">Sign up</a>
        </div>
      )}
      
      <div className="admin-login-option">
        <p>{isAdminLogin ? 'Not an admin?' : 'Admin access?'}</p>
        <button className="admin-login-link" onClick={toggleAdminLogin}>
          {isAdminLogin ? 'Switch to User Login' : 'Switch to Admin Login'}
        </button>
      </div>
      
      {isAdminLogin && (
        <div className="admin-features">
          <h3>Admin Features</h3>
          <ul>
            <li>User Management</li>
            <li>Subscription Control</li>
            <li>Content Moderation</li>
            <li>System Analytics</li>
            <li>Master Admin Controls</li>
          </ul>
        </div>
      )}
    </div>
  );
};

export default LoginForm;
