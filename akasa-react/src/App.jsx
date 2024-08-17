import React from "react";
import { BrowserRouter as Router, Route, Routes, Link } from "react-router-dom";
import { Cart, Home, Layout, Login, Menu, NotFound, Register } from "./Pages";
import Payment from "./Pages/Payment";
import Order from "./Pages/Order";
import Auth from "./Pages/Auth";
import { EmptyCart } from "./Components";

const App = () => {
  return (
    <Router>
      <div className="max-w-screen-sm md:max-w-full">
        <Routes>
          <Route element={<Layout />}>
            <Route index element={<Home />} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />
            <Route path="*" element={<NotFound />} />
            <Route path="/auth" element={<Auth />}>
              <Route path="menu" element={<Menu />} />
              <Route path="cart" element={<Cart />} />
              <Route path="payment" element={<Payment />} />
              <Route path="orders" element={<Order />} />
              <Route path="emptycart" element={<EmptyCart />} />
            </Route>
          </Route>
          {/* 404 Route */}
        </Routes>
      </div>
    </Router>
  );
};

export default App;
