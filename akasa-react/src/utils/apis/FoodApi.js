import { axiosAuth, axiosNoAuth } from "../axios";
import validUser from "../PriavateRoute";

class FoodApi {
  static async fetchHomeData(endpoint) {
    if (validUser()) {
      try {
        const response = await axiosNoAuth.get(endpoint);
        //console.log(response);
        return response.data;
      } catch (error) {
        console.error("API call failed:", error);
      }
    }
  }

  static async fetchFoodData(endpoint) {
    if (validUser()) {
      try {
        const response = await axiosAuth.get(endpoint);
        //console.log(response);
        return response.data;
      } catch (error) {
        return error;
      }
    }
  }

  static getSelected = async (ids) => {
    if (validUser()) {
      try {
        const response = await axiosAuth.post("/food/ids", ids);
        //console.log(response);
        return response.data;
      } catch (error) {
        return error;
      }
    }
  };
}

export default FoodApi;
