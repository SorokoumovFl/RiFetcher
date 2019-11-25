CREATE TABLE IF NOT EXISTS good_boys (
    id uuid PRIMARY KEY,
    name VARCHAR(128) NOT NULL,
    breed VARCHAR(128) NOT NULL,
    tail_length INT NOT NULL
);