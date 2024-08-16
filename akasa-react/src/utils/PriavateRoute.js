import { jwtDecode } from "jwt-decode";

const validUser = () => {
  const token = localStorage.getItem("token");
  //console.log(token);
  if (!token) return false;
  try {
    const decoded = jwtDecode(token);
    const currentTime = Date.now() / 1000;

    // Check if token is expired
    if (decoded.exp < currentTime) {
      return false;
    }

    return true;
  } catch (error) {
    return false;
  }
};

const getUser = () => {
  const token = localStorage.getItem("token");
  const decoded = jwtDecode(token);

  return decoded.sub;
};

export { getUser };

export default validUser;
