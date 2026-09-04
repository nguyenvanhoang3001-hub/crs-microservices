import { Link, useLocation } from 'react-router-dom';

export default function Navbar() {
  const location = useLocation();

  const isCurrent = (path: string) => location.pathname === path;

  return (
    <nav style={{ borderBottom: '1px solid #e5e7eb', padding: '12px 24px', marginBottom: 20 }}>
      <div style={{ maxWidth: 800, margin: '0 auto', display: 'flex', alignItems: 'center' }}>
        <strong style={{ marginRight: 24, fontSize: 18 }}>CRS Admin</strong>
        <Link
          to="/admin/courses"
          style={{
            marginRight: 16,
            textDecoration: 'none',
            color: isCurrent('/admin/courses') || isCurrent('/') ? '#2563eb' : '#333',
            fontWeight: isCurrent('/admin/courses') || isCurrent('/') ? 'bold' : 'normal',
          }}
        >
          Quan tri mon hoc
        </Link>
        <Link
          to="/admin/api-keys"
          style={{
            textDecoration: 'none',
            color: isCurrent('/admin/api-keys') ? '#2563eb' : '#333',
            fontWeight: isCurrent('/admin/api-keys') ? 'bold' : 'normal',
          }}
        >
          Quan ly API Key
        </Link>
      </div>
    </nav>
  );
}
