// src/App.js
import React, { useState } from 'react';
import RoomList from './components/RoomList';
import BookingForm from './components/BookingForm';
import UserReservations from './components/UserReservations';
import './App.css';

function App() {
  const [selectedRoomId, setSelectedRoomId] = useState(null);
  const [userId, setUserId] = useState('');
  const [bookingSuccess, setBookingSuccess] = useState(false);
  
  const handleRoomSelect = (roomId) => {
    setSelectedRoomId(roomId);
    setBookingSuccess(false);
  };
  
  const handleBookingSuccess = () => {
    setBookingSuccess(true);
    setSelectedRoomId(null);
  };

  return (
    <div className="App">
      <header className="App-header">
        <h1>Hotel Reservation System</h1>
      </header>
      
      <div className="user-section">
        <label>
          Enter User ID: 
          <input 
            type="number" 
            value={userId} 
            onChange={(e) => setUserId(e.target.value)} 
          />
        </label>
      </div>
      
      <div className="main-content">
        <div className="rooms-section">
          <RoomList onSelectRoom={handleRoomSelect} />
        </div>
        
        <div className="booking-section">
          {selectedRoomId ? (
            <BookingForm 
              roomId={selectedRoomId} 
              userId={userId}
              onSuccess={handleBookingSuccess} 
            />
          ) : bookingSuccess ? (
            <div className="success-message">
              Booking successful! Check your reservations.
            </div>
          ) : (
            <p>Select a room to book</p>
          )}
        </div>
      </div>
      
      {userId && (
        <div className="reservations-section">
          <UserReservations userId={userId} />
        </div>
      )}
    </div>
  );
}

export default App;