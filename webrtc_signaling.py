from fastapi import FastAPI, WebSocket, WebSocketDisconnect
from typing import Dict

# WebRTC signaling connections
class ConnectionManager:
    def __init__(self):
        self.active_connections: Dict[str, WebSocket] = {}

    async def connect(self, websocket: WebSocket, user_id: str):
        await websocket.accept()
        self.active_connections[user_id] = websocket

    def disconnect(self, user_id: str):
        if user_id in self.active_connections:
            del self.active_connections[user_id]

    async def send_personal_message(self, message: dict, user_id: str):
        if user_id in self.active_connections:
            await self.active_connections[user_id].send_json(message)

manager = ConnectionManager()

# Add this to your FastAPI app
async def setup_webrtc_signaling(app: FastAPI):
    @app.websocket("/signaling/{user_id}")
    async def websocket_endpoint(websocket: WebSocket, user_id: str):
        await manager.connect(websocket, user_id)
        try:
            while True:
                data = await websocket.receive_json()
                target_user_id = data.get("target")
                if target_user_id:
                    await manager.send_personal_message(data, target_user_id)
        except WebSocketDisconnect:
            manager.disconnect(user_id)
