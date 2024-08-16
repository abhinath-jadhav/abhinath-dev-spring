import React, { useEffect, useState } from "react";
import { UserApi, getUser } from "../utils";
import FlightCard from "./Cards/FlightCard";
import TextRadioButton from "./Buttons/CustomRadioButton";
import { Link } from "react-router-dom";

const CartUserDetails = ({ handlePayment }) => {
  const [user, setUser] = useState("");
  const [flights, setFlights] = useState([]);

  useEffect(() => {
    const user = getUser();
    setUser(user);
    const fetchFlight = async () => {
      const data = await UserApi.getFlightDetails();
      if (data.status == 200) {
        setFlights(data.flights);
      }
    };
    fetchFlight();
  }, []);
  const handleClick = () => {};
  return (
    <div className="w-[80%]">
      <div>
        <p className="text-2xl">Contact details</p>
        <div className="flex mt-5">
          <p className="font-semibold">Email Id : </p>
          <p className="ml-2"> {user}</p>
        </div>

        <div className="flex">
          <p className="font-semibold">Phone Number: </p>
          <p className="ml-2"> ******9865 </p>
        </div>
      </div>
      <div className="border w-[100%] border-black my-5 "></div>
      <div className="w-full">
        <p className="text-2xl mb-5">Select flight</p>
        <div className="flex flex-col gap-4">
          {flights.map((f) => (
            <div key={f.id} className="">
              <FlightCard {...f} handleClick={handleClick} />
            </div>
          ))}
        </div>
      </div>
      <div className="border w-[100%] border-black my-5"></div>
      <div>
        <h3 className="text-2xl mb-5">Select Payment</h3>
        <div className="flex justify-center">
          <div className="flex flex-wrap justify-center">
            <TextRadioButton />
          </div>
        </div>
        <div className="w-full flex justify-center mt-10">
          <button
            onClick={handlePayment}
            className="text-3xl border p-6 bg-green-700 text-white"
          >
            Complete Payment
          </button>
        </div>
      </div>
    </div>
  );
};

export default CartUserDetails;
