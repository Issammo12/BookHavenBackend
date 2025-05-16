import { useQuery, useMutation } from '@tanstack/react-query';
import { Link } from 'react-router-dom';
import { useForm } from 'react-hook-form';
import api from '../api/axios';
import { Playlist } from '../types';

interface PlaylistForm {
  name: string;
}

const Playlists = () => {
  const { register, handleSubmit, reset } = useForm<PlaylistForm>();

  const { data: playlists, isLoading, refetch } = useQuery<Playlist[]>(
    ['playlists'],
    async () => {
      const response = await api.get('/users/myPlaylists');
      return response.data;
    }
  );

  const createPlaylistMutation = useMutation(
    (data: PlaylistForm) => api.post('/users/createPlaylist', data),
    {
      onSuccess: () => {
        refetch();
        reset();
      },
    }
  );

  if (isLoading) {
    return <div className="text-center">Loading...</div>;
  }

  return (
    <div>
      <h1 className="text-3xl font-bold mb-6">My Playlists</h1>
      
      <form
        onSubmit={handleSubmit((data) => createPlaylistMutation.mutate(data))}
        className="mb-8 bg-white p-6 rounded-lg shadow-md"
      >
        <h2 className="text-xl font-semibold mb-4">Create New Playlist</h2>
        <div className="flex gap-4">
          <input
            type="text"
            {...register('name', { required: true })}
            placeholder="Playlist name"
            className="flex-1 rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring focus:ring-blue-200"
          />
          <button
            type="submit"
            className="px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600"
          >
            Create
          </button>
        </div>
      </form>

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {playlists?.map((playlist) => (
          <Link
            key={playlist.id}
            to={`/playlists/${playlist.id}`}
            className="block bg-white rounded-lg shadow-md hover:shadow-lg transition-shadow p-6"
          >
            <h2 className="text-xl font-semibold mb-2">{playlist.name}</h2>
            <p className="text-gray-600">
              {playlist.books.length} {playlist.books.length === 1 ? 'book' : 'books'}
            </p>
            <p className="text-sm text-gray-500">
              Created: {new Date(playlist.creationDate).toLocaleDateString()}
            </p>
          </Link>
        ))}
      </div>
    </div>
  );
};

export default Playlists;