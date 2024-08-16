import React from "react";
import { GrSquare } from "react-icons/gr";
import ChangeQuantity from "../Buttons/ChangeQuantity";
const CartItemCard = ({ price, qty, name, diet, id, inventory }) => {
  //console.log(inventory);
  return (
    <>
      <div className="flex items-center gap-2">
        <GrSquare className="text-green-600" />
        <p>{name}</p>
      </div>

      <ChangeQuantity qty={qty} id={id} price={price} inventory={inventory} />
    </>
  );
};

export default CartItemCard;
