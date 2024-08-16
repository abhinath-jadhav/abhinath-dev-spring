import React from "react";
import { BrowserRouter as Router, Route, Routes, Link } from "react-router-dom";
import { Cart, Home, Layout, Login, Menu, NotFound, Register } from "./Pages";
import Payment from "./Pages/Payment";
import Order from "./Pages/Order";

const App = () => {
  return (
    <Router>
      <div>
        <Routes>
          <Route element={<Layout />}>
            <Route index element={<Home />} />
            <Route path="/menu" element={<Menu />} />
            <Route path="/cart" element={<Cart />} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />
            <Route path="/payment" element={<Payment />} />
            <Route path="*" element={<NotFound />} />
            <Route path="/orders" element={<Order />} />
          </Route>
          {/* 404 Route */}
        </Routes>
      </div>
    </Router>
  );
};

export default App;
