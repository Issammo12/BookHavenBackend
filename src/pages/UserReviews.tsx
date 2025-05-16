import { useQuery } from '@tanstack/react-query';
import { Link } from 'react-router-dom';
import api from '../api/axios';
import { Note } from '../types';

const UserReviews = () => {
  const { data: reviews, isLoading } = useQuery<Note[]>(
    ['userReviews'],
    async () => {
      const response = await api.get('/notes/listNotes');
      return response.data;
    }
  );

  if (isLoading) {
    return <div className="text-center">Loading...</div>;
  }

  return (
    <div>
      <h1 className="text-3xl font-bold mb-6">My Book Reviews</h1>
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {reviews?.map((review) => (
          <div
            key={review.noteId}
            className="bg-white rounded-lg shadow-md overflow-hidden"
          >
            <img
              src={review.book.thumbnail}
              alt={review.book.title}
              className="w-full h-48 object-cover"
            />
            <div className="p-4">
              <Link
                to={`/books/${review.book.isbn13}`}
                className="text-xl font-semibold mb-2 hover:text-blue-500"
              >
                {review.book.title}
              </Link>
              <p className="text-gray-600 mb-2">by {review.book.author}</p>
              <div className="flex items-center">
                <span className="text-yellow-400 text-lg">
                  {'★'.repeat(review.rating)}
                  {'☆'.repeat(5 - review.rating)}
                </span>
                <span className="ml-2 text-gray-600">
                  {review.rating} out of 5
                </span>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default UserReviews;