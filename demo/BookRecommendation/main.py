import numpy as np
import pymysql

# Function to connect to the MySQL database
def connect_to_database():
    try:
        connection = pymysql.connect(
            host='localhost',       # Change to your host
            database='book_reviews', # Change to your database name
            user='springstudent',     # Change to your username
            password='springstudent',   # Change to your password
            cursorclass=pymysql.cursors.DictCursor  # Use dictionary cursor
        )
        return connection
    except Exception as e:
        print(f"Error connecting to the database: {e}")
        return None

# Function to calculate similarity and update recommendations in the book table
def update_recommendations(num):
    # Connect to the database
    connection = connect_to_database()
    if connection is None:
        print("Database connection failed")
        return  # Exit if connection fails

    try:
        with connection.cursor() as cursor:
            #print("Fetching books...")
            cursor.execute("""
                SELECT model.book_id,
                       model.humour, model.comedy, model.education,
                       model.thriller, model.romance, model.philosophical,
                       model.motivational, model.finance, model.drama,
                       model.horror, model.biopic, model.fiction,
                       model.mythology, model.sci_fi, model.adventure,
                       model.comics, model.religious_text
                FROM model
                JOIN book ON model.book_id = book.book_id
            """)
            books = cursor.fetchall()

            if not books:
                print("No books found")
                return

            print(f"Fetched {len(books)} books")

            # Process each book to find similar ones
            for target_book in books:
                target_features = np.array([
                    target_book['humour'], target_book['comedy'], target_book['education'],
                    target_book['thriller'], target_book['romance'], target_book['philosophical'],
                    target_book['motivational'], target_book['finance'], target_book['drama'],
                    target_book['horror'], target_book['biopic'], target_book['fiction'],
                    target_book['mythology'], target_book['sci_fi'], target_book['adventure'],
                    target_book['comics'], target_book['religious_text']
                ])

                distances = []

                for book in books:
                    if book['book_id'] != target_book['book_id']:
                        features = np.array([
                            book['humour'], book['comedy'], book['education'],
                            book['thriller'], book['romance'], book['philosophical'],
                            book['motivational'], book['finance'], book['drama'],
                            book['horror'], book['biopic'], book['fiction'],
                            book['mythology'], book['sci_fi'], book['adventure'],
                            book['comics'], book['religious_text']
                        ])

                        distance = np.linalg.norm(target_features - features)
                        distances.append((book['book_id'], distance))

                distances.sort(key=lambda x: x[1])

                top_recommendations = [book_id for book_id, _ in distances[:num]]
                top_recommendations_padded = top_recommendations + [None] * (5 - len(top_recommendations))

                cursor.execute("""
                    UPDATE book
                    SET similar_1 = %s, similar_2 = %s, similar_3 = %s, similar_4 = %s, similar_5 = %s
                    WHERE book_id = %s
                """, (*top_recommendations_padded, target_book['book_id']))

            connection.commit()
            print("Recommendations updated successfully.")

    except Exception as e:
        print(f"Error during processing: {e}")

    finally:
        connection.close()

if __name__ == "__main__":
    num_recommendations = 5
    update_recommendations(num_recommendations)
