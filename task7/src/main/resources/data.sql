insert into AUTHORS (ID, NAME) values (1, 'Alexandre Dumas');
insert into AUTHORS (ID, NAME) values (2, 'Arthur Conan Doyle');

insert into GENRES (ID, NAME) values (1, 'Detective');
insert into GENRES (ID, NAME) values (2, 'History');

insert into BOOKS (ID, NAME, AUTHOR_ID, GENRE_ID) values (1, 'The Three Musketeers', 1, 2);
insert into BOOKS (ID, NAME, AUTHOR_ID, GENRE_ID) values (2, 'The Sign of the Four', 2, 1);

insert into COMMENTS (id, book_id, text) values ( 1, 1, 'my test comment' );
