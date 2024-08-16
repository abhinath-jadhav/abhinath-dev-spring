// features/items/itemsSlice.js
import { createSlice } from "@reduxjs/toolkit";

const itemsSlice = createSlice({
  name: "items",
  initialState: [], // Initialize with an empty array
  reducers: {
    addItem: (state, action) => {
      const item = action.payload;
      // console.log(item);
      const existingItem = state.find((i) => i.item === item);
      if (existingItem) {
        // Update quantity if item exists
        existingItem.quantity++;
      } else {
        // Add new item if it doesn't exist
        state.push({ item: item, quantity: 1 });
      }
    },
    removeItem: (state, action) => {
      const item = action.payload;
      // Filter out the item to remove it from the list
      return state.filter((i) => i.item !== item);
    },
    reduceQuantity: (state, action) => {
      const item = action.payload;
      //console.log(item);
      const existingItem = state.find((i) => i.item === item);
      if (existingItem) {
        existingItem.quantity--;
      }
    },
    addAll: (state, action) => {
      //console.log(action.payload);
      return action.payload;
    },
  },
});

export const { addItem, removeItem, reduceQuantity, addAll } =
  itemsSlice.actions;

export default itemsSlice.reducer;
