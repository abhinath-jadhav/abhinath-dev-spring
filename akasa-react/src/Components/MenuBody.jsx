import React, { useEffect, useState } from "react";
import FoodMenuCard from "./FoodMenuCard";
import { InventoryApi, validUser } from "../utils";
import { useDispatch } from "react-redux";
import { pushInventory } from "../Store/Feature/inventorySlice";
import Swal from "sweetalert2";
import { useNavigate } from "react-router-dom";

const MenuBody = ({ filteredFoodItems }) => {
  const [inventory, setInventory] = useState({});
  const dispatch = useDispatch();

  const [isAuthenticated, setAuthenticated] = useState(false);
  const nav = useNavigate();

  useEffect(() => {
    const valid = validUser();
    if (!valid) {
      nav("/login");
      Swal.fire({
        title: "Authentication Required",
        text: "Please login to continue.",
        icon: "warning",
        confirmButtonText: "OK",
      });
      return;
    } else {
      setAuthenticated(true);
    }
  });

  useEffect(() => {
    //if (isAuthenticated) return;
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
