import React, { useEffect, useState } from "react";
import { CartApi, FoodApi, InventoryApi, UserApi } from "../utils";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
import {
  CartItemCard,
  CartUserDetails,
  Container,
  EmptyCart,
  Loading,
  SubFooter,
} from "../Components";
import { useDispatch, useSelector } from "react-redux";
import { pushInventory } from "../Store/Feature/inventorySlice";
import { addAll } from "../Store/Feature/CartSlice";

const Cart = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const [cartList, setCartList] = useState([]);

  const items = useSelector((state) => state.cartItems);
  const inventoryMap = useSelector((state) => state.ineventory);
  const [toPay, setToPay] = useState(0);
  const [flights, setFlights] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    //console.log(cartList);
    try {
      const map = {};
      cartList?.forEach((o) => {
        map[o.id] = o;
      });
      const pay = items.reduce(
        (sum, i) => i.quantity * map[i.item]?.price + sum,
        0
      );
      setToPay(Math.round(pay));
    } catch (err) {
      console.log(err);
    }
  });

  const fetchData = async () => {
    const fetchCartDetails = async () => {
      const data = await FoodApi.getCartDetails();

      setCartList(data.items);
    };
    const fetchInventory = async () => {
      const data = await InventoryApi.getAllInventories();
      if (data.status == 200) {
        const inventoryMap = {};
        data.inventories.forEach((item) => {
          inventoryMap[item.itemId] = item;
        });
        //console.log(inventoryMap);
        //setInventory(inventoryMap);
        dispatch(pushInventory(inventoryMap));
      }
    };

    if (Object.keys(inventoryMap) == 0) {
      await fetchInventory();
    }

    const fetchFlight = async () => {
      const data = await UserApi.getFlightDetails();
      if (data.status == 200) {
        setFlights(data.flights);
      }
    };
    await fetchFlight();

    await fetchCartDetails();
    setLoading(false);
  };

  useEffect(() => {
    fetchData();
  }, [items]);

  const handlePayment = async () => {
    //console.log(cartList);
    const data = {
      payment: toPay,
      items: items,
    };
    //console.log(data);
    const res = await CartApi.completeOrder(data);
    if (res.status == 200) {
      dispatch(addAll([]));
      localStorage.removeItem("cart");
      navigate("/payment", { state: { res } });
    } else {
      Swal.fire({
        title: "Error",
        text: res.message,
        icon: "warning",
        confirmButtonText: "OK",
      });
    }

    //console.log(res);
  };

  //console.log(!cartList ? "check" : "fail");

  if (loading)
    return (
      <>
        <Loading />
      </>
    );
  return (
    <>
      {!cartList ? (
        <div>
          <EmptyCart />
        </div>
      ) : (
        <div className="pt-4 ">
          <Container>
            <div className="w-[80%]">
              <h3 className="text-3xl">Secure checkout</h3>
              {/* <hr className="w-full mt-5 border-t shadow-hr-dark" /> */}

              <div className="w-full lg:flex justify-between mt-5">
                <div className="md:w-[55%] h-fit bg-white flex justify-center py-5">
                  <CartUserDetails
                    handlePayment={handlePayment}
                    flights={flights}
                  />
                </div>
                <div className="md:hidden border w-[100%] border-black my-10 shadow-hr-dark"></div>
                <div className="md:mt-0 md:w-[35%] h-fit pb-8 bg-white text-sm flex flex-col items-center">
                  <h3 className="text-2xl my-5">Price Breakup</h3>
                  <div className="border w-[85%] border-black"></div>
                  {/* <hr className="w-full mt-2 border-t shadow-hr-dark" /> */}
                  <div className="mt-8 flex flex-col gap-5 items-center justify-center w-full">
                    {cartList?.map((cart, i) => (
                      <div
                        key={i}
                        className="w-[80%] flex justify-between items-center select-none"
                      >
                        <CartItemCard
                          {...cart}
                          inventory={inventoryMap[cart.id]}
                        />
                      </div>
                    ))}
                    <div className="border w-[85%] border-black"></div>
                  </div>

                  <div className="mt-5 w-[80%] flex  items-center justify-between">
                    <p className="text-2xl">To pay</p>
                    <p className="text-2xl">
                      {"₹ "} {toPay}
                    </p>
                  </div>
                  <div className="mt-5">
                    <div className="flex justify-center mt-10">
                      <button
                        onClick={handlePayment}
                        className="text-3xl border p-6 bg-green-700 text-white"
                      >
                        Complete Payment
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </Container>

          <Container
            className={"justify-start items-start w-[50%]"}
          ></Container>

          <SubFooter
            desc={"Add few more items from our delicious menu !!"}
            button={"Explore now"}
            to={"/menu"}
          />
        </div>
      )}
    </>
  );
};

export default Cart;
