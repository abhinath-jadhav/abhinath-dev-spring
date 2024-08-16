package com.akasa.food.order.utils;

import com.akasa.food.order.models.Category;
import com.akasa.food.order.models.FoodItem;
import com.akasa.food.order.models.Inventory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DummyData {

    static ObjectMapper objectMapper = new ObjectMapper();

    public static String categories = "    [\n" +
            "        {\n" +
            "            \"name\": \"Pizza\",\n" +
            "            \"desc\": \"Select from Pizzas\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"Sandwich\",\n" +
            "            \"desc\": \"Select from Sandwiches\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"Fruits\",\n" +
            "            \"desc\": \"Select from Fruits\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"Burger\",\n" +
            "            \"desc\": \"Select from Burgers\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"Rice\",\n" +
            "            \"desc\": \"Select from Rice\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"Fast food\",\n" +
            "            \"desc\": \"Select from Fast foods\"\n" +
            "        }\n" +
            "    ]";

    private static String inventory = "[\n" +
            "  {\n" +
            "    \"itemId\": 1,\n" +
            "    \"itemName\": \"Corn Pizza\",\n" +
            "    \"stock\": 5\n" +
            "  },\n" +
            "  {\n" +
            "    \"itemId\": 2,\n" +
            "    \"itemName\": \"Chicken Pizza\",\n" +
            "    \"stock\": 8\n" +
            "  },\n" +
            "  {\n" +
            "    \"itemId\": 3,\n" +
            "    \"itemName\": \"Paneer Pizza\",\n" +
            "    \"stock\": 0\n" +
            "  },\n" +
            "  {\n" +
            "    \"itemId\": 4,\n" +
            "    \"itemName\": \"Veg Pizza\",\n" +
            "    \"stock\": 0\n" +
            "  },\n" +
            "  {\n" +
            "    \"itemId\": 5,\n" +
            "    \"itemName\": \"Veg Sandwich\",\n" +
            "    \"stock\": 1\n" +
            "  },\n" +
            "  {\n" +
            "    \"itemId\": 6,\n" +
            "    \"itemName\": \"Chicken Sandwich\",\n" +
            "    \"stock\": 3\n" +
            "  },\n" +
            "  {\n" +
            "    \"itemId\": 7,\n" +
            "    \"itemName\": \"Fruit Juice\",\n" +
            "    \"stock\": 5\n" +
            "  },\n" +
            "  {\n" +
            "    \"itemId\": 8,\n" +
            "    \"itemName\": \"Fruit bowl\",\n" +
            "    \"stock\": 0\n" +
            "  },\n" +
            "  {\n" +
            "    \"itemId\": 9,\n" +
            "    \"itemName\": \"Chicken Burger\",\n" +
            "    \"stock\": 7\n" +
            "  },\n" +
            "  {\n" +
            "    \"itemId\": 10,\n" +
            "    \"itemName\": \"Veg Burger\",\n" +
            "    \"stock\": 7\n" +
            "  },\n" +
            "  {\n" +
            "    \"itemId\": 11,\n" +
            "    \"itemName\": \"Paneer Burger\",\n" +
            "    \"stock\": 4\n" +
            "  },\n" +
            "  {\n" +
            "    \"itemId\": 12,\n" +
            "    \"itemName\": \"Egg Burger\",\n" +
            "    \"stock\": 2\n" +
            "  },\n" +
            "  {\n" +
            "    \"itemId\": 13,\n" +
            "    \"itemName\": \"Veg Rice\",\n" +
            "    \"stock\": 0\n" +
            "  },\n" +
            "  {\n" +
            "    \"itemId\": 14,\n" +
            "    \"itemName\": \"Chinese Rice\",\n" +
            "    \"stock\": 4\n" +
            "  },\n" +
            "  {\n" +
            "    \"itemId\": 15,\n" +
            "    \"itemName\": \"Veg Biryani 15\",\n" +
            "    \"stock\": 3\n" +
            "  },\n" +
            "  {\n" +
            "    \"itemId\": 16,\n" +
            "    \"itemName\": \"Chicken Biryani\",\n" +
            "    \"stock\": 0\n" +
            "  },\n" +
            "  {\n" +
            "    \"itemId\": 17,\n" +
            "    \"itemName\": \"Vadapav\",\n" +
            "    \"stock\": 0\n" +
            "  },\n" +
            "  {\n" +
            "    \"itemId\": 18,\n" +
            "    \"itemName\": \"Samosa\",\n" +
            "    \"stock\": 9\n" +
            "  },\n" +
            "  {\n" +
            "    \"itemId\": 19,\n" +
            "    \"itemName\": \"Kachori\",\n" +
            "    \"stock\": 4\n" +
            "  },\n" +
            "  {\n" +
            "    \"itemId\": 20,\n" +
            "    \"itemName\": \"Bread pattis\",\n" +
            "    \"stock\": 1\n" +
            "  }\n" +
            "]\n";

    private static String food = "[\n" +
            "  {\n" +
            "    \"name\": \"Corn Pizza\",\n" +
            "    \"price\": 250,\n" +
            "    \"diet\": 1,\n" +
            "    \"category\": 1,\n" +
            "    \"description\": \"Spicy paneer, candied mango and charred bell pepper sandwich served on white and brown bread\",\n" +
            "    \"img\": \"https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,h_600/f37cc995c130a39daae423f075bd23d4\",\n" +
            "    \"ratings\": 4,\n" +
            "    \"featured\": false\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Chicken Pizza\",\n" +
            "    \"price\": 300,\n" +
            "    \"diet\": 2,\n" +
            "    \"category\": 1,\n" +
            "    \"description\": \"Spicy paneer, candied mango and charred bell pepper sandwich served on white and brown bread\",\n" +
            "    \"img\": \"https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_208,h_208,c_fit/fe37811c8d1a9b0c5923ff1a60926b7d\",\n" +
            "    \"ratings\": 4,\n" +
            "    \"featured\": true\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Paneer Pizza\",\n" +
            "    \"price\": 300,\n" +
            "    \"diet\": 1,\n" +
            "    \"category\": 1,\n" +
            "    \"description\": \"Spicy paneer, candied mango and charred bell pepper sandwich served on white and brown bread\",\n" +
            "    \"img\": \"https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_208,h_208,c_fit/FOOD_CATALOG/IMAGES/CMS/2024/7/12/458b187b-20ea-4c58-ae00-228cf3c34be5_1a31c323-330b-47e6-9718-886805c08f13.jpg\",\n" +
            "    \"ratings\": 4.5,\n" +
            "    \"featured\": false\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Veg Pizza\",\n" +
            "    \"price\": 250,\n" +
            "    \"diet\": 1,\n" +
            "    \"category\": 1,\n" +
            "    \"description\": \"Spicy paneer, candied mango and charred bell pepper sandwich served on white and brown bread\",\n" +
            "    \"img\": \"https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_208,h_208,c_fit/iugtwbuqpi6enzij2r24\",\n" +
            "    \"ratings\": 5,\n" +
            "    \"featured\": true\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Veg Sandwich\",\n" +
            "    \"price\": 100,\n" +
            "    \"diet\": 1,\n" +
            "    \"category\": 2,\n" +
            "    \"description\": \"Spicy paneer, candied mango and charred bell pepper sandwich served on white and brown bread\",\n" +
            "    \"img\": \"https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_208,h_208,c_fit/FOOD_CATALOG/IMAGES/CMS/2024/4/24/b557c550-582c-4298-bb57-3cf9c18d62e2_4e51bd8e-a839-4fab-ae13-4bc794d4085d.jpg\",\n" +
            "    \"ratings\": 4.5,\n" +
            "    \"featured\": false\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Chicken Sandwich\",\n" +
            "    \"price\": 100,\n" +
            "    \"diet\": 2,\n" +
            "    \"category\": 2,\n" +
            "    \"description\": \"Spicy paneer, candied mango and charred bell pepper sandwich served on white and brown bread\",\n" +
            "    \"img\": \"https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_208,h_208,c_fit/FOOD_CATALOG/IMAGES/CMS/2024/6/24/3de5c86f-edb5-477e-92d7-ef22e63b2154_9c5e49df-0655-4eef-bf14-02352ba21fe3.JPG\",\n" +
            "    \"ratings\": 4,\n" +
            "    \"featured\": true\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Fruit Juice\",\n" +
            "    \"price\": 100,\n" +
            "    \"diet\": 1,\n" +
            "    \"category\": 3,\n" +
            "    \"description\": \"Spicy paneer, candied mango and charred bell pepper sandwich served on white and brown bread\",\n" +
            "    \"img\": \"https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_112,h_112,c_fill/s4ddcyrmerhips45jitr\",\n" +
            "    \"ratings\": 4,\n" +
            "    \"featured\": false\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Fruit Bowl\",\n" +
            "    \"price\": 100,\n" +
            "    \"diet\": 1,\n" +
            "    \"category\": 3,\n" +
            "    \"description\": \"Spicy paneer, candied mango and charred bell pepper sandwich served on white and brown bread\",\n" +
            "    \"img\": \"https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_112,h_112,c_fill/d3868c1acee58c2847a8687785c8c965\",\n" +
            "    \"ratings\": 4.5,\n" +
            "    \"featured\": true\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Chicken Burger\",\n" +
            "    \"price\": 100,\n" +
            "    \"diet\": 1,\n" +
            "    \"category\": 4,\n" +
            "    \"description\": \"Spicy paneer, candied mango and charred bell pepper sandwich served on white and brown bread\",\n" +
            "    \"img\": \"https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_112,h_112,c_fill/fralafdv3iw3pvtf4ocv\",\n" +
            "    \"ratings\": 3.5,\n" +
            "    \"featured\": false\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Veg Burger\",\n" +
            "    \"price\": 200,\n" +
            "    \"diet\": 1,\n" +
            "    \"category\": 4,\n" +
            "    \"description\": \"Spicy paneer, candied mango and charred bell pepper sandwich served on white and brown bread\",\n" +
            "    \"img\": \"https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_112,h_112,c_fill/37035f5eedcd36d4740be311a3e407f2\",\n" +
            "    \"ratings\": 5,\n" +
            "    \"featured\": true\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Paneer Burger\",\n" +
            "    \"price\": 200,\n" +
            "    \"diet\": 1,\n" +
            "    \"category\": 4,\n" +
            "    \"description\": \"Spicy paneer, candied mango and charred bell pepper sandwich served on white and brown bread\",\n" +
            "    \"img\": \"https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_208,h_208,c_fit/FOOD_CATALOG/IMAGES/CMS/2024/4/17/9e2b428f-de45-457c-89ec-8868616800e3_fbcb04f9-4af0-4b1d-b51c-6568d9f78509.jpeg\",\n" +
            "    \"ratings\": 4,\n" +
            "    \"featured\": false\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Egg Burger\",\n" +
            "    \"price\": 220,\n" +
            "    \"diet\": 2,\n" +
            "    \"category\": 4,\n" +
            "    \"description\": \"Spicy paneer, candied mango and charred bell pepper sandwich served on white and brown bread\",\n" +
            "    \"img\": \"https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_208,h_208,c_fit/FOOD_CATALOG/IMAGES/CMS/2024/7/20/b41f9c09-218e-4848-ae8a-25e931412165_084098cd-f08f-447f-a3da-3234a9b06aff.jpg\",\n" +
            "    \"ratings\": 5,\n" +
            "    \"featured\": true\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Veg Rice\",\n" +
            "    \"price\": 150,\n" +
            "    \"diet\": 1,\n" +
            "    \"category\": 5,\n" +
            "    \"description\": \"Spicy paneer, candied mango and charred bell pepper sandwich served on white and brown bread\",\n" +
            "    \"img\": \"https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_112,h_112,c_fill/ne2lpcebxpvbrrncmvxi\",\n" +
            "    \"ratings\": 4.5,\n" +
            "    \"featured\": false\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Chinese Rice\",\n" +
            "    \"price\": 200,\n" +
            "    \"diet\": 2,\n" +
            "    \"category\": 5,\n" +
            "    \"description\": \"Spicy paneer, candied mango and charred bell pepper sandwich served on white and brown bread\",\n" +
            "    \"img\": \"https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_112,h_112,c_fill/hedzfqumdcq97kn2kgh3\",\n" +
            "    \"ratings\": 3.5,\n" +
            "    \"featured\": true\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Veg Biryani\",\n" +
            "    \"price\": 200,\n" +
            "    \"diet\": 1,\n" +
            "    \"category\": 5,\n" +
            "    \"description\": \"Spicy paneer, candied mango and charred bell pepper sandwich served on white and brown bread\",\n" +
            "    \"img\": \"https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_112,h_112,c_fill/txejcebtxl91xaksvkla\",\n" +
            "    \"ratings\": 3.5,\n" +
            "    \"featured\": false\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Chicken Biryani\",\n" +
            "    \"price\": 300,\n" +
            "    \"diet\": 2,\n" +
            "    \"category\": 5,\n" +
            "    \"description\": \"Spicy paneer, candied mango and charred bell pepper sandwich served on white and brown bread\",\n" +
            "    \"img\": \"https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_112,h_112,c_fill/gxghope8gkvkyyhicv0p\",\n" +
            "    \"ratings\": 4.5,\n" +
            "    \"featured\": true\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Vadapav\",\n" +
            "    \"price\": 50,\n" +
            "    \"diet\": 1,\n" +
            "    \"category\": 6,\n" +
            "    \"description\": \"Spicy paneer, candied mango and charred bell pepper sandwich served on white and brown bread\",\n" +
            "    \"img\": \"https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_112,h_112,c_fill/onwcfeuufp4ki6tykxcr\",\n" +
            "    \"ratings\": 5,\n" +
            "    \"featured\": false\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Samosa\",\n" +
            "    \"price\": 50,\n" +
            "    \"diet\": 1,\n" +
            "    \"category\": 6,\n" +
            "    \"description\": \"Spicy paneer, candied mango and charred bell pepper sandwich served on white and brown bread\",\n" +
            "    \"img\": \"https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_112,h_112,c_fill/xwfcgbriwkfttaqsrt2x\",\n" +
            "    \"ratings\": 2,\n" +
            "    \"featured\": false\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Kachori\",\n" +
            "    \"price\": 60,\n" +
            "    \"diet\": 1,\n" +
            "    \"category\": 6,\n" +
            "    \"description\": \"Spicy paneer, candied mango and charred bell pepper sandwich served on white and brown bread\",\n" +
            "    \"img\": \"https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_112,h_112,c_fill/cc3b9da18bd496d22163a766c911dee3\",\n" +
            "    \"ratings\": 4,\n" +
            "    \"featured\": false\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Bread Patties\",\n" +
            "    \"price\": 100,\n" +
            "    \"diet\": 1,\n" +
            "    \"category\": 61,\n" +
            "    \"description\": \"Spicy paneer, candied mango and charred bell pepper sandwich served on white and brown bread\",\n" +
            "    \"img\": \"https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_112,h_112,c_fill/ensqvz8kk2dkdhacnmdi\",\n" +
            "    \"ratings\": 3,\n" +
            "    \"featured\": false\n" +
            "  }\n" +
            "]\n";

    public static void main(String[] args) {



        getfoods();
    }

    public static List<FoodItem> getfoods(){
        try {
            List<FoodItem> users = objectMapper.readValue(food, new TypeReference<List<FoodItem>>(){});
            for (FoodItem user : users) {
                System.out.println(user);
            }
            return users;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public static List<Inventory> getInv(){
        try {
            List<Inventory> users = objectMapper.readValue(inventory, new TypeReference<List<Inventory>>(){});
            for (Inventory user : users) {
                System.out.println(user);
            }
            return users;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public static  List<Category> getCats(){
        try {
            List<Category> users = objectMapper.readValue(categories, new TypeReference<List<Category>>(){});
            for (Category user : users) {
                System.out.println(user);
            }

            return users;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
