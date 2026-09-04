import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import Navbar from './components/Navbar';
import ProtectedRoute from './components/ProtectedRoute';
import CoursesPage from './pages/CoursesPage';
import ApiKeysPage from './pages/ApiKeysPage';

function App() {
  return (
    <BrowserRouter>
      <div>
        <Navbar />
        <Routes>
          <Route path="/" element={<CoursesPage />} />
          <Route path="/admin/courses" element={<CoursesPage />} />
          <Route
            path="/admin/api-keys"
            element={
              <ProtectedRoute requiredRole="ADMIN">
                <ApiKeysPage />
              </ProtectedRoute>
            }
          />
          <Route path="*" element={<Navigate to="/admin/courses" replace />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
