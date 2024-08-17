import React, { useEffect, useState } from "react";
import FoodMenuCard from "./FoodMenuCard";
import { InventoryApi } from "../utils";
import { useDispatch } from "react-redux";
import { pushInventory } from "../Store/Feature/inventorySlice";

const MenuBody = ({ filteredFoodItems, inventory }) => {
  useEffect(() => {}, []);
  return (
    <div className="mt-5 flex flex-wrap justify-center gap-5">
      {filteredFoodItems.map((item, index) => (
        <FoodMenuCard
          inventory={inventory[item.id]}
          index={index}
          {...item}
          key={item.id}
        />
      ))}
    </div>
  );
};

export default MenuBody;
