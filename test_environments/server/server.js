const http = require('http');
const fs = require('fs');
const path = require('path');

// Create HTTP server for web environment
const webServer = http.createServer((req, res) => {
    let filePath = path.join(__dirname, '../web/public', req.url === '/' ? 'index.html' : req.url);
    
    // Check if file exists
    fs.access(filePath, fs.constants.F_OK, (err) => {
        if (err) {
            res.writeHead(404);
            res.end('File not found');
            return;
        }
        
        // Read file and serve
        fs.readFile(filePath, (err, data) => {
            if (err) {
                res.writeHead(500);
                res.end('Error loading file');
                return;
            }
            
            // Set content type
            const ext = path.extname(filePath);
            let contentType = 'text/html';
            
            switch (ext) {
                case '.css':
                    contentType = 'text/css';
                    break;
                case '.js':
                    contentType = 'text/javascript';
                    break;
                case '.json':
                    contentType = 'application/json';
                    break;
                case '.png':
                    contentType = 'image/png';
                    break;
                case '.jpg':
                    contentType = 'image/jpeg';
                    break;
            }
            
            res.writeHead(200, { 'Content-Type': contentType });
            res.end(data);
        });
    });
});

// Create HTTP server for iOS environment
const iosServer = http.createServer((req, res) => {
    let filePath = path.join(__dirname, '../ios/public', req.url === '/' ? 'index.html' : req.url);
    
    // Check if file exists
    fs.access(filePath, fs.constants.F_OK, (err) => {
        if (err) {
            res.writeHead(404);
            res.end('File not found');
            return;
        }
        
        // Read file and serve
        fs.readFile(filePath, (err, data) => {
            if (err) {
                res.writeHead(500);
                res.end('Error loading file');
                return;
            }
            
            // Set content type
            const ext = path.extname(filePath);
            let contentType = 'text/html';
            
            switch (ext) {
                case '.css':
                    contentType = 'text/css';
                    break;
                case '.js':
                    contentType = 'text/javascript';
                    break;
                case '.json':
                    contentType = 'application/json';
                    break;
                case '.png':
                    contentType = 'image/png';
                    break;
                case '.jpg':
                    contentType = 'image/jpeg';
                    break;
            }
            
            res.writeHead(200, { 'Content-Type': contentType });
            res.end(data);
        });
    });
});

// Create HTTP server for Android environment
const androidServer = http.createServer((req, res) => {
    let filePath = path.join(__dirname, '../android/public', req.url === '/' ? 'index.html' : req.url);
    
    // Check if file exists
    fs.access(filePath, fs.constants.F_OK, (err) => {
        if (err) {
            res.writeHead(404);
            res.end('File not found');
            return;
        }
        
        // Read file and serve
        fs.readFile(filePath, (err, data) => {
            if (err) {
                res.writeHead(500);
                res.end('Error loading file');
                return;
            }
            
            // Set content type
            const ext = path.extname(filePath);
            let contentType = 'text/html';
            
            switch (ext) {
                case '.css':
                    contentType = 'text/css';
                    break;
                case '.js':
                    contentType = 'text/javascript';
                    break;
                case '.json':
                    contentType = 'application/json';
                    break;
                case '.png':
                    contentType = 'image/png';
                    break;
                case '.jpg':
                    contentType = 'image/jpeg';
                    break;
            }
            
            res.writeHead(200, { 'Content-Type': contentType });
            res.end(data);
        });
    });
});

// Start servers
webServer.listen(3000, () => {
    console.log('Web server running on port 3000');
});

iosServer.listen(3001, () => {
    console.log('iOS server running on port 3001');
});

androidServer.listen(3002, () => {
    console.log('Android server running on port 3002');
});

console.log('All test environment servers are running');
