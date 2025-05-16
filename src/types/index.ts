export interface User {
  idUser: number;
  name: string;
  email: string;
  playlists: Playlist[];
  clubs: Club[];
}

export interface Book {
  isbn13: string;
  isbn10: string;
  title: string;
  author: string;
  description: string;
  category: string;
  thumbnail: string;
  datePublished: Date;
  numberOfPages: number;
  clubs: Club[];
}

export interface Club {
  id: number;
  name: string;
  category: string;
  users: User[];
  books: Book[];
}

export interface Note {
  noteId: number;
  rating: number;
  book: Book;
  user: User;
}

export interface Playlist {
  id: number;
  name: string;
  creationDate: Date;
  user: User;
  books: Book[];
}