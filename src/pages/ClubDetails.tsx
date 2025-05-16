import { useQuery, useMutation } from '@tanstack/react-query';
import { useParams, Link } from 'react-router-dom';
import { useAuthStore } from '../store/authStore';
import api from '../api/axios';
import { Club } from '../types';

const ClubDetails = () => {
  const { id } = useParams<{ id: string }>();
  const { user } = useAuthStore();

  const { data: club, isLoading, refetch } = useQuery<Club>(
    ['club', id],
    async () => {
      const response = await api.get(`/clubs/getClub/${id}`);
      return response.data;
    }
  );

  const joinClubMutation = useMutation(
    () => api.post(`/users/joinClub/${id}`),
    {
      onSuccess: () => refetch(),
    }
  );

  if (isLoading) {
    return <div className="text-center">Loading...</div>;
  }

  if (!club) {
    return <div className="text-center text-red-500">Club not found</div>;
  }

  const isUserMember = user && club.users.some((u) => u.idUser === user.idUser);

  return (
    <div>
      <div className="bg-white rounded-lg shadow-md p-6 mb-8">
        <h1 className="text-3xl font-bold mb-4">{club.name}</h1>
        <p className="text-gray-600 mb-4">{club.category}</p>
        <p className="text-gray-500 mb-6">{club.users.length} members</p>
        
        {user && !isUserMember && (
          <button
            onClick={() => joinClubMutation.mutate()}
            className="px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600"
          >
            Join Club
          </button>
        )}
      </div>

      <h2 className="text-2xl font-bold mb-4">Books Discussed</h2>
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {club.books.map((book) => (
          <Link
            key={book.isbn13}
            to={`/books/${book.isbn13}`}
            className="block bg-white rounded-lg shadow-md hover:shadow-lg transition-shadow"
          >
            <img
              src={book.thumbnail}
              alt={book.title}
              className="w-full h-48 object-cover rounded-t-lg"
            />
            <div className="p-4">
              <h3 className="text-xl font-semibold mb-2">{book.title}</h3>
              <p className="text-gray-600">by {book.author}</p>
            </div>
          </Link>
        ))}
      </div>
    </div>
  );
};

export default ClubDetails;