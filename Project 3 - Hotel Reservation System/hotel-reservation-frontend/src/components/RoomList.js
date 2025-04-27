// src/components/RoomList.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';

function RoomList({ onSelectRoom }) {
  const [rooms, setRooms] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    // Fetch available rooms when component mounts
    axios.get('http://localhost:8080/api/rooms/available')
      .then(response => {
        setRooms(response.data);
        setLoading(false);
      })
      .catch(err => {
        setError('Failed to fetch rooms: ' + err.message);
        setLoading(false);
      });
  }, []);

  // Add the missing handleBooking function
  const handleBooking = (roomId) => {
    // If you're using the parent component's onSelectRoom function
    if (onSelectRoom) {
      onSelectRoom(roomId);
    } else {
      // If you want to handle booking directly in this component
      console.log(`Booking room with ID: ${roomId}`);
      // You could navigate to a booking form or open a modal here
    }
  };

  if (loading) return <div>Loading rooms...</div>;
  if (error) return <div className="error">{error}</div>;

  return (
    <div className="room-list">
      <h2>Available Rooms</h2>
      {rooms.length === 0 ? (
        <p>No rooms available</p>
      ) : (
        <ul>
          {rooms.map(room => (
            <li key={room.id} className="room-item">
              <div className="room-details">
                <h3>Room #{room.number}</h3>
                <p>Category: {room.category}</p>
                <p>Price: ₹{room.price}/night</p>
                <button onClick={() => handleBooking(room.id)}>Book Now</button>
              </div>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default RoomList;