import React, { useEffect, useState } from "react";
import { Outlet, useNavigate } from "react-router-dom";
import { validUser } from "../utils";
import Swal from "sweetalert2";
import Login from "./Login";

const Auth = () => {
  const [valid, setValid] = useState(null);
  useEffect(() => {
    if (validUser()) {
      setValid(true);
    } else {
      Swal.fire({
        title: "Authentication Required",
        text: "Please login to continue.",
        icon: "warning",
        confirmButtonText: "OK",
      }).then(() => setValid(false));
    }
  }, []);

  if (valid === null) {
    return <div>Loading...</div>; // Optional: show a loading indicator while checking
  }

  return <div>{valid ? <Outlet /> : <Login />}</div>;
};

export default Auth;
