import React, { useState } from "react";

function TextRadioButton() {
  const [selectedValue, setSelectedValue] = useState("");

  const handleChange = (event) => {
    setSelectedValue(event.target.value);
  };

  return (
    <div className="flex flex-col space-y-4">
      <ul className="grid w-full gap-6 md:grid-cols-2">
        <li
          className={`${
            selectedValue === "hosting-small"
              ? "bg-blue-100 border-blue-500"
              : "bg-white"
          } rounded-lg p-2`}
        >
          <div>
            <input
              type="radio"
              id="hosting-small"
              name="pay"
              value="hosting-small"
              className="hidden"
              checked={selectedValue === "hosting-small"}
              onChange={handleChange}
            />
            <label
              htmlFor="hosting-small"
              className={`inline-flex items-center justify-between w-full px-5 py-3 text-slate-50 cursor-pointer ${
                selectedValue === "hosting-small"
                  ? "bg-primery"
                  : "bg-secondary"
              }`}
            >
              <div className="block text-center w-full">
                <div className="w-full text-lg font-semibold">UPI</div>
              </div>
            </label>
          </div>
        </li>
        <li
          className={`${
            selectedValue === "online"
              ? "bg-blue-100 border-blue-500"
              : "bg-white"
          } rounded-lg p-2`}
        >
          <div>
            <input
              type="radio"
              id="online"
              name="pay"
              value="online"
              className="hidden"
              checked={selectedValue === "online"}
              onChange={handleChange}
            />
            <label
              htmlFor="online"
              className={`inline-flex items-center justify-between w-full px-5 py-3 text-slate-50 cursor-pointer ${
                selectedValue === "online" ? "bg-primery" : "bg-secondary"
              }`}
            >
              <div className="block text-center w-full">
                <div className="w-full text-lg font-semibold">Online</div>
              </div>
            </label>
          </div>
        </li>
        <li
          className={`${
            selectedValue === "debit-card"
              ? "bg-blue-100 border-blue-500"
              : "bg-white"
          } rounded-lg p-2`}
        >
          <div>
            <input
              type="radio"
              id="debit-card"
              name="pay"
              value="debit-card"
              className="hidden"
              checked={selectedValue === "debit-card"}
              onChange={handleChange}
            />
            <label
              htmlFor="debit-card"
              className={`inline-flex items-center justify-between w-full px-5 py-3 text-slate-50 cursor-pointer ${
                selectedValue === "debit-card" ? "bg-primery" : "bg-secondary"
              }`}
            >
              <div className="block text-center w-full">
                <div className="w-full text-lg font-semibold">Debit Card</div>
              </div>
            </label>
          </div>
        </li>
        <li
          className={`${
            selectedValue === "credit-card"
              ? "bg-blue-100 border-blue-500"
              : "bg-white"
          } rounded-lg p-2`}
        >
          <div>
            <input
              type="radio"
              id="credit-card"
              name="pay"
              value="credit-card"
              className="hidden"
              checked={selectedValue === "credit-card"}
              onChange={handleChange}
            />
            <label
              htmlFor="credit-card"
              className={`inline-flex items-center justify-between w-full px-5 py-3 text-slate-50 cursor-pointer ${
                selectedValue === "credit-card" ? "bg-primery" : "bg-secondary"
              }`}
            >
              <div className="block text-center w-full">
                <div className="w-full text-lg font-semibold">Credit Card</div>
              </div>
            </label>
          </div>
        </li>
      </ul>
    </div>
  );
}

export default TextRadioButton;
