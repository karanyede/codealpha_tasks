// src/components/BookingForm.js
import React, { useState } from 'react';
import axios from 'axios';

function BookingForm({ roomId, onSuccess }) {
  const [userId, setUserId] = useState('');
  const [checkIn, setCheckIn] = useState('');
  const [checkOut, setCheckOut] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError(null);

    try {
      // Calculate total amount (simplified example)
      const amount = 1000;

      // Make POST request to book room
      const response = await axios.post(
        `http://localhost:8080/api/reservations/book?userId=${userId}&roomId=${roomId}&checkIn=${checkIn}&checkOut=${checkOut}&amount=${amount}`
      );

      setLoading(false);
      onSuccess(response.data);
    } catch (err) {
      setLoading(false);
      setError('Booking failed: ' + (err.response?.data || err.message));
    }
  };

  return (
    <div className="booking-form">
      <h3>Book Room</h3>
      {error && <div className="error">{error}</div>}

      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>User ID:</label>
          <input
            type="number"
            value={userId}
            onChange={(e) => setUserId(e.target.value)}
            required
          />
        </div>

        <div className="form-group">
          <label>Check-in Date:</label>
          <input
            type="date"
            value={checkIn}
            onChange={(e) => setCheckIn(e.target.value)}
            required
          />
        </div>

        <div className="form-group">
          <label>Check-out Date:</label>
          <input
            type="date"
            value={checkOut}
            onChange={(e) => setCheckOut(e.target.value)}
            required
          />
        </div>

        <button type="submit" disabled={loading}>
          {loading ? 'Processing...' : 'Confirm Booking'}
        </button>
      </form>
    </div>
  );
}

export default BookingForm;