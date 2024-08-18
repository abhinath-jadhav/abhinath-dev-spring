import React, { useState } from "react";
import { GrSquare } from "react-icons/gr";
import ChangeQuantity from "../Buttons/ChangeQuantity";
import { useSelector } from "react-redux";
const CartItemCard = ({ price, qty, name, diet, id, inventory }) => {
  const cartDetails = useSelector((state) => state.cartdetails);

  const value = cartDetails.find((i) => i.id == id);

  if (!value) {
    return <></>;
  }

  return (
    <>
      <div className={"flex items-center gap-2"}>
        <GrSquare
          className={diet == 1 ? "text-green-600" : "text-red-600"}
          size={12}
        />
        <p>{value.name}</p>
      </div>

      <ChangeQuantity {...value} inventory={inventory} />
    </>
  );
};

export default CartItemCard;
