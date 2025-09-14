import { createSlice, createAsyncThunk, PayloadAction } from '@reduxjs/toolkit';

interface Application {
  id: number;
  applicationNumber: string;
  studentId: number;
  schoolId: number;
  applicationDate: string;
  status: string;
  requestedLevel: string;
  academicYear: string;
  notes?: string;
  documents?: string[];
}

interface IntegrationTest {
  id: number;
  applicationId: number;
  studentId: number;
  testDate: string;
  testLocation: string;
  status: string;
  score?: number;
  comments?: string;
  examinerName?: string;
}

interface EnrollmentState {
  applications: Application[];
  integrationTests: IntegrationTest[];
  loading: boolean;
  error: string | null;
}

const initialState: EnrollmentState = {
  applications: [],
  integrationTests: [],
  loading: false,
  error: null,
};

export const fetchApplications = createAsyncThunk(
  'enrollment/fetchApplications',
  async () => {
    const response = await fetch('/api/enrollments/applications');
    if (!response.ok) {
      throw new Error('Failed to fetch applications');
    }
    return response.json();
  }
);

export const createApplication = createAsyncThunk(
  'enrollment/createApplication',
  async (application: Omit<Application, 'id' | 'applicationNumber' | 'applicationDate'>) => {
    const response = await fetch('/api/enrollments/applications', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(application),
    });
    
    if (!response.ok) {
      throw new Error('Failed to create application');
    }
    
    return response.json();
  }
);

export const updateApplicationStatus = createAsyncThunk(
  'enrollment/updateApplicationStatus',
  async ({ id, action }: { id: number; action: string }) => {
    const response = await fetch(`/api/enrollments/applications/${id}/process?action=${action}`, {
      method: 'POST',
    });
    
    if (!response.ok) {
      throw new Error('Failed to update application status');
    }
    
    return response.json();
  }
);

export const scheduleIntegrationTest = createAsyncThunk(
  'enrollment/scheduleIntegrationTest',
  async (test: Omit<IntegrationTest, 'id'>) => {
    const response = await fetch('/api/enrollments/integration-tests', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(test),
    });
    
    if (!response.ok) {
      throw new Error('Failed to schedule integration test');
    }
    
    return response.json();
  }
);

const enrollmentSlice = createSlice({
  name: 'enrollment',
  initialState,
  reducers: {
    clearError: (state) => {
      state.error = null;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchApplications.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(fetchApplications.fulfilled, (state, action) => {
        state.loading = false;
        state.applications = action.payload;
      })
      .addCase(fetchApplications.rejected, (state, action) => {
        state.loading = false;
        state.error = action.error.message || 'Failed to fetch applications';
      })
      .addCase(createApplication.fulfilled, (state, action) => {
        state.applications.push(action.payload);
      })
      .addCase(updateApplicationStatus.fulfilled, (state, action) => {
        const index = state.applications.findIndex(app => app.id === action.payload.id);
        if (index !== -1) {
          state.applications[index] = action.payload;
        }
      })
      .addCase(scheduleIntegrationTest.fulfilled, (state, action) => {
        state.integrationTests.push(action.payload);
      });
  },
});

export const { clearError } = enrollmentSlice.actions;
export default enrollmentSlice.reducer;
