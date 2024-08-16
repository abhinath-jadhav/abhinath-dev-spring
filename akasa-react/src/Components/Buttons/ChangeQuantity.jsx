import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { FaMinus, FaPlus } from "react-icons/fa";
import {
  addAll,
  addItem,
  reduceQuantity,
  removeItem,
} from "../../Store/Feature/CartSlice.js";
import Swal from "sweetalert2";

const ChangeQuantity = ({ id, qty, inventory, price }) => {
  const dispatch = useDispatch();

  const items = useSelector((state) => state.cartItems);

  const value = items.find((i) => i.item == id);

  const handleReduce = () => {
    if (value.quantity > 1) {
      dispatch(reduceQuantity(id));

      //dispatch(removeItem(id));
    } else {
      Swal.fire({
        title: "Item Removed",
        text: "Minimum 1 quantity is Item removed.",
        icon: "warning",
        confirmButtonText: "OK",
      });
      const filered = items.filter((i) => i.item != id);
      //console.log(filered);
      //console.log(id);
      dispatch(addAll(filered));
    }
  };

  const handleAdd = () => {
    //console.log(inventory);
    if (inventory != null && inventory.stock > 0) {
      if (value?.quantity > 4) {
        Swal.fire({
          title: "Limit reached",
          text: "Cannot add more then 5 quantity",
          icon: "warning",
          confirmButtonText: "OK",
        });
        return;
      } else {
        dispatch(addItem(id));
      }
      //setShowAddToCart(false);
    }
  };
  return (
    <div className="flex items-center gap-3">
      <div
        className="h-[32px] w-[80px] border-2 flex justify-between items-center text-primery 
                 font-medium text-sm text-center px-1
              "
      >
        <div onClick={handleReduce} className=" cursor-pointer">
          <FaMinus className="pl-1 text-gray-500" size={12} />
        </div>

        <div className="max-w-[40px] flex justify-center focus:ring-4 focus:outline-none focus:ring-blue-300">
          <p className="">{value?.quantity}</p>
        </div>
        <div onClick={handleAdd} className="cursor-pointer">
          <FaPlus className="text-green-600 pr-1" size={15} />
        </div>
      </div>
      <p className="w-[70px] text-end">
        {"₹ "} {price * value?.quantity}
      </p>
    </div>
  );
};

export default ChangeQuantity;
