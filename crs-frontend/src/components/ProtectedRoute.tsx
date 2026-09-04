import type { ReactNode } from 'react';

interface ProtectedRouteProps {
  requiredRole?: string;
  children: ReactNode;
}

export default function ProtectedRoute({ children }: ProtectedRouteProps) {
  return <>{children}</>;
}
