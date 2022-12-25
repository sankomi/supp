CREATE TABLE IF NOT EXISTS users (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	username TEXT,
	password TEXT,
	support INTEGER
);

CREATE TABLE IF NOT EXISTS tickets (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	title TEXT,
	created DATETIME,
	userId INTEGER
);

CREATE TABLE IF NOT EXISTS contents (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	created DATETIME,
	ticketId INTEGER,
	userId INTEGER,
	content TEXT
);