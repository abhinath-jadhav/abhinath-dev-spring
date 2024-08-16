import { axiosAuth, axiosNoAuth } from "../axios";
import validUser from "../PriavateRoute";

class InventoryApi {
  static async getAllInventories() {
    if (!validUser()) return;
    try {
      const response = await axiosAuth.get("/inventory");
      return response.data;
    } catch (error) {
      console.log(error);
    }
  }

  static async getInventory(id) {
    if (!validUser()) return;
    try {
      const response = await axiosAuth.get("/inventory/" + id);
      return response.data;
    } catch (error) {
      console.log(error);
    }
  }
}

export default InventoryApi;
