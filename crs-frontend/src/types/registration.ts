export interface Registration {
  id: number;
  studentId: number;
  courseId: number;
  trangThai: 'DA_DANG_KY' | 'DA_HUY';
  ngayDangKy: string; // ISO date string tu backend
}

export interface RegistrationRequest {
  studentId: number;
  courseId: number;
}
