export type RegisterRequest = {
  fullName: string;
  email: string;
  password: string;
};

export type RegisterResponse = {
  id: number;
  fullName: string;
  email: string;
  role: string;
  message: string;
};

export type LoginRequest = {
  email: string;
  password: string;
};

export type LoginResponse = {
  id: number;
  fullName: string;
  email: string;
  role: string;
  token: string;
  tokenType: string;
  expiresIn: number;
};