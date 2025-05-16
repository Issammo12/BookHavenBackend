import { useQuery } from '@tanstack/react-query';
import { Link } from 'react-router-dom';
import api from '../api/axios';
import { Club } from '../types';

const Clubs = () => {
  const { data: clubs, isLoading } = useQuery<Club[]>(['clubs'], async () => {
    const response = await api.get('/clubs/allClubs');
    return response.data;
  });

  if (isLoading) {
    return <div className="text-center">Loading...</div>;
  }

  return (
    <div>
      <h1 className="text-3xl font-bold mb-6">Book Clubs</h1>
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {clubs?.map((club) => (
          <Link
            key={club.id}
            to={`/clubs/${club.id}`}
            className="block bg-white rounded-lg shadow-md hover:shadow-lg transition-shadow p-6"
          >
            <h2 className="text-xl font-semibold mb-2">{club.name}</h2>
            <p className="text-gray-600 mb-2">{club.category}</p>
            <div className="text-sm text-gray-500">
              <p>{club.users.length} members</p>
              <p>{club.books.length} books discussed</p>
            </div>
          </Link>
        ))}
      </div>
    </div>
  );
};

export default Clubs;