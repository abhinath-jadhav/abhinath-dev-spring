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
import { CartApi } from "../../utils/index.js";
import { addCartDetails } from "../../Store/Feature/Cartdetails.js";
import EmptyCart from "../Cards/EmptyCart.jsx";
import { useNavigate } from "react-router-dom";

const ChangeQuantity = ({ id, qty, inventory, price }) => {
  const dispatch = useDispatch();

  const items = useSelector((state) => state.cartItems);
  const nav = useNavigate();

  const handleReduce = async () => {
    if (qty > 1) {
      dispatch(reduceQuantity(id));

      const reduced = items.map((i) => {
        if (i.item == id) {
          return {
            item: i.item,
            quantity: i.quantity - 1,
          };
        }
        return i;
      });
      const res = await CartApi.saveCart(reduced);

      dispatch(addCartDetails(res.items));
    } else {
      Swal.fire({
        title: "Item Removed",
        text: "Minimum 1 quantity is Item removed.",
        icon: "warning",
        confirmButtonText: "OK",
      });
      const filered = items.filter((i) => i.item != id);

      dispatch(addAll(filered));

      const res = await CartApi.saveCart(filered);
      if (res.status == 500) {
        dispatch(addCartDetails([]));
        nav("/auth/emptycart");
      } else {
        dispatch(addCartDetails(res.items));
      }
    }
  };

  const handleAdd = async () => {
    //console.log(inventory);
    if (inventory != null && inventory.stock > 0) {
      if (qty > 4) {
        Swal.fire({
          title: "Limit reached",
          text: "Cannot add more then 5 quantity",
          icon: "warning",
          confirmButtonText: "OK",
        });
        return;
      } else {
        dispatch(addItem(id));
        const added = items.map((i) => {
          if (i.item == id) {
            return {
              item: i.item,
              quantity: i.quantity + 1,
            };
          }
          return i;
        });
        const res = await CartApi.saveCart(added);
        console.log(res);

        dispatch(addCartDetails(res.items));
      }
      //setShowAddToCart(false);
    }
  };
  return (
    <div className={"flex items-center gap-3"}>
      <div
        className="h-[32px] w-[80px] border-2 flex justify-between items-center text-primery 
                 font-medium text-sm text-center px-1
              "
      >
        <div onClick={handleReduce} className=" cursor-pointer">
          <FaMinus className="pl-1 text-gray-500" size={12} />
        </div>

        <div className="max-w-[40px] flex justify-center focus:ring-4 focus:outline-none focus:ring-blue-300">
          <p className="">{qty}</p>
        </div>
        <div onClick={handleAdd} className="cursor-pointer">
          <FaPlus className="text-green-600 pr-1" size={15} />
        </div>
      </div>
      <p className="w-[50px] text-end">
        {"₹ "} {price * qty}
      </p>
    </div>
  );
};

export default ChangeQuantity;
