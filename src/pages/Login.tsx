import { useForm } from 'react-hook-form';
import { useNavigate } from 'react-router-dom';
import { useAuthStore } from '../store/authStore';
import api from '../api/axios';

interface LoginForm {
  email: string;
  password: string;
}

const Login = () => {
  const { register, handleSubmit, formState: { errors } } = useForm<LoginForm>();
  const navigate = useNavigate();
  const setAuth = useAuthStore(state => state.setAuth);

  const onSubmit = async (data: LoginForm) => {
    try {
      const response = await api.post('/users/login', data);
      setAuth(response.data.token, response.data.user);
      navigate('/');
    } catch (error) {
      console.error('Login failed:', error);
    }
  };

  return (
    <div className="max-w-md mx-auto bg-white p-8 rounded-lg shadow-md">
      <h2 className="text-2xl font-bold mb-6 text-center">Login</h2>
      <form onSubmit={handleSubmit(onSubmit)} className="space-y-4">
        <div>
          <label className="block text-sm font-medium text-gray-700">Email</label>
          <input
            type="email"
            {...register('email', { required: 'Email is required' })}
            className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring focus:ring-blue-200"
          />
          {errors.email && <p className="text-red-500 text-sm">{errors.email.message}</p>}
        </div>
        <div>
          <label className="block text-sm font-medium text-gray-700">Password</label>
          <input
            type="password"
            {...register('password', { required: 'Password is required' })}
            className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring focus:ring-blue-200"
          />
          {errors.password && <p className="text-red-500 text-sm">{errors.password.message}</p>}
        </div>
        <button
          type="submit"
          className="w-full bg-blue-500 text-white py-2 px-4 rounded-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2"
        >
          Login
        </button>
      </form>
    </div>
  );
};

export default Login;