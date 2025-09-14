import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';

interface Student {
  id: number;
  matricule: string;
  name: string;
  lastName: string;
  birthDate: string;
  level: string;
  pictureUrl?: string;
}

interface StudentState {
  students: Student[];
  loading: boolean;
  error: string | null;
}

const initialState: StudentState = {
  students: [],
  loading: false,
  error: null,
};

export const fetchStudents = createAsyncThunk(
  'student/fetchStudents',
  async () => {
    const response = await fetch('/api/students');
    if (!response.ok) {
      throw new Error('Failed to fetch students');
    }
    return response.json();
  }
);

const studentSlice = createSlice({
  name: 'student',
  initialState,
  reducers: {
    clearError: (state) => {
      state.error = null;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchStudents.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(fetchStudents.fulfilled, (state, action) => {
        state.loading = false;
        state.students = action.payload;
      })
      .addCase(fetchStudents.rejected, (state, action) => {
        state.loading = false;
        state.error = action.error.message || 'Failed to fetch students';
      });
  },
});

export const { clearError } = studentSlice.actions;
export default studentSlice.reducer;
