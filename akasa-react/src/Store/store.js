import { configureStore } from "@reduxjs/toolkit";
import itemReducer from "./Feature/CartSlice.js";
import inventorySlice from "./Feature/inventorySlice.js";
import auth from "./Feature/authSlice.js";

export const store = configureStore({
  reducer: {
    cartItems: itemReducer,
    ineventory: inventorySlice,
    auth: auth,
  },
});
