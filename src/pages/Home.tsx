import { useQuery } from '@tanstack/react-query';
import { Link } from 'react-router-dom';
import api from '../api/axios';
import { Book } from '../types';

const Home = () => {
  const { data: books, isLoading, error } = useQuery<Book[]>(['books'], 
    async () => {
      const response = await api.get('/books/allBooks');
      return response.data;
    }
  );

  if (isLoading) {
    return <div className="text-center">Loading...</div>;
  }

  if (error) {
    return <div className="text-center text-red-500">Error loading books</div>;
  }

  return (
    <div>
      <h1 className="text-3xl font-bold mb-6">All Books</h1>
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {books?.map((book) => (
          <Link
            key={book.isbn13}
            to={`/books/${book.isbn13}`}
            className="block bg-white rounded-lg shadow-md hover:shadow-lg transition-shadow"
          >
            <div className="aspect-w-3 aspect-h-4">
              <img
                src={book.thumbnail}
                alt={book.title}
                className="object-cover w-full h-48 rounded-t-lg"
              />
            </div>
            <div className="p-4">
              <h2 className="text-xl font-semibold mb-2">{book.title}</h2>
              <p className="text-gray-600 mb-2">by {book.author}</p>
              <p className="text-sm text-gray-500">{book.category}</p>
            </div>
          </Link>
        ))}
      </div>
    </div>
  );
};

export default Home;