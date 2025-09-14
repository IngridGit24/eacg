import { configureStore } from '@reduxjs/toolkit';
import authReducer from './slices/authSlice';
import schoolReducer from './slices/schoolSlice';
import studentReducer from './slices/studentSlice';
import enrollmentReducer from './slices/enrollmentSlice';

export const store = configureStore({
  reducer: {
    auth: authReducer,
    school: schoolReducer,
    student: studentReducer,
    enrollment: enrollmentReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
