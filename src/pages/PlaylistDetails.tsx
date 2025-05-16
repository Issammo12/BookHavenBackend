import { useQuery, useMutation } from '@tanstack/react-query';
import { useParams, Link } from 'react-router-dom';
import api from '../api/axios';
import { Playlist } from '../types';

const PlaylistDetails = () => {
  const { id } = useParams<{ id: string }>();

  const { data: playlist, isLoading, refetch } = useQuery<Playlist>(
    ['playlist', id],
    async () => {
      const response = await api.get(`/playlists/getPlaylist/${id}`);
      return response.data;
    }
  );

  const deleteBookMutation = useMutation(
    (bookId: string) => api.delete(`/playlists/${id}/books/${bookId}`),
    {
      onSuccess: () => refetch(),
    }
  );

  if (isLoading) {
    return <div className="text-center">Loading...</div>;
  }

  if (!playlist) {
    return <div className="text-center text-red-500">Playlist not found</div>;
  }

  return (
    <div>
      <h1 className="text-3xl font-bold mb-6">{playlist.name}</h1>
      <p className="text-gray-600 mb-8">
        Created on {new Date(playlist.creationDate).toLocaleDateString()}
      </p>

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {playlist.books.map((book) => (
          <div
            key={book.isbn13}
            className="bg-white rounded-lg shadow-md overflow-hidden"
          >
            <img
              src={book.thumbnail}
              alt={book.title}
              className="w-full h-48 object-cover"
            />
            <div className="p-4">
              <h2 className="text-xl font-semibold mb-2">{book.title}</h2>
              <p className="text-gray-600 mb-2">by {book.author}</p>
              <div className="flex justify-between items-center">
                <Link
                  to={`/books/${book.isbn13}`}
                  className="text-blue-500 hover:text-blue-600"
                >
                  View Details
                </Link>
                <button
                  onClick={() => deleteBookMutation.mutate(book.isbn13)}
                  className="text-red-500 hover:text-red-600"
                >
                  Remove
                </button>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default PlaylistDetails;