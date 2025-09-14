import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';

interface School {
  id: number;
  matricule: string;
  name: string;
  dateOfCreation: string;
  typeOfSchool: string;
  location: string;
  numberOfClass: number;
  capacityPerClass: number;
}

interface SchoolState {
  schools: School[];
  loading: boolean;
  error: string | null;
}

const initialState: SchoolState = {
  schools: [],
  loading: false,
  error: null,
};

export const fetchSchools = createAsyncThunk(
  'school/fetchSchools',
  async () => {
    const response = await fetch('/api/schools');
    if (!response.ok) {
      throw new Error('Failed to fetch schools');
    }
    return response.json();
  }
);

const schoolSlice = createSlice({
  name: 'school',
  initialState,
  reducers: {
    clearError: (state) => {
      state.error = null;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchSchools.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(fetchSchools.fulfilled, (state, action) => {
        state.loading = false;
        state.schools = action.payload;
      })
      .addCase(fetchSchools.rejected, (state, action) => {
        state.loading = false;
        state.error = action.error.message || 'Failed to fetch schools';
      });
  },
});

export const { clearError } = schoolSlice.actions;
export default schoolSlice.reducer;
