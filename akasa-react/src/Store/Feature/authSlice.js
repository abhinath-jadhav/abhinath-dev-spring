import { createSlice } from "@reduxjs/toolkit";

const initialState = false;

const authSlice = createSlice({
  initialState: initialState,
  name: "Auth",
  reducers: {
    setAuth: (state) => !state,
  },
});

export const { setAuth } = authSlice.actions;

export default authSlice.reducer;
