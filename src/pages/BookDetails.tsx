import { useQuery, useMutation } from '@tanstack/react-query';
import { useParams } from 'react-router-dom';
import { useAuthStore } from '../store/authStore';
import api from '../api/axios';
import { Book, Note } from '../types';

const BookDetails = () => {
  const { id } = useParams<{ id: string }>();
  const { user } = useAuthStore();

  const { data: book, isLoading } = useQuery<Book>(['book', id], async () => {
    const response = await api.get(`/books/getBook/${id}`);
    return response.data;
  });

  const addReviewMutation = useMutation(
    (rating: number) => api.post('/notes/addNote', { rating, book: { isbn13: id } }),
    {
      onSuccess: () => {
        // Optionally show success message
      },
    }
  );

  const addToPlaylistMutation = useMutation(
    (playlistId: number) =>
      api.post(`/users/addBookToPlaylist/${playlistId}/${book?.isbn10}`),
    {
      onSuccess: () => {
        // Optionally show success message
      },
    }
  );

  if (isLoading) {
    return <div className="text-center">Loading...</div>;
  }

  if (!book) {
    return <div className="text-center text-red-500">Book not found</div>;
  }

  return (
    <div className="max-w-4xl mx-auto bg-white rounded-lg shadow-md overflow-hidden">
      <div className="md:flex">
        <div className="md:flex-shrink-0">
          <img
            className="h-48 w-full object-cover md:w-48"
            src={book.thumbnail}
            alt={book.title}
          />
        </div>
        <div className="p-8">
          <h1 className="text-2xl font-bold text-gray-900 mb-2">{book.title}</h1>
          <p className="text-gray-600 mb-4">by {book.author}</p>
          <p className="text-gray-500 mb-4">{book.description}</p>
          <div className="mb-4">
            <p className="text-sm text-gray-500">Category: {book.category}</p>
            <p className="text-sm text-gray-500">Pages: {book.numberOfPages}</p>
            <p className="text-sm text-gray-500">
              Published: {new Date(book.datePublished).toLocaleDateString()}
            </p>
          </div>
          
          {user && (
            <div className="space-y-4">
              <div>
                <h3 className="text-lg font-semibold mb-2">Add Review</h3>
                <div className="flex space-x-2">
                  {[1, 2, 3, 4, 5].map((rating) => (
                    <button
                      key={rating}
                      onClick={() => addReviewMutation.mutate(rating)}
                      className="p-2 bg-blue-100 rounded hover:bg-blue-200"
                    >
                      {rating} ★
                    </button>
                  ))}
                </div>
              </div>
              
              <div>
                <h3 className="text-lg font-semibold mb-2">Add to Playlist</h3>
                <select
                  onChange={(e) => addToPlaylistMutation.mutate(Number(e.target.value))}
                  className="block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring focus:ring-blue-200"
                >
                  <option value="">Select a playlist</option>
                  {user.playlists?.map((playlist) => (
                    <option key={playlist.id} value={playlist.id}>
                      {playlist.name}
                    </option>
                  ))}
                </select>
              </div>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default BookDetails;