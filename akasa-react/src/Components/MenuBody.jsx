import React, { useEffect, useState } from "react";
import FoodMenuCard from "./FoodMenuCard";
import { InventoryApi } from "../utils";
import { useDispatch } from "react-redux";
import { pushInventory } from "../Store/Feature/inventorySlice";

const MenuBody = ({ filteredFoodItems }) => {
  const [inventory, setInventory] = useState({});
  const dispatch = useDispatch();
  useEffect(() => {
    const fetchInventory = async () => {
      const data = await InventoryApi.getAllInventories();
      if (data.status == 200) {
        const inventoryMap = {};
        data.inventories.forEach((item) => {
          inventoryMap[item.itemId] = item;
        });
        //console.log(inventoryMap);
        setInventory(inventoryMap);
        dispatch(pushInventory(inventoryMap));
      }
    };

    fetchInventory();
  }, []);
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
