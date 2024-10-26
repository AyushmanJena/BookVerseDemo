DROP TABLE IF EXISTS book_reviews.model;

CREATE TABLE book_reviews.model(
    book_id INT,                            -- Book ID (No Auto Increment here)
    humour INT,                           
    comedy INT,                          
    education INT,                           
    thriller INT,                           
    romance INT,                            
    philosophical INT,
    motivational INT,
    finance INT,
    drama INT,
    horror INT,
    biopic INT,
    fiction INT,
    mythology INT,
    sci_fi INT,
    adventure INT,
    comics INT,
    religious_text INT,
    
    -- Primary Key on book_id (but without AUTO_INCREMENT)
    PRIMARY KEY (book_id),

    -- Foreign key constraint to reference the book_id from the books table
    CONSTRAINT fk_book
        FOREIGN KEY (book_id) 
        REFERENCES book(book_id)
        ON DELETE CASCADE
);
