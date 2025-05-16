import { Link } from 'react-router-dom';
import { useAuthStore } from '../store/authStore';

const Navbar = () => {
  const { user, logout } = useAuthStore();

  return (
    <nav className="bg-white shadow-lg">
      <div className="max-w-7xl mx-auto px-4">
        <div className="flex justify-between h-16">
          <div className="flex">
            <Link to="/" className="flex items-center px-2 py-2 text-gray-700 hover:text-gray-900">
              Home
            </Link>
            <Link to="/clubs" className="flex items-center px-2 py-2 text-gray-700 hover:text-gray-900">
              Clubs
            </Link>
            {user && (
              <>
                <Link to="/playlists" className="flex items-center px-2 py-2 text-gray-700 hover:text-gray-900">
                  My Playlists
                </Link>
                <Link to="/my-reviews" className="flex items-center px-2 py-2 text-gray-700 hover:text-gray-900">
                  My Reviews
                </Link>
              </>
            )}
          </div>
          <div className="flex items-center">
            {user ? (
              <button
                onClick={logout}
                className="ml-4 px-4 py-2 rounded bg-red-500 text-white hover:bg-red-600"
              >
                Logout
              </button>
            ) : (
              <>
                <Link
                  to="/login"
                  className="px-4 py-2 rounded bg-blue-500 text-white hover:bg-blue-600"
                >
                  Login
                </Link>
                <Link
                  to="/register"
                  className="ml-4 px-4 py-2 rounded bg-green-500 text-white hover:bg-green-600"
                >
                  Register
                </Link>
              </>
            )}
          </div>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;