import pymysql

# MySQL connection details
db_config = {
    'user': 'springstudent',
    'password': 'springstudent',
    'host': 'localhost',
    'database': 'book_reviews'
}

# Connect to the MySQL database using PyMySQL
connection = pymysql.connect(
    host=db_config['host'],
    user=db_config['user'],
    password=db_config['password'],
    database=db_config['database']
)

cursor = connection.cursor()

# Fetch data from table 'a'
fetch_query = "SELECT book_id, humour, comedy, education, thriller, romance FROM model"
cursor.execute(fetch_query)
rows = cursor.fetchall()

# Prepare to insert modified values into table 'b'
update_query = "UPDATE book SET similar_1 = %s, similar_2 = %s, similar_3 = %s, similar_4 = %s, similar_5 = %s WHERE book_id = %s"

# Loop through the rows, multiply values by 10, and insert into table 'b'
for row in rows:
    id = row[0]
    # Multiply each column (except id) by 10
    modified_row = (row[1], row[2], row[3], row[4], row[5], id)
    
    # Insert into table 'b' where id is the foreign key
    cursor.execute(update_query, modified_row)

# Commit the changes to the database
connection.commit()

# Close the cursor and connection
cursor.close()
connection.close()

print("Data successfully copied and updated in table book.")
