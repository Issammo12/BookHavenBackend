import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import Home from './pages/Home';
import Login from './pages/Login';
import Register from './pages/Register';
import BookDetails from './pages/BookDetails';
import Playlists from './pages/Playlists';
import PlaylistDetails from './pages/PlaylistDetails';
import Clubs from './pages/Clubs';
import ClubDetails from './pages/ClubDetails';
import UserReviews from './pages/UserReviews';
import PrivateRoute from './components/PrivateRoute';

function App() {
  return (
    <Router>
      <div className="min-h-screen bg-gray-100">
        <Navbar />
        <main className="container mx-auto px-4 py-8">
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />
            <Route path="/books/:id" element={<BookDetails />} />
            <Route
              path="/playlists"
              element={
                <PrivateRoute>
                  <Playlists />
                </PrivateRoute>
              }
            />
            <Route
              path="/playlists/:id"
              element={
                <PrivateRoute>
                  <PlaylistDetails />
                </PrivateRoute>
              }
            />
            <Route path="/clubs" element={<Clubs />} />
            <Route path="/clubs/:id" element={<ClubDetails />} />
            <Route
              path="/my-reviews"
              element={
                <PrivateRoute>
                  <UserReviews />
                </PrivateRoute>
              }
            />
          </Routes>
        </main>
      </div>
    </Router>
  );
}

export default App;