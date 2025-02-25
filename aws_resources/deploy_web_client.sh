#!/bin/bash
# Script to deploy the web client to the EC2 instance

set -e

# Set variables
AWS_REGION="us-west-1"
EC2_INSTANCE_ID="i-02c36ae2dc15653c6"  # Actual instance ID
EC2_PUBLIC_IP="54.215.16.4"  # Actual public IP
SSH_KEY_PATH="~/.ssh/ghostprotocol-dev-key.pem"

# Create a more advanced web client
echo "Creating web client..."
mkdir -p /tmp/ghostprotocol-web
cat > /tmp/ghostprotocol-web/index.html << 'EOF'
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GhostProtocol - Secure Messaging</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
            color: #333;
        }
        header {
            background-color: #2c3e50;
            color: white;
            padding: 1rem;
            text-align: center;
        }
        nav {
            background-color: #34495e;
            padding: 0.5rem;
        }
        nav ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
        }
        nav ul li {
            margin: 0 1rem;
        }
        nav ul li a {
            color: white;
            text-decoration: none;
        }
        main {
            max-width: 800px;
            margin: 2rem auto;
            padding: 1rem;
            background-color: white;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }
        .feature {
            margin-bottom: 1.5rem;
            padding-bottom: 1.5rem;
            border-bottom: 1px solid #eee;
        }
        .feature:last-child {
            border-bottom: none;
        }
        .cta {
            text-align: center;
            margin: 2rem 0;
        }
        .cta button {
            background-color: #2c3e50;
            color: white;
            border: none;
            padding: 0.5rem 1rem;
            border-radius: 3px;
            cursor: pointer;
            font-size: 1rem;
        }
        footer {
            text-align: center;
            padding: 1rem;
            background-color: #2c3e50;
            color: white;
        }
    </style>
</head>
<body>
    <header>
        <h1>GhostProtocol</h1>
        <p>Secure, Private, Encrypted Messaging</p>
    </header>
    <nav>
        <ul>
            <li><a href="#">Home</a></li>
            <li><a href="#">Features</a></li>
            <li><a href="#">Pricing</a></li>
            <li><a href="#">Download</a></li>
            <li><a href="#">Support</a></li>
        </ul>
    </nav>
    <main>
        <div class="cta">
            <h2>Take Control of Your Privacy</h2>
            <p>GhostProtocol provides end-to-end encrypted messaging for individuals and organizations.</p>
            <button>Get Started</button>
        </div>
        <div class="feature">
            <h2>End-to-End Encryption</h2>
            <p>All messages are encrypted end-to-end, ensuring that only you and your intended recipients can read them.</p>
        </div>
        <div class="feature">
            <h2>Self-Destructing Messages</h2>
            <p>Set messages to automatically delete after they've been read or after a specific time period.</p>
        </div>
        <div class="feature">
            <h2>Secure Group Chats</h2>
            <p>Create encrypted group conversations with friends, family, or colleagues.</p>
        </div>
        <div class="feature">
            <h2>Media Sharing</h2>
            <p>Share photos, videos, and documents securely with end-to-end encryption.</p>
        </div>
        <div class="feature">
            <h2>Screen Sharing</h2>
            <p>Share your screen securely during video calls.</p>
        </div>
        <div class="feature">
            <h2>Vault Storage</h2>
            <p>Store sensitive information in an encrypted vault accessible only to you.</p>
        </div>
        <div class="feature">
            <h2>Premium Features</h2>
            <p>Upgrade to premium for additional storage, longer message history, and advanced features.</p>
        </div>
        <div class="cta">
            <h2>Ready to Get Started?</h2>
            <p>Download GhostProtocol today and take control of your privacy.</p>
            <button>Download Now</button>
        </div>
    </main>
    <footer>
        <p>&copy; 2025 GhostProtocol. All rights reserved.</p>
    </footer>
</body>
</html>
EOF

# Create a script.js file
cat > /tmp/ghostprotocol-web/script.js << 'EOF'
document.addEventListener('DOMContentLoaded', function() {
    // Add event listeners to buttons
    const buttons = document.querySelectorAll('button');
    buttons.forEach(button => {
        button.addEventListener('click', function() {
            alert('This feature is coming soon!');
        });
    });

    // Add a simple animation to the header
    const header = document.querySelector('header');
    header.style.transition = 'background-color 0.5s ease';
    header.addEventListener('mouseover', function() {
        this.style.backgroundColor = '#34495e';
    });
    header.addEventListener('mouseout', function() {
        this.style.backgroundColor = '#2c3e50';
    });
});
EOF

# Create a styles.css file
cat > /tmp/ghostprotocol-web/styles.css << 'EOF'
/* Additional styles for the web client */
.feature h2 {
    color: #2c3e50;
}

.feature:hover {
    background-color: #f9f9f9;
}

.cta button:hover {
    background-color: #34495e;
}

@media (max-width: 600px) {
    nav ul {
        flex-direction: column;
        align-items: center;
    }
    nav ul li {
        margin: 0.5rem 0;
    }
}
EOF

# Deploy to EC2 instance
echo "Deploying web client to EC2 instance..."
ssh -i $SSH_KEY_PATH -o StrictHostKeyChecking=no ec2-user@$EC2_PUBLIC_IP "sudo mkdir -p /usr/share/nginx/html/web"
scp -i $SSH_KEY_PATH -o StrictHostKeyChecking=no /tmp/ghostprotocol-web/* ec2-user@$EC2_PUBLIC_IP:/home/ec2-user/ghostprotocol/web/
ssh -i $SSH_KEY_PATH -o StrictHostKeyChecking=no ec2-user@$EC2_PUBLIC_IP "sudo cp -r /home/ec2-user/ghostprotocol/web/* /usr/share/nginx/html/web/ && sudo chown -R nginx:nginx /usr/share/nginx/html/web && sudo chmod -R 755 /usr/share/nginx/html/web"

echo "Web client deployment completed successfully!"
echo ""
echo "GhostProtocol Web Client Access Information"
echo "=========================================="
echo "Web Client: http://$EC2_PUBLIC_IP/"
