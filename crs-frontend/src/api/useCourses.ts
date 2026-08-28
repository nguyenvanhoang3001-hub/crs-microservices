import { useState, useEffect, useCallback, useRef } from 'react';
import { getCourses } from './courseApi';
import type { Course } from '../types/course';
import type { ApiErrorResponse } from '../types/apiError';
import axios from 'axios';

export type LoadState = 'loading' | 'success' | 'empty' | 'error';

export function useCourses(keyword: string, page: number, size = 10) {
  const [courses, setCourses] = useState<Course[]>([]);
  const [totalPages, setTotalPages] = useState(0);
  const [state, setState] = useState<LoadState>('loading');
  const [errorMessage, setErrorMessage] = useState<string>('');
  const timeoutRef = useRef<number | null>(null);

  const fetchCourses = useCallback(() => {
    setState('loading');
    
    if (timeoutRef.current) {
      window.clearTimeout(timeoutRef.current);
    }

    // Gia lap delay 2 giay de dễ dang quan sat trang thai Loading
    timeoutRef.current = window.setTimeout(() => {
      getCourses(keyword, page, size)
        .then((res) => {
          const data = res.data;
          setCourses(data.content);
          setTotalPages(data.totalPages);
          setState(data.content.length === 0 ? 'empty' : 'success');
        })
        .catch((err) => {
          let message = 'Da xay ra loi khong xac dinh, vui long thu lai.';
          if (axios.isAxiosError<ApiErrorResponse>(err)) {
            if (err.response?.data?.message) {
              message = err.response.data.message;
            } else if (!err.response) {
              // Khong nhan duoc response nao ca - Gateway hoac course-service dang tat
              message = 'Khong ket noi duoc toi he thong. Vui long thu lai sau.';
            }
          }
          setErrorMessage(message);
          setState('error');
        });
    }, 2000);
  }, [keyword, page, size]);

  useEffect(() => {
    fetchCourses();
    return () => {
      if (timeoutRef.current) {
        window.clearTimeout(timeoutRef.current);
      }
    };
  }, [fetchCourses]);

  return { courses, totalPages, state, errorMessage, refetch: fetchCourses };
}
