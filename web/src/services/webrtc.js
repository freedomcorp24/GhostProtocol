import 'webrtc-adapter';

class WebRTCService {
  constructor() {
    this.peerConnections = new Map();
    this.localStream = null;
    this.onTrackHandlers = new Set();
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

    this.peerConnections.set(userId, pc);
    return pc;
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

  async cleanup() {
    await this.stopScreenShare();
    this.peerConnections.forEach(pc => pc.close());
    this.peerConnections.clear();
    this.onTrackHandlers.clear();
  }
}

export default new WebRTCService();
