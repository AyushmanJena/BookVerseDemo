DROP TABLE IF EXISTS book_reviews.book;

CREATE TABLE book_reviews.book (
    book_id INT AUTO_INCREMENT PRIMARY KEY,  -- Book ID, Auto Increment and Primary Key
    book_rating INT,                         -- Rating for the book
    book_author VARCHAR(255),                -- Author of the book
    book_title VARCHAR(255),                 -- Title of the book
    book_cover_photo BLOB,                   -- Stores the cover photo as a binary object
    similar_1 INT,                           -- Similar book 1 (foreign key or reference)
    similar_2 INT,                           -- Similar book 2 (foreign key or reference)
    similar_3 INT,                           -- Similar book 3 (foreign key or reference)
    similar_4 INT,                           -- Similar book 4 (foreign key or reference)
    similar_5 INT                            -- Similar book 5 (foreign key or reference)
);
