// import React, { useState, useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

import React, { useState, useEffect } from 'react';
import axios from 'axios';

//let path = 'http://192.168.29.73:7070/api/books' // change as per ip address
let path = 'http://localhost:7070/api/books' // change as per ip address


const App = () => {
  const [books, setBooks] = useState([]);
  const [newBook, setNewBook] = useState({ bookTitle: '', bookAuthor: '', bookRating: '' });

  // Fetch books when the component loads
  useEffect(() => {
    fetchBooks();
  }, []);

  const fetchBooks = async () => {
    try {
      const response = await axios.get(path); // Assuming /api/books is your new endpoint
      setBooks(response.data); // Directly accessing the array of books from the response
    } catch (error) {
      console.error("Error fetching books: ", error);
    }
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewBook({ ...newBook, [name]: value });
  };

  const addBook = async () => {
    try {
      await axios.post(path, newBook);
      fetchBooks(); // Refresh the list after adding
      setNewBook({ bookTitle: '', bookAuthor: '', bookRating: '' }); // Reset form
    } catch (error) {
      console.error("Error adding book: ", error);
    }
  };

  return (
    <div className="container">
      <h1>Book List</h1>

      {/* Displaying Books */}
      <table className="table">
        <thead>
          <tr>
            <th>Title</th>
            <th>Author</th>
            <th>Rating</th>
          </tr>
        </thead>
        <tbody>
          {books.map((book) => (
            <tr key={book.bookId}>
              <td>{book.bookTitle}</td>
              <td>{book.bookAuthor}</td>
              <td>{book.bookRating}</td>
            </tr>
          ))}
        </tbody>
      </table>

      {/* Form to Add Book */}
      <h2>Add a new book</h2>
      <form
        onSubmit={(e) => {
          e.preventDefault();
          addBook();
        }}
      >
        <div className="mb-3">
          <label className="form-label">Book Title</label>
          <input
            type="text"
            name="bookTitle"
            value={newBook.bookTitle}
            onChange={handleInputChange}
            className="form-control"
          />
        </div>
        <div className="mb-3">
          <label className="form-label">Author</label>
          <input
            type="text"
            name="bookAuthor"
            value={newBook.bookAuthor}
            onChange={handleInputChange}
            className="form-control"
          />
        </div>
        <div className="mb-3">
          <label className="form-label">Rating</label>
          <input
            type="number"
            name="bookRating"
            value={newBook.bookRating}
            onChange={handleInputChange}
            className="form-control"
          />
        </div>
        <button type="submit" className="btn btn-primary">Add Book</button>
      </form>
    </div>
  );
};

export default App;
