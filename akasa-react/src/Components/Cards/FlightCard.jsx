import React from "react";

const FlightCard = ({ flightId, path, handleClick }) => {
  return (
    <div className="flex gap-5">
      <p className="w-20">{flightId}</p>
      <p className="w-36">{path}</p>
      <input type="radio" name="flight" id="flight" />
    </div>
  );
};

export default FlightCard;
