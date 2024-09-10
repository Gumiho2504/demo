use MOVIES_INFO;

-- Create the tables

CREATE TABLE genres (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE actors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE directors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE movies (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    description TEXT,
    release_date DATE,
    rating DECIMAL(3, 1),
    duration INT,
    director_id INT,
    FOREIGN KEY (director_id) REFERENCES Directors(id)
);

CREATE TABLE movie_genres (
    movie_id INT,
    genre_id INT,
    PRIMARY KEY (movie_id, genre_id),
    FOREIGN KEY (movie_id) REFERENCES Movies(id),
    FOREIGN KEY (genre_id) REFERENCES Genres(id)
);

CREATE TABLE movie_actors (
    movie_id INT,
    actor_id INT,
    role VARCHAR(100),
    PRIMARY KEY (movie_id, actor_id),
    FOREIGN KEY (movie_id) REFERENCES Movies(id),
    FOREIGN KEY (actor_id) REFERENCES Actors(id)
);

-- Insert data into Genres table
INSERT INTO genres (name) VALUES
('Action'),
('Adventure'),
('Sci-Fi'),
('Crime'),
('Drama');

-- Insert data into Actors table
INSERT INTO actors (name) VALUES
('Leonardo DiCaprio'),
('Joseph Gordon-Levitt'),
('Christian Bale'),
('Heath Ledger');

-- Insert data into Directors table
INSERT INTO directors (name) VALUES
('Christopher Nolan');

-- Insert data into Movies table
INSERT INTO movies (title, description, release_date, rating, duration, director_id) VALUES
('Inception', 'A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a CEO.', '2010-07-16', 8.8, 148, 1),
('The Dark Knight', 'When the menace known as the Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham.', '2008-07-18', 9.0, 152, 1);

-- Insert data into MovieGenres table
INSERT INTO movie_genres (movie_id, genre_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 1),
(2, 4),
(2, 5);

-- Insert data into MovieActors table
INSERT INTO movie_actors (movie_id, actor_id, role) VALUES
(1, 1, 'Cobb'),
(1, 2, 'Arthur'),
(2, 3, 'Bruce Wayne'),
(2, 4, 'Joker');
