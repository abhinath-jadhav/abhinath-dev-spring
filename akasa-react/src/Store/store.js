import { configureStore } from "@reduxjs/toolkit";
import itemReducer from "./Feature/CartSlice.js";
import inventorySlice from "./Feature/inventorySlice.js";
import auth from "./Feature/authSlice.js";
import cartdetails, { addCartDetails } from "./Feature/Cartdetails.js";

export const store = configureStore({
  reducer: {
    cartItems: itemReducer,
    ineventory: inventorySlice,
    auth: auth,
    cartdetails: cartdetails,
  },
});
