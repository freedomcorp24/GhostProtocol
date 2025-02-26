import 'webrtc-adapter';

class WebRTCService {
  constructor() {
    this.peerConnections = new Map();
    this.localStream = null;
    this.onTrackHandlers = new Set();
    this.socket = null;
    this.userId = null;
    this.onCallHandlers = new Set();
  }

  connect(userId) {
    this.userId = userId;
    const protocol = window.location.protocol === 'https:' ? 'wss:' : 'ws:';
    const wsUrl = `${protocol}//${window.location.host}/signaling/${userId}`;
    
    this.socket = new WebSocket(wsUrl);
    
    this.socket.onopen = () => {
      console.log('Connected to signaling server');
    };
    
    this.socket.onmessage = async (event) => {
      const data = JSON.parse(event.data);
      await this.handleSignalingMessage(data);
    };
    
    this.socket.onerror = (error) => {
      console.error('WebSocket error:', error);
    };
    
    this.socket.onclose = () => {
      console.log('Disconnected from signaling server');
    };
  }
  
  async handleSignalingMessage(data) {
    const { type, sender, sdp, candidate } = data;
    
    if (type === 'offer') {
      await this.handleOffer(sender, sdp);
    } else if (type === 'answer') {
      await this.handleAnswer(sender, sdp);
    } else if (type === 'candidate') {
      await this.handleCandidate(sender, candidate);
    } else if (type === 'call-request') {
      this.onCallHandlers.forEach(handler => handler(sender));
    } else if (type === 'end-call') {
      this.endCall(sender);
    }
  }
  
  async handleOffer(sender, sdp) {
    let pc = this.peerConnections.get(sender);
    
    if (!pc) {
      pc = await this.createPeerConnection(sender);
      
      pc.onicecandidate = (event) => {
        if (event.candidate) {
          this.sendSignalingMessage({
            type: 'candidate',
            target: sender,
            candidate: event.candidate
          });
        }
      };
    }
    
    await pc.setRemoteDescription(new RTCSessionDescription(sdp));
    const answer = await pc.createAnswer();
    await pc.setLocalDescription(answer);
    
    this.sendSignalingMessage({
      type: 'answer',
      target: sender,
      sdp: pc.localDescription
    });
  }
  
  async handleAnswer(sender, sdp) {
    const pc = this.peerConnections.get(sender);
    
    if (pc) {
      await pc.setRemoteDescription(new RTCSessionDescription(sdp));
    }
  }
  
  async handleCandidate(sender, candidate) {
    const pc = this.peerConnections.get(sender);
    
    if (pc) {
      await pc.addIceCandidate(new RTCIceCandidate(candidate));
    }
  }
  
  sendSignalingMessage(message) {
    if (this.socket && this.socket.readyState === WebSocket.OPEN) {
      this.socket.send(JSON.stringify(message));
    }
  }
  
  async initializeMediaStream(video = true, audio = true) {
    try {
      this.localStream = await navigator.mediaDevices.getUserMedia({ video, audio });
      return this.localStream;
    } catch (error) {
      console.error('Error accessing media devices:', error);
      throw error;
    }
  }

  async initializeScreenShare(constraints = {
    video: {
      cursor: 'always',
      displaySurface: 'monitor',
      logicalSurface: true,
      width: { max: 1920 },
      height: { max: 1080 },
      frameRate: { max: 30 }
    }
  }) {
    try {
      this.localStream = await navigator.mediaDevices.getDisplayMedia(constraints);
      return this.localStream;
    } catch (error) {
      console.error('Error accessing screen share:', error);
      throw error;
    }
  }

  async createPeerConnection(userId) {
    const config = {
      iceServers: [
        { urls: 'stun:stun.l.google.com:19302' }
      ]
    };

    const pc = new RTCPeerConnection(config);
    
    if (this.localStream) {
      this.localStream.getTracks().forEach(track => {
        pc.addTrack(track, this.localStream);
      });
    }

    pc.ontrack = (event) => {
      this.onTrackHandlers.forEach(handler => handler(event.streams[0], userId));
    };
    
    pc.onicecandidate = (event) => {
      if (event.candidate) {
        this.sendSignalingMessage({
          type: 'candidate',
          target: userId,
          candidate: event.candidate
        });
      }
    };

    this.peerConnections.set(userId, pc);
    return pc;
  }
  
  async callUser(userId) {
    let pc = this.peerConnections.get(userId);
    
    if (!pc) {
      pc = await this.createPeerConnection(userId);
    }
    
    // Send call request
    this.sendSignalingMessage({
      type: 'call-request',
      target: userId
    });
    
    // Create and send offer
    const offer = await pc.createOffer();
    await pc.setLocalDescription(offer);
    
    this.sendSignalingMessage({
      type: 'offer',
      target: userId,
      sdp: pc.localDescription
    });
  }
  
  async acceptCall(userId) {
    // The actual acceptance happens in handleOffer
    // This method is just for API completeness
    console.log(`Accepting call from ${userId}`);
  }
  
  endCall(userId) {
    const pc = this.peerConnections.get(userId);
    
    if (pc) {
      pc.close();
      this.peerConnections.delete(userId);
    }
    
    // Notify the other user
    this.sendSignalingMessage({
      type: 'end-call',
      target: userId
    });
  }

  async stopScreenShare() {
    if (this.localStream) {
      this.localStream.getTracks().forEach(track => track.stop());
      this.localStream = null;
    }
  }

  addTrackHandler(handler) {
    this.onTrackHandlers.add(handler);
  }

  removeTrackHandler(handler) {
    this.onTrackHandlers.delete(handler);
  }
  
  addCallHandler(handler) {
    this.onCallHandlers.add(handler);
  }
  
  removeCallHandler(handler) {
    this.onCallHandlers.delete(handler);
  }

  async cleanup() {
    await this.stopScreenShare();
    this.peerConnections.forEach(pc => pc.close());
    this.peerConnections.clear();
    this.onTrackHandlers.clear();
    this.onCallHandlers.clear();
    
    if (this.socket) {
      this.socket.close();
      this.socket = null;
    }
  }
  
  toggleAudio(enabled) {
    if (this.localStream) {
      this.localStream.getAudioTracks().forEach(track => {
        track.enabled = enabled;
      });
    }
  }
  
  toggleVideo(enabled) {
    if (this.localStream) {
      this.localStream.getVideoTracks().forEach(track => {
        track.enabled = enabled;
      });
    }
  }
}

export default new WebRTCService();
