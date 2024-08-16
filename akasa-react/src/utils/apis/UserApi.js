import { axiosAuth } from "../axios";
import validUser from "../PriavateRoute";

class UserApi {
  static getFlightDetails = async () => {
    if (!validUser()) return;
    try {
      const response = await axiosAuth.get("/user/flight");
      return response.data;
    } catch (error) {
      console.log(error);
    }
  };

  static getAllOrders = async () => {
    if (!validUser()) return;
    try {
      const response = await axiosAuth.get("/user/orders");
      return response.data;
    } catch (error) {
      console.log(error);
    }
  };
}

export default UserApi;
