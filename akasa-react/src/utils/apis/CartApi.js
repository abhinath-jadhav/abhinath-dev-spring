import validUser from "../PriavateRoute";
import { axiosAuth } from "../axios";

class CartApi {
  static async saveCart(items) {
    if (validUser()) {
      const newList = items.map((o) => ({
        item: o.item,
        quantity: o.quantity,
      }));
      console.log(newList);
      const res = await axiosAuth.post("/user/carts", items);
      console.log(res);
    }
  }

  static async getAllCarts() {
    if (validUser()) {
      try {
        const res = await axiosAuth.get("/user/carts");
        return res.data;
      } catch {
        return null;
      }
    }
    return null;
  }

  static async completeOrder(data) {
    try {
      const res = await axiosAuth.post("/user/order", data);
      return res.data;
    } catch (error) {
      console.log(error);
    }
  }
}

export default CartApi;
